package com.kopo.portal.model;

import java.time.LocalDate;

public class JobPosting {

    private Long id;
    private String company;
    private String role;
    private String region;
    private LocalDate deadline;
    private LocalDate requestedDate;
    private String employmentType;
    private String salary;
    private String description;
    private String requirements;

    public JobPosting() {
    }

    public JobPosting(Long id, String company, String role, String region,
                      LocalDate deadline, LocalDate requestedDate,
                      String employmentType, String salary,
                      String description, String requirements) {
        this.id = id;
        this.company = company;
        this.role = role;
        this.region = region;
        this.deadline = deadline;
        this.requestedDate = requestedDate;
        this.employmentType = employmentType;
        this.salary = salary;
        this.description = description;
        this.requirements = requirements;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }

    public LocalDate getRequestedDate() { return requestedDate; }
    public void setRequestedDate(LocalDate requestedDate) { this.requestedDate = requestedDate; }

    public String getEmploymentType() { return employmentType; }
    public void setEmploymentType(String employmentType) { this.employmentType = employmentType; }

    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getRequirements() { return requirements; }
    public void setRequirements(String requirements) { this.requirements = requirements; }
}
