package com.kopo.portal.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ScholarshipApplication {

    public static final String STATUS_PENDING  = "심사중";
    public static final String STATUS_APPROVED = "승인";
    public static final String STATUS_REJECTED = "반려";

    // 결재 단계
    public static class ApprovalStep {
        private final String role;
        private final String approverName;
        private String status;        // 대기중 / 승인 / 반려
        private LocalDateTime processedAt;
        private String comment;

        public ApprovalStep(String role, String approverName) {
            this.role = role;
            this.approverName = approverName;
            this.status = "대기중";
        }

        public String getRole() { return role; }
        public String getApproverName() { return approverName; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public LocalDateTime getProcessedAt() { return processedAt; }
        public void setProcessedAt(LocalDateTime processedAt) { this.processedAt = processedAt; }
        public String getComment() { return comment; }
        public void setComment(String comment) { this.comment = comment; }
    }

    // 첨부 파일
    public static class UploadedFile {
        private final String originalName;
        private final String storedName;

        public UploadedFile(String originalName, String storedName) {
            this.originalName = originalName;
            this.storedName = storedName;
        }

        public String getOriginalName() { return originalName; }
        public String getStoredName() { return storedName; }
    }

    private Long id;
    private Long studentId;
    private String type;
    private String reason;
    private String status;
    private LocalDateTime appliedAt;
    private List<ApprovalStep> approvalSteps;
    private List<UploadedFile> uploadedFiles;

    public ScholarshipApplication() {}

    public ScholarshipApplication(Long id, Long studentId, String type, String reason,
                                   LocalDateTime appliedAt) {
        this.id = id;
        this.studentId = studentId;
        this.type = type;
        this.reason = reason;
        this.appliedAt = appliedAt;
        this.approvalSteps = new ArrayList<>(List.of(
                new ApprovalStep("담당직원", "이수진"),
                new ApprovalStep("장학위원회", "박현수"),
                new ApprovalStep("학생처장", "김태호")
        ));
        this.uploadedFiles = new ArrayList<>();
        this.status = STATUS_PENDING;
    }

    /** 결재 단계 상태에서 전체 상태 계산 */
    public void recalcStatus() {
        boolean anyRejected = approvalSteps.stream().anyMatch(s -> "반려".equals(s.getStatus()));
        boolean allApproved = approvalSteps.stream().allMatch(s -> "승인".equals(s.getStatus()));
        if (anyRejected) this.status = STATUS_REJECTED;
        else if (allApproved) this.status = STATUS_APPROVED;
        else this.status = STATUS_PENDING;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getStudentId() { return studentId; }
    public String getType() { return type; }
    public String getReason() { return reason; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getAppliedAt() { return appliedAt; }
    public List<ApprovalStep> getApprovalSteps() { return approvalSteps; }
    public List<UploadedFile> getUploadedFiles() { return uploadedFiles; }
}
