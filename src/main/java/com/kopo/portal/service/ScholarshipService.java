package com.kopo.portal.service;

import com.kopo.portal.model.ScholarshipApplication;
import com.kopo.portal.model.ScholarshipApplication.ApprovalStep;
import com.kopo.portal.model.ScholarshipApplication.UploadedFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ScholarshipService {

    @Value("${upload.dir.scholarship}")
    private String uploadDir;

    private final List<ScholarshipApplication> applications = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public ScholarshipService() {
        // 데모 데이터 — student id=1 (박성준)
        ScholarshipApplication a1 = new ScholarshipApplication(
                idGenerator.getAndIncrement(), 1L, "성적우수장학금",
                "지난 학기 성적 4.3으로 우수장학금 신청합니다.",
                LocalDateTime.now().minusDays(10));
        approve(a1, 0, "서류 확인 완료", LocalDateTime.now().minusDays(9));
        approve(a1, 1, "성적 기준 충족 확인", LocalDateTime.now().minusDays(7));
        approve(a1, 2, "최종 승인", LocalDateTime.now().minusDays(5));
        a1.recalcStatus();
        applications.add(a1);

        ScholarshipApplication a3 = new ScholarshipApplication(
                idGenerator.getAndIncrement(), 1L, "근로장학금",
                "교내 근로 장학생으로 참여하고자 신청합니다.",
                LocalDateTime.now().minusDays(1));
        approve(a3, 0, "서류 확인 완료", LocalDateTime.now().minusHours(20));
        a3.recalcStatus();
        applications.add(a3);
    }

    private void approve(ScholarshipApplication app, int stepIdx, String comment, LocalDateTime at) {
        ApprovalStep step = app.getApprovalSteps().get(stepIdx);
        step.setStatus("승인");
        step.setComment(comment);
        step.setProcessedAt(at);
    }

    private void reject(ScholarshipApplication app, int stepIdx, String comment, LocalDateTime at) {
        ApprovalStep step = app.getApprovalSteps().get(stepIdx);
        step.setStatus("반려");
        step.setComment(comment);
        step.setProcessedAt(at);
    }

    public ScholarshipApplication apply(Long studentId, String type, String reason, MultipartFile[] files) throws IOException {
        ScholarshipApplication app = new ScholarshipApplication(
                idGenerator.getAndIncrement(), studentId, type, reason, LocalDateTime.now());
        saveFiles(app, files);
        applications.add(app);
        return app;
    }

    private void saveFiles(ScholarshipApplication app, MultipartFile[] files) throws IOException {
        if (files == null) return;
        Path dir = Paths.get(uploadDir);
        Files.createDirectories(dir);
        for (MultipartFile f : files) {
            if (f != null && !f.isEmpty()) {
                String ext = StringUtils.getFilenameExtension(f.getOriginalFilename());
                String stored = UUID.randomUUID() + (ext != null ? "." + ext : "");
                f.transferTo(dir.resolve(stored));
                app.getUploadedFiles().add(new UploadedFile(f.getOriginalFilename(), stored));
            }
        }
    }

    public List<ScholarshipApplication> findByStudent(Long studentId) {
        return applications.stream()
                .filter(a -> a.getStudentId().equals(studentId))
                .sorted((a, b) -> b.getAppliedAt().compareTo(a.getAppliedAt()))
                .collect(Collectors.toList());
    }
}
