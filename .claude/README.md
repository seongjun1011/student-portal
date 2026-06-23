# 학생정보시스템 (Student Portal) - Spring Boot

성준대학교 학생정보시스템/통합포털을 참고하여, 1학년 때 HTML/CSS/JS로 만들었던 개인 홈페이지 프로젝트를
**Spring Boot MVC 구조**로 재구성한 프로젝트입니다.

## 주요 기술

- Java 17, Spring Boot 3.2.5 (Maven)
- Spring MVC (Controller - Service - Model 계층 구조)
- Thymeleaf (서버사이드 템플릿 렌더링)
- HttpSession 기반 직접 구현한 로그인/인증 (Spring Security 미사용)
- HandlerInterceptor를 이용한 로그인 여부 검사 (`/login`, `/error`, 정적 리소스 제외)
- 별도의 DB 없이 Service 계층의 메모리 저장소(List/Map)로 데이터 관리
- OpenWeatherMap REST API 연동 (RestTemplate), 키 미설정 시 더미 데이터로 대체

## 실행 방법

1. IntelliJ에서 프로젝트 열기 (Maven 프로젝트로 자동 인식)
2. `StudentPortalApplication.java` 실행
3. 브라우저에서 `http://localhost:8080` 접속
4. 데모 계정으로 로그인
   - `2301110329` / `1234` (박성준)

날씨 위젯에 실시간 데이터를 표시하려면 `src/main/resources/application.properties`의
`weather.api.key`에 [OpenWeatherMap](https://openweathermap.org/api)에서 발급받은 키를 입력하세요.
입력하지 않으면 더미 날씨 데이터가 표시됩니다.

## 구현된 기능

- 로그인 / 로그아웃
- 통합포털 홈 (날씨, 주간 시간표, 학사일정, 채용정보, 공지사항 위젯)
- 수강신청 / 수강신청내역조회 (정원 초과 검증, 신청 시 confirm 확인창)
- 개인시간표조회 (교시×요일 격자, 과목별 색상 구분, 신청과목수·주간수업일수 요약)
- 개인신상정보수정 (연락처·주소·계좌번호 수정 / 학과·캠퍼스는 조회만 가능)
- 학사일정조회 (월별 조회, 이전/다음달 이동)
- 휴학/복학신청 (담당직원 → 학과장 → 학생처장 3단계 결재 흐름 표시)
- 장학금신청 (담당직원 → 장학위원회 → 학생처장 3단계 결재 흐름 표시)
- 공지사항조회
- 채용정보조회 (고용형태·급여·상세설명·지원자격 포함, 목록 클릭 시 상세 이동)

> 실제 학교 시스템의 메뉴 구조를 최대한 그대로 옮겼으며, 이번 과제에서 직접 구현한 기능은 위와 같습니다.
> 사이드바의 일부 메뉴(증명서발급신청, 등록금조회, 성적조회 등)는 화면 구조만 반영된 비활성 메뉴입니다.
> 장학금신청의 서류 첨부 기능은 모델(`UploadedFile`)과 업로드 설정(`application.properties`의 `spring.servlet.multipart.*`)만 준비되어 있고, 화면 폼에는 아직 연결되지 않았습니다.

## 데이터 관리 방식

DB를 아직 학습하지 않은 단계이므로, 모든 데이터는 각 `Service` 클래스 내부의
`CopyOnWriteArrayList` 기반 메모리 저장소에서 관리됩니다. 서버를 재시작하면 초기 더미 데이터로 리셋됩니다.
휴학신청·장학금신청은 실행 시점에 결재가 일부/전부 진행된 데모 데이터가 미리 채워져 있어,
승인·반려·진행중 상태를 바로 확인할 수 있습니다.
