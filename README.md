# 학생정보시스템 (Student Portal) - Spring Boot

한국폴리텍대학 학생정보시스템/통합포털을 참고하여, 1학년 때 HTML/CSS/JS로 만들었던 개인 홈페이지 프로젝트를
**Spring Boot MVC 구조**로 재구성한 프로젝트입니다.

## 주요 기술

- Java 17, Spring Boot 3.2.5 (Maven)
- Spring MVC (Controller - Service - Model 계층 구조)
- Thymeleaf (서버사이드 템플릿 렌더링)
- HttpSession 기반 직접 구현한 로그인/인증 (Spring Security 미사용)
- HandlerInterceptor를 이용한 로그인 여부 검사
- 별도의 DB 없이 Service 계층의 메모리 저장소(List/Map)로 데이터 관리
- OpenWeatherMap REST API 연동 (RestTemplate), 키 미설정 시 더미 데이터로 대체

## 실행 방법

1. IntelliJ에서 프로젝트 열기 (Maven 프로젝트로 자동 인식)
2. `StudentPortalApplication.java` 실행
3. 브라우저에서 `http://localhost:8080` 접속
4. 데모 계정으로 로그인
   - `student1` / `1234` (박성준)
   - `student2` / `1234` (김민준)

날씨 위젯에 실시간 데이터를 표시하려면 `src/main/resources/application.properties`의
`weather.api.key`에 [OpenWeatherMap](https://openweathermap.org/api)에서 발급받은 키를 입력하세요.
입력하지 않으면 더미 날씨 데이터가 표시됩니다.

## 구현된 기능

- 로그인 / 로그아웃
- 통합포털 홈 (날씨, 주간 시간표, 학사일정, 채용정보, 공지사항 위젯)
- 개인신상정보수정
- 학사일정조회
- 휴학/복학신청
- 장학금신청
- 수강신청 / 수강신청내역조회 (정원 초과, 중복신청 검증)
- 개인시간표조회
- 공지사항 / 채용정보조회

> 실제 학교 시스템의 메뉴 구조를 최대한 그대로 옮겼으며, 이번 과제에서 직접 구현한 기능은 위와 같습니다.
> 사이드바의 일부 메뉴(증명서발급신청, 등록금조회, 성적조회 등)는 화면 구조만 반영된 비활성 메뉴입니다.

## 데이터 관리 방식

DB를 아직 학습하지 않은 단계이므로, 모든 데이터는 각 `Service` 클래스 내부의
`CopyOnWriteArrayList` 기반 메모리 저장소에서 관리됩니다. 서버를 재시작하면 초기 더미 데이터로 리셋됩니다.
