package com.kopo.portal.controller;

import com.kopo.portal.service.JobService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 채용정보조회 게시판 (목록 + 상세).
 */
@Controller
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public String list(Model model) {
        model.addAttribute("jobs", jobService.findAll());
        return "jobs";
    }

    @GetMapping("/jobs/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("job", jobService.findById(id).orElse(null));
        return "job-detail";
    }
}
