package com.kopo.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 학생정보시스템 Spring Boot 메인 애플리케이션.
 * 1학년 때 HTML/CSS/JS로 만들었던 홈페이지를 Spring Boot의 MVC 구조
 * (Controller - Service - Model)로 재구성한 프로젝트의 진입점이다.
 *
 * 별도의 DB 없이, Service 계층의 메모리(List/Map) 저장소로
 * 데이터를 관리한다. (서버 재시작 시 초기 더미 데이터로 리셋됨)
 */
@SpringBootApplication
public class StudentPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentPortalApplication.class, args);
    }
}
