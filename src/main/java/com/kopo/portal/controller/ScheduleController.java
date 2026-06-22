package com.kopo.portal.controller;

import com.kopo.portal.service.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

/**
 * 학적/학생 > 학사력조회 페이지. 월 단위로 학사일정을 보여주고, 이전/다음 달로 이동할 수 있다.
 */
@Controller
public class ScheduleController {

    private static final DateTimeFormatter MONTH_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM");

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/schedule")
    public String schedule(@RequestParam(required = false) String month, Model model) {
        YearMonth target = (month == null || month.isBlank())
                ? YearMonth.now()
                : YearMonth.parse(month, MONTH_FORMAT);

        model.addAttribute("month", target);
        model.addAttribute("prevMonth", target.minusMonths(1).format(MONTH_FORMAT));
        model.addAttribute("nextMonth", target.plusMonths(1).format(MONTH_FORMAT));
        model.addAttribute("schedules", scheduleService.findByMonth(target));
        return "schedule";
    }
}
