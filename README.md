# final_practice

## 배달 애플리케이션

### 프로젝트의 목적
- 지난기간 학습한 모든것을 최종적으로 점검하고 연습하기 위한 프로젝트
- 팀 프로젝트 진행 중 기억에 남는 튜터님들의 피드백을 모두 적용하는 목표를 가진 프로젝트
- 최대한 배운것을 모두 적용하는 프로젝트

### 스프링 심화 코드 개선 과제
#### 1일차. Controller, Service 패키지 내 클래스 개선
  - 1. ~~Controller Advice 로 예외 공통화 처리하기~~
    [https://github.com/gunnu3226/final_practice/blob/main/src/main/java/com/sparta/finalpractice/exception/ControllerAdvice.java](코드링크)
  - 2. ~~Service 인터페이스와 구현체 분리하여 추상화 하기~~
       ![스크린샷 2024-03-10 오전 2 59 18](https://github.com/gunnu3226/final_practice/assets/139452702/766c3b78-5629-45b5-aed2-b612f9d4b7d4)

#### 2일차. CustomException 정의 및 SpringAOP 적용
- 1. CustomException 정의
     [https://github.com/gunnu3226/final_practice/tree/main/src/main/java/com/sparta/finalpractice/exception](exception패키지)
- 2. Spring AOP 적용

#### 3일차. QueryDSL 을 사용하여 검색 기능 만들기

#### 4일차. Pageable 을 사용하여 페이징 및 정렬 기능 만들기

#### 5일차. Controller 테스트 코드 작성하기

#### 6일차. Service 테스트 코드 작성하기

#### 7일차. Repository 테스트 코드 작성하기

#### 8일차. AWS EC2 를 이용해 애플리케이션 .jar 파일 배포하기


### 개선한 점
- 스프링 시큐리티에서 토큰을 사용해 유저를 식별하는 내용이 강의에서 토큰을 쓸 때마다 유저 조회쿼리가 나가는것은 좋지 않다.
    > filter, jwtUtil, UserDetails, UserDetailsService 를 커스텀하여 토큰이 존재하는 동안 인증을 요청할 땐 토큰의 정보를 사용하여 유저 조회 쿼리가 나가지 않도록 개선.

- Store 좋아요 기능은 요청이 빈번하다고 판단. Redis를 활용하여 캐시로 구현.(Db와 적절히 합칠 방법을 찾아야함)