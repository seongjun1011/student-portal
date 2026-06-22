package com.kopo.portal.service;

import com.kopo.portal.model.Student;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StudentService {

    private final List<Student> students = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public StudentService() {
        students.add(new Student(idGenerator.getAndIncrement(), "2301110329", "1234",
                "박성준", "인공지능소프트웨어과", "서울정수캠퍼스", "2학년"));
    }

    public Optional<Student> login(String loginId, String password) {
        return students.stream()
                .filter(s -> s.getLoginId().equals(loginId) && s.getPassword().equals(password))
                .findFirst();
    }

    public Optional<Student> findById(Long id) {
        return students.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    public void recordLogin(Student student, String ip) {
        student.setLastLoginAt(LocalDateTime.now());
        student.setLastLoginIp(ip);
    }

    public void updateProfile(Long id, String phone, String address, String accountNumber) {
        findById(id).ifPresent(s -> {
            s.setPhone(phone);
            s.setAddress(address);
            s.setAccountNumber(accountNumber);
        });
    }
}
