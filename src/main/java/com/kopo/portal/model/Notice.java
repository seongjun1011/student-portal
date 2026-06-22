package com.kopo.portal.model;

import java.time.LocalDateTime;

/**
 * 공지사항 게시글을 표현하는 모델 클래스.
 */
public class Notice {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt;

    public Notice() {
    }

    public Notice(Long id, String title, String content, String writer, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
