package com.kopo.portal.model;

public class Course {

    private Long id;
    private String name;
    private String professor;
    private String dayOfWeek;
    private int startPeriod;
    private int endPeriod;
    private String location;
    private int capacity;
    private int enrolledCount;

    public Course() {
    }

    public Course(Long id, String name, String professor, String dayOfWeek,
                  int startPeriod, int endPeriod, String location, int capacity) {
        this.id = id;
        this.name = name;
        this.professor = professor;
        this.dayOfWeek = dayOfWeek;
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
        this.location = location;
        this.capacity = capacity;
        this.enrolledCount = 0;
    }

    public boolean isFull() {
        return enrolledCount >= capacity;
    }

    /** 기존 템플릿 호환용 — "N교시" 또는 "N-M교시" 형태로 반환 */
    public String getPeriod() {
        if (startPeriod == endPeriod) return startPeriod + "교시";
        return startPeriod + "-" + endPeriod + "교시";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getProfessor() { return professor; }
    public void setProfessor(String professor) { this.professor = professor; }

    public String getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public int getStartPeriod() { return startPeriod; }
    public void setStartPeriod(int startPeriod) { this.startPeriod = startPeriod; }

    public int getEndPeriod() { return endPeriod; }
    public void setEndPeriod(int endPeriod) { this.endPeriod = endPeriod; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public int getEnrolledCount() { return enrolledCount; }
    public void setEnrolledCount(int enrolledCount) { this.enrolledCount = enrolledCount; }
}
