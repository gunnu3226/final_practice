# final_practice

## 배달 애플리케이션

### 프로젝트의 목적
- 지난기간 학습한 모든것을 최종적으로 점검하고 연습하기 위한 프로젝트
- 팀 프로젝트 진행 중 기억에 남는 튜터님들의 피드백을 모두 적용하는 목표를 가진 프로젝트
- 최대한 배운것을 모두 적용하는 프로젝트

### 스프링 심화 코드 개선 과제
#### ~~1일차. Controller, Service 패키지 내 클래스 개선~~ ✅
  - 1. ~~Controller Advice 로 예외 공통화 처리하기~~
    - https://github.com/gunnu3226/final_practice/blob/main/src/main/java/com/sparta/finalpractice/exception/ControllerAdvice.java
  - 2. ~~Service 인터페이스와 구현체 분리하여 추상화 하기~~
    - https://github.com/gunnu3226/final_practice/tree/main/src/main/java/com/sparta/finalpractice/foodReview/service

#### 2일차. CustomException 정의 및 SpringAOP 적용 
- 1. ~~CustomException 정의~~
  - https://github.com/gunnu3226/final_practice/tree/main/src/main/java/com/sparta/finalpractice/exception
- 2. Spring AOP 적용

#### 3일차. QueryDSL 을 사용하여 검색 기능 만들기
- QueryDSL 의 jpaQueryFactory 를 사용해서 검색기능을 만들어주세요!


#### ~~4일차. Pageable 을 사용하여 페이징 및 정렬 기능 만들기~~ ✅
- ~~Pageable 을 사용해서 원하는 페이지 사이즈만큼만 조회 해주세요! (JpaRepository, QueryDSL 모두)~~
  - ~~Querydsl~~
    - https://github.com/gunnu3226/final_practice/tree/main/src/main/java/com/sparta/finalpractice/store/repository
    - Store 전체 조회에서 사용해 보았는데 Store가 List<Food>를 들고있어 food까지 fetchjoin하게되면 데이터를 모두 애플리케이션 단으로 끌고와서 애플리케이션 메모리에서 페이징을 한다는 사실을 알게됨. 
    따라서 이런 경우(컬렉션을 같이 조회하는 경우) 먼저 toOne관계만 페치조인하여 받아오고, toMany는 배치 사이즈를 설정하여 지연로딩하면 쿼리 2개로 해결.
    - 단건 조회에서 1개의 스토어에 n개의 음식만 조회하도록 제한하고 싶으면 방향을 반대로하여 food에서 limit을 걸고 조회
      ```java
      public StoreResponse searchOneStoreFoodLimit(StoreSearchCond cond) {
              List<Food> foodList = jpaQueryFactory.selectFrom(food)
                  .innerJoin(food.store, store).fetchJoin()
                  .where(store.id.eq(cond.getStoreId()))
                  .limit(2)
                  .fetch();
              Store store  = foodList.get(0).getStore();
      
              return new StoreResponse(
                      store.getId(),
                      store.getName(),
                      store.getIntroduce(),
                      foodList.stream()
                          .map(FoodResponse::new)
                          .collect(Collectors.toList()));
          }
      ```
  - ~~JpaRepository~~
    - https://github.com/gunnu3226/final_practice/blob/main/src/main/java/com/sparta/finalpractice/store/service/StoreServiceImpl.java
#### 5일차. Controller 테스트 코드 작성하기
- MockMvc 를 사용해서 Controller 테스트 코드를 작성해주세요!

#### 6일차. Service 테스트 코드 작성하기
- Mockito 을 사용하여 Service 테스트 코드를 작성해주세요!

#### 7일차. Repository 테스트 코드 작성하기
- @DataJpaTest 를 사용해서 Repository 테스트 코드를 작성해주세요!

#### 8일차. AWS EC2 를 이용해 애플리케이션 .jar 파일 배포하기


### 개선한 점
- 스프링 시큐리티에서 토큰을 사용해 유저를 식별하는 내용이 강의에서 토큰을 쓸 때마다 유저 조회쿼리가 나가는것은 좋지 않다.
    > filter, jwtUtil, UserDetails, UserDetailsService 를 커스텀하여 토큰이 존재하는 동안 인증을 요청할 땐 토큰의 정보를 사용하여 유저 조회 쿼리가 나가지 않도록 개선.

- Store 좋아요 기능은 요청이 빈번하다고 판단. Redis를 활용하여 캐시로 구현.(Db와 적절히 합칠 방법을 찾아야함)