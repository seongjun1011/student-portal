package com.kopo.portal.service;

import com.kopo.portal.model.AcademicSchedule;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * 학사일정 데이터를 메모리에서 관리하는 서비스.
 */
@Service
public class ScheduleService {

    private final List<AcademicSchedule> schedules = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public ScheduleService() {
        schedules.add(new AcademicSchedule(idGenerator.getAndIncrement(),
                LocalDate.of(2026, 6, 16), LocalDate.of(2026, 6, 22), "기말고사"));
        schedules.add(new AcademicSchedule(idGenerator.getAndIncrement(),
                LocalDate.of(2026, 6, 30), LocalDate.of(2026, 6, 30), "1학기 종강"));
        schedules.add(new AcademicSchedule(idGenerator.getAndIncrement(),
                LocalDate.of(2026, 7, 6), LocalDate.of(2026, 7, 10), "여름방학 시작"));
    }

    public List<AcademicSchedule> findByMonth(YearMonth month) {
        return schedules.stream()
                .filter(s -> YearMonth.from(s.getStartDate()).equals(month)
                        || YearMonth.from(s.getEndDate()).equals(month))
                .sorted((a, b) -> a.getStartDate().compareTo(b.getStartDate()))
                .collect(Collectors.toList());
    }

    public List<AcademicSchedule> findAll() {
        return schedules;
    }
}
