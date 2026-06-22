package com.kopo.portal.service;

import com.kopo.portal.model.Course;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 개설 강좌 데이터를 메모리에서 관리하는 서비스.
 */
@Service
public class CourseService {

    private final List<Course> courses = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public CourseService() {
        courses.add(new Course(idGenerator.getAndIncrement(), "자바프로그래밍",  "박지원", "월", 1, 2, "201호", 30));
        courses.add(new Course(idGenerator.getAndIncrement(), "스프링부트웹개발", "김정희", "화", 3, 4, "302호", 25));
        courses.add(new Course(idGenerator.getAndIncrement(), "데이터베이스",    "이익",   "수", 1, 2, "201호", 30));
        courses.add(new Course(idGenerator.getAndIncrement(), "알고리즘",        "송시열", "목", 5, 6, "401호", 20));
        courses.add(new Course(idGenerator.getAndIncrement(), "캡스톤디자인",    "최익현", "금", 3, 4, "501호", 15));
    }

    public List<Course> findAll() {
        return courses;
    }

    public Optional<Course> findById(Long id) {
        return courses.stream().filter(c -> c.getId().equals(id)).findFirst();
    }
}
