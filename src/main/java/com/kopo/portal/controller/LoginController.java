package com.kopo.portal.controller;

import com.kopo.portal.model.Student;
import com.kopo.portal.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * 로그인/로그아웃을 처리하는 컨트롤러.
 * 데모용 계정: student1 / 1234, student2 / 1234
 */
@Controller
public class LoginController {

    private final StudentService studentService;

    public LoginController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String loginId,
                         @RequestParam String password,
                         HttpServletRequest request,
                         Model model) {
        Optional<Student> result = studentService.login(loginId, password);
        if (result.isEmpty()) {
            model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "login";
        }
        Student student = result.get();
        studentService.recordLogin(student, request.getRemoteAddr());
        request.getSession().setAttribute("loginStudent", student);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
