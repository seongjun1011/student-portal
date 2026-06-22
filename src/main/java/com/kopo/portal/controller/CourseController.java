package com.kopo.portal.controller;

import com.kopo.portal.model.Student;
import com.kopo.portal.service.CourseService;
import com.kopo.portal.service.EnrollmentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 수업/성적 > 수강신청, 수강신청내역조회 컨트롤러.
 * 학생-과목-수강신청(Enrollment) 관계를 다루는 핵심 기능.
 */
@Controller
public class CourseController {

    private final CourseService courseService;
    private final EnrollmentService enrollmentService;

    public CourseController(CourseService courseService, EnrollmentService enrollmentService) {
        this.courseService = courseService;
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/courses")
    public String list(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("loginStudent");
        List<Long> enrolledIds = enrollmentService.findByStudent(student.getId())
                .stream().map(e -> e.getCourseId()).toList();

        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("enrolledIds", enrolledIds);
        return "courses";
    }

    @PostMapping("/courses/{id}/enroll")
    public String enroll(@PathVariable Long id, HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("loginStudent");
        String error = enrollmentService.enroll(student.getId(), id);
        if (error != null) {
            model.addAttribute("error", error);
            List<Long> enrolledIds = enrollmentService.findByStudent(student.getId())
                    .stream().map(e -> e.getCourseId()).toList();
            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("enrolledIds", enrolledIds);
            return "courses";
        }
        return "redirect:/courses";
    }

    @PostMapping("/courses/{id}/cancel")
    public String cancel(@PathVariable Long id, HttpSession session) {
        Student student = (Student) session.getAttribute("loginStudent");
        enrollmentService.cancel(student.getId(), id);
        return "redirect:/courses";
    }

    @GetMapping("/enrollments")
    public String myEnrollments(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("loginStudent");
        model.addAttribute("courses", enrollmentService.findEnrolledCourses(student.getId()));
        return "enrollments";
    }
}
