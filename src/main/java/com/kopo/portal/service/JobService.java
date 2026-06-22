package com.kopo.portal.service;

import com.kopo.portal.model.JobPosting;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class JobService {

    private final List<JobPosting> jobs = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public JobService() {
        jobs.add(new JobPosting(
                idGenerator.getAndIncrement(),
                "DRB오토메이션", "선행기술부 연구원", "부산광역시",
                LocalDate.of(2026, 6, 30), LocalDate.of(2026, 6, 18),
                "정규직", "회사 내규에 따름",
                "DRB오토메이션은 산업용 자동화 설비 및 로봇 시스템을 개발하는 기업입니다.\n" +
                "선행기술부에서 신규 자동화 기술을 연구하고 제품 개발에 기여할 연구원을 모집합니다.\n\n" +
                "• 주요 업무: PLC/HMI 기반 자동화 시스템 설계 및 개발\n" +
                "• 로봇 제어 알고리즘 연구 및 적용\n" +
                "• 공정 자동화 프로세스 개선 및 최적화\n" +
                "• 고객사 현장 설치 및 기술 지원",
                "• 기계, 전기, 전자, 메카트로닉스 관련 학과 졸업(예정)자\n" +
                "• PLC(LS, 미쓰비시, 지멘스 중 1개 이상) 조작 가능자 우대\n" +
                "• AutoCAD 또는 SolidWorks 활용 가능자 우대\n" +
                "• 병역 필 또는 면제자로 해외 출장 가능한 분"
        ));

        jobs.add(new JobPosting(
                idGenerator.getAndIncrement(),
                "모닥불에너지", "히트펌프 재생 엔지니어", "서울특별시",
                LocalDate.of(2026, 6, 28), LocalDate.of(2026, 6, 17),
                "정규직", "3,200만원 ~ 4,000만원",
                "모닥불에너지는 신재생에너지 분야의 히트펌프 시스템 전문 기업입니다.\n" +
                "건물 냉난방 에너지 효율화를 위한 히트펌프 재생 및 유지보수 엔지니어를 채용합니다.\n\n" +
                "• 주요 업무: 히트펌프 시스템 점검 및 유지보수\n" +
                "• 냉매 회수·충전 및 부품 교체 작업\n" +
                "• 고객사 현장 에너지 진단 및 개선 제안\n" +
                "• 신규 설치 프로젝트 지원",
                "• 냉동공조기사, 공조냉동기계기능사 자격증 소지자 우대\n" +
                "• 히트펌프 또는 냉동공조 관련 실무 경험 1년 이상 우대\n" +
                "• 운전면허 1종 보통 소지자\n" +
                "• 성실하고 팀워크를 중시하는 분"
        ));

        jobs.add(new JobPosting(
                idGenerator.getAndIncrement(),
                "(주)에이블테크", "2차전지 비전 검사기 제작", "경기도",
                LocalDate.of(2026, 6, 27), LocalDate.of(2026, 6, 17),
                "계약직 (정규직 전환 가능)", "협의 후 결정",
                "(주)에이블테크는 머신비전 기반 품질검사 장비를 전문으로 하는 기업입니다.\n" +
                "급성장하는 2차전지 시장을 대응하기 위한 비전 검사기 제작 인력을 모집합니다.\n\n" +
                "• 주요 업무: 2차전지 셀·모듈·팩 외관 비전 검사 시스템 개발\n" +
                "• 카메라, 조명, 렌즈 등 광학계 설계 및 셋업\n" +
                "• 이미지 처리 알고리즘 개발 (C++/Python)\n" +
                "• 납품 및 현장 설치, 고객 교육",
                "• 컴퓨터비전, 영상처리, 전기전자, 기계 관련 학과 출신\n" +
                "• OpenCV, Halcon 등 비전 라이브러리 활용 경험자 우대\n" +
                "• C++ 또는 Python 프로그래밍 가능자\n" +
                "• 2차전지 또는 반도체 장비 업계 경험자 우대"
        ));

        jobs.add(new JobPosting(
                idGenerator.getAndIncrement(),
                "(주)삼익", "오설록 시설관리직 채용", "제주특별자치도",
                LocalDate.of(2026, 6, 25), LocalDate.of(2026, 6, 15),
                "정규직", "2,800만원 ~ 3,400만원",
                "(주)삼익은 제주 오설록 티뮤지엄 및 관련 시설의 운영·관리를 담당하는 기업입니다.\n" +
                "제주 본사 소재 시설물의 전기·기계 설비를 관리할 시설관리직 사원을 채용합니다.\n\n" +
                "• 주요 업무: 건물 전기·소방·기계 설비 일상 점검 및 유지보수\n" +
                "• 냉난방 공조 설비 운전 및 관리\n" +
                "• 시설 내 안전 관리 및 비상 대응\n" +
                "• 외주 업체 관리 및 공사 감독",
                "• 전기기사, 전기산업기사, 건축설비기사 중 1개 이상 소지자 우대\n" +
                "• 시설관리 또는 건물관리 유경험자 우대\n" +
                "• 제주 거주자 또는 제주 이주 가능한 분\n" +
                "• 성실하고 책임감 있는 분"
        ));
    }

    public List<JobPosting> findAll() {
        return jobs.stream()
                .sorted(Comparator.comparing(JobPosting::getRequestedDate).reversed())
                .collect(Collectors.toList());
    }

    public Optional<JobPosting> findById(Long id) {
        return jobs.stream().filter(j -> j.getId().equals(id)).findFirst();
    }
}
