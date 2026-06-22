package com.kopo.portal.controller;

import com.kopo.portal.model.Student;
import com.kopo.portal.service.LeaveService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping("/leave")
    public String page(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("loginStudent");
        model.addAttribute("applications", leaveService.findByStudent(student.getId()));
        return "leave";
    }

    @PostMapping("/leave")
    public String apply(@RequestParam String type,
                        @RequestParam String reason,
                        @RequestParam(required = false) MultipartFile[] files,
                        HttpSession session) throws IOException {
        Student student = (Student) session.getAttribute("loginStudent");
        leaveService.apply(student.getId(), type, reason, files);
        return "redirect:/leave";
    }
}
