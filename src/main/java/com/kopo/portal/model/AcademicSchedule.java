package com.kopo.portal.model;

import java.time.LocalDate;

/**
 * 학사일정 한 건을 표현하는 모델 클래스.
 */
public class AcademicSchedule {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;  // 단일 날짜 일정이면 startDate와 동일
    private String title;

    public AcademicSchedule() {
    }

    public AcademicSchedule(Long id, LocalDate startDate, LocalDate endDate, String title) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /** 화면에 "06-16~06-22" 또는 "06-30" 형태로 표시하기 위한 헬퍼 */
    public String getDisplayRange() {
        String start = String.format("%02d-%02d", startDate.getMonthValue(), startDate.getDayOfMonth());
        if (endDate == null || endDate.equals(startDate)) {
            return start;
        }
        String end = String.format("%02d-%02d", endDate.getMonthValue(), endDate.getDayOfMonth());
        return start + "~" + end;
    }
}
