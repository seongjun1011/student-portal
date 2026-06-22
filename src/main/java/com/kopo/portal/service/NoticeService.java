package com.kopo.portal.service;

import com.kopo.portal.model.Notice;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * 공지사항 게시글 데이터를 메모리에서 관리하는 서비스.
 */
@Service
public class NoticeService {

    private final List<Notice> notices = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public NoticeService() {
        notices.add(new Notice(idGenerator.getAndIncrement(), "기말고사 시간표 안내",
                "6월 16일부터 22일까지 기말고사가 진행됩니다. 자세한 시간표는 첨부파일을 확인하세요.",
                "학사지원팀", LocalDateTime.of(2026, 6, 10, 9, 0)));
        notices.add(new Notice(idGenerator.getAndIncrement(), "여름방학 중 도서관 운영 안내",
                "여름방학 기간 중 전자도서관은 평소와 동일하게 24시간 운영됩니다.",
                "도서관", LocalDateTime.of(2026, 6, 12, 14, 0)));
        notices.add(new Notice(idGenerator.getAndIncrement(), "2학기 수강신청 일정 사전 안내",
                "2학기 수강신청은 8월 중 진행 예정이며, 정확한 일정은 추후 공지됩니다.",
                "학사지원팀", LocalDateTime.of(2026, 6, 15, 10, 30)));
    }

    public List<Notice> findAll() {
        return notices.stream()
                .sorted(Comparator.comparing(Notice::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    public Optional<Notice> findById(Long id) {
        return notices.stream().filter(n -> n.getId().equals(id)).findFirst();
    }
}
