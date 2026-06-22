package com.kopo.portal.service;

import com.kopo.portal.model.Course;
import com.kopo.portal.model.Enrollment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * 수강신청(학생-과목 관계) 처리를 담당하는 서비스.
 * 정원 초과, 중복 신청 같은 비즈니스 규칙을 검증하는 핵심 로직이 들어있다.
 */
@Service
public class EnrollmentService {

    private final List<Enrollment> enrollments = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    private final CourseService courseService;

    public EnrollmentService(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * 수강신청을 시도한다.
     * @return 성공 시 null, 실패 시 사용자에게 보여줄 에러 메시지
     */
    public synchronized String enroll(Long studentId, Long courseId) {
        Course course = courseService.findById(courseId).orElse(null);
        if (course == null) {
            return "존재하지 않는 강좌입니다.";
        }
        boolean alreadyEnrolled = enrollments.stream()
                .anyMatch(e -> e.getStudentId().equals(studentId) && e.getCourseId().equals(courseId));
        if (alreadyEnrolled) {
            return "이미 신청한 강좌입니다.";
        }
        if (course.isFull()) {
            return "정원이 초과된 강좌입니다.";
        }
        enrollments.add(new Enrollment(idGenerator.getAndIncrement(), studentId, courseId, LocalDateTime.now()));
        course.setEnrolledCount(course.getEnrolledCount() + 1);
        return null;
    }

    public synchronized void cancel(Long studentId, Long courseId) {
        Enrollment target = enrollments.stream()
                .filter(e -> e.getStudentId().equals(studentId) && e.getCourseId().equals(courseId))
                .findFirst()
                .orElse(null);
        if (target == null) {
            return;
        }
        enrollments.remove(target);
        courseService.findById(courseId)
                .ifPresent(c -> c.setEnrolledCount(Math.max(0, c.getEnrolledCount() - 1)));
    }

    public List<Enrollment> findByStudent(Long studentId) {
        return enrollments.stream()
                .filter(e -> e.getStudentId().equals(studentId))
                .collect(Collectors.toList());
    }

    /** 로그인한 학생이 신청한 과목 목록(Course)을 그대로 반환한다. */
    public List<Course> findEnrolledCourses(Long studentId) {
        return findByStudent(studentId).stream()
                .map(e -> courseService.findById(e.getCourseId()).orElse(null))
                .filter(c -> c != null)
                .collect(Collectors.toList());
    }
}
