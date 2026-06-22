package com.kopo.portal.controller;

import com.kopo.portal.model.Student;
import com.kopo.portal.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyPageController {

    private final StudentService studentService;

    public MyPageController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/mypage")
    public String myPage(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("loginStudent");
        model.addAttribute("student", student);
        return "mypage";
    }

    @PostMapping("/mypage")
    public String updateMyPage(@RequestParam(defaultValue = "") String phone,
                               @RequestParam(defaultValue = "") String address,
                               @RequestParam(defaultValue = "") String accountNumber,
                               HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("loginStudent");
        studentService.updateProfile(student.getId(), phone, address, accountNumber);
        model.addAttribute("student", student);
        model.addAttribute("success", true);
        return "mypage";
    }
}
