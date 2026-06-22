package com.kopo.portal.service;

import com.kopo.portal.model.LeaveApplication;
import com.kopo.portal.model.LeaveApplication.ApprovalStep;
import com.kopo.portal.model.LeaveApplication.UploadedFile;
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
public class LeaveService {

    @Value("${upload.dir.leave}")
    private String uploadDir;

    private final List<LeaveApplication> applications = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public LeaveService() {
        // 데모 데이터 — student id=1
        LeaveApplication a1 = new LeaveApplication(
                idGenerator.getAndIncrement(), 1L, "휴학",
                "군 입대로 인한 휴학 신청입니다.",
                LocalDateTime.now().minusDays(8));
        approve(a1, 0, "서류 확인 완료", LocalDateTime.now().minusDays(7));
        approve(a1, 1, "학과 내규 검토 완료", LocalDateTime.now().minusDays(6));
        approve(a1, 2, "최종 승인", LocalDateTime.now().minusDays(5));
        a1.recalcStatus();
        applications.add(a1);

        LeaveApplication a2 = new LeaveApplication(
                idGenerator.getAndIncrement(), 1L, "복학",
                "군 전역 후 복학 신청입니다.",
                LocalDateTime.now().minusDays(3));
        approve(a2, 0, "전역 증명서 확인", LocalDateTime.now().minusDays(2));
        a2.recalcStatus();
        applications.add(a2);
    }

    private void approve(LeaveApplication app, int idx, String comment, LocalDateTime at) {
        ApprovalStep s = app.getApprovalSteps().get(idx);
        s.setStatus("승인"); s.setComment(comment); s.setProcessedAt(at);
    }

    public void apply(Long studentId, String type, String reason, MultipartFile[] files) throws IOException {
        LeaveApplication app = new LeaveApplication(
                idGenerator.getAndIncrement(), studentId, type, reason, LocalDateTime.now());
        saveFiles(app, files);
        applications.add(app);
    }

    private void saveFiles(LeaveApplication app, MultipartFile[] files) throws IOException {
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

    public List<LeaveApplication> findByStudent(Long studentId) {
        return applications.stream()
                .filter(a -> a.getStudentId().equals(studentId))
                .sorted((a, b) -> b.getAppliedAt().compareTo(a.getAppliedAt()))
                .collect(Collectors.toList());
    }
}
