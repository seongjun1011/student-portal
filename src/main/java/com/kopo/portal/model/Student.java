package com.kopo.portal.model;

import java.time.LocalDateTime;

public class Student {

    private Long id;
    private String loginId;
    private String password;
    private String name;
    private String department;
    private String campus;
    private String grade;
    private LocalDateTime lastLoginAt;
    private String lastLoginIp;
    // 수정 가능한 개인정보
    private String phone;
    private String address;
    private String accountNumber;

    public Student() {
    }

    public Student(Long id, String loginId, String password, String name,
                   String department, String campus, String grade) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.department = department;
        this.campus = campus;
        this.grade = grade;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLoginId() { return loginId; }
    public void setLoginId(String loginId) { this.loginId = loginId; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getCampus() { return campus; }
    public void setCampus(String campus) { this.campus = campus; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public LocalDateTime getLastLoginAt() { return lastLoginAt; }
    public void setLastLoginAt(LocalDateTime lastLoginAt) { this.lastLoginAt = lastLoginAt; }

    public String getLastLoginIp() { return lastLoginIp; }
    public void setLastLoginIp(String lastLoginIp) { this.lastLoginIp = lastLoginIp; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
}
