package com.kopo.portal.controller;

import com.kopo.portal.model.Student;
import com.kopo.portal.service.EnrollmentService;
import com.kopo.portal.service.JobService;
import com.kopo.portal.service.NoticeService;
import com.kopo.portal.service.ScheduleService;
import com.kopo.portal.service.WeatherService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.YearMonth;

/**
 * 통합포털 메인 대시보드. 위젯형으로 시간표/학사일정/채용정보/공지/날씨를 한 화면에 모은다.
 */
@Controller
public class DashboardController {

    private final EnrollmentService enrollmentService;
    private final ScheduleService scheduleService;
    private final JobService jobService;
    private final NoticeService noticeService;
    private final WeatherService weatherService;

    public DashboardController(EnrollmentService enrollmentService, ScheduleService scheduleService,
                                JobService jobService, NoticeService noticeService,
                                WeatherService weatherService) {
        this.enrollmentService = enrollmentService;
        this.scheduleService = scheduleService;
        this.jobService = jobService;
        this.noticeService = noticeService;
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String dashboard(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("loginStudent");
        model.addAttribute("student", student);
        model.addAttribute("now", LocalDateTime.now());
        model.addAttribute("weather", weatherService.getCurrentWeather());
        model.addAttribute("enrolledCourses", enrollmentService.findEnrolledCourses(student.getId()));
        model.addAttribute("schedules", scheduleService.findByMonth(YearMonth.now()));
        model.addAttribute("jobs", jobService.findAll());
        model.addAttribute("notices", noticeService.findAll());
        return "dashboard";
    }
}
