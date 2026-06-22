package com.kopo.portal.controller;

import com.kopo.portal.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 공지사항 게시판 (목록 + 상세).
 */
@Controller
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/notices")
    public String list(Model model) {
        model.addAttribute("notices", noticeService.findAll());
        return "notices";
    }

    @GetMapping("/notices/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("notice", noticeService.findById(id).orElse(null));
        return "notice-detail";
    }
}
