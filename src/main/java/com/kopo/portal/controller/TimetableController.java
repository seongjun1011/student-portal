package com.kopo.portal.controller;

import com.kopo.portal.model.Course;
import com.kopo.portal.model.Student;
import com.kopo.portal.service.EnrollmentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class TimetableController {

    private static final List<String>  DAYS    = List.of("월", "화", "수", "목", "금");
    private static final List<Integer> PERIODS = List.of(1, 2, 3, 4, 5, 6);
    private static final String[] PERIOD_TIMES = {
            "09:00 - 09:50", "10:00 - 10:50", "11:00 - 11:50",
            "12:00 - 12:50", "13:00 - 13:50", "14:00 - 14:50"
    };

    private final EnrollmentService enrollmentService;

    public TimetableController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    public static class Cell {
        public final boolean empty;
        public final boolean start;
        public final String name;
        public final String professor;
        public final String location;
        public final int color;

        public Cell() {
            this.empty = true; this.start = false;
            this.name = null; this.professor = null; this.location = null; this.color = 0;
        }

        public Cell(Course c, boolean start) {
            this.empty = false;
            this.start = start;
            this.name = c.getName();
            this.professor = c.getProfessor();
            this.location = c.getLocation();
            this.color = (int)(c.getId() % 5);
        }
    }

    public static class Row {
        public final String period;
        public final String time;
        public final List<Cell> cells;

        public Row(String period, String time, List<Cell> cells) {
            this.period = period;
            this.time = time;
            this.cells = cells;
        }
    }

    @GetMapping("/timetable")
    public String timetable(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("loginStudent");
        List<Course> enrolled = enrollmentService.findEnrolledCourses(student.getId());

        // "요일-교시" -> Course 매핑
        Map<String, Course> lookup = new HashMap<>();
        Set<String> startKeys = new HashSet<>();
        for (Course c : enrolled) {
            for (int p = c.getStartPeriod(); p <= c.getEndPeriod(); p++) {
                lookup.put(c.getDayOfWeek() + "-" + p, c);
            }
            startKeys.add(c.getDayOfWeek() + "-" + c.getStartPeriod());
        }

        // 행 x 열 그리드 미리 계산
        List<Row> grid = new ArrayList<>();
        for (int i = 0; i < PERIODS.size(); i++) {
            int p = PERIODS.get(i);
            List<Cell> cells = new ArrayList<>();
            for (String day : DAYS) {
                String key = day + "-" + p;
                Course c = lookup.get(key);
                if (c == null) {
                    cells.add(new Cell());
                } else {
                    cells.add(new Cell(c, startKeys.contains(key)));
                }
            }
            grid.add(new Row(p + "교시", PERIOD_TIMES[i], cells));
        }

        long activeDayCount = enrolled.stream().map(Course::getDayOfWeek).distinct().count();

        model.addAttribute("enrolled", enrolled);
        model.addAttribute("days", DAYS);
        model.addAttribute("grid", grid);
        model.addAttribute("activeDayCount", activeDayCount);
        return "timetable";
    }
}
