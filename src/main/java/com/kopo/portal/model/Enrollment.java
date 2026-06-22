package com.kopo.portal.model;

import java.time.LocalDateTime;

/**
 * 학생의 수강신청 내역(학생-과목 관계)을 표현하는 모델 클래스.
 */
public class Enrollment {

    private Long id;
    private Long studentId;
    private Long courseId;
    private LocalDateTime appliedAt;

    public Enrollment() {
    }

    public Enrollment(Long id, Long studentId, Long courseId, LocalDateTime appliedAt) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.appliedAt = appliedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }
}
