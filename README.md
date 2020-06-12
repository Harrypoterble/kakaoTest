<개발프레임워크>
1. Eclipse IDE for Enterprise Java Developers. 2020-03 ver.
2. language : Java13
3. DB : H2 database embedded
4. test : JUnit 4

<문제해결전략>
1. 랜덤쿠폰번호 생성 : 자바 UUID 를 이용하여 랜덤 구현 
2. 쿠폰번호는 3개의 컬럼으로 각각 저장하여 가독성 및 추후 여러행태로 사용용이하도록
저장 
3. 목록조회 : JSON 형식 사용 
4. 만료일은 현재일과 계산하기 쉽고 테이블 쿼리로 조회시 가독성 향상을 위해 문자열 형식으로 저장 
5. 지급여부와 사용여부 기준으로 테이블 접근속도 향상을 위해 지급여부, 사용여부 컬럼으로
인덱스 생성
6. api의 추가나 변경시 쉽게 파악하고 수정하기 위해서 mainProcess.java로 전체 모듈관리

<빌드 및 실행방법>
1. h2 database 설치 후 이클립스 CouponSystem project를 project import 하여 빌드 및 실행 
2. com.coupons.test 패키지 안의 각 테스트 파일을 우클릭후 Run as 에서 Junit Test 를 클릭하여 실행 
3. 결과는 이클립스의 콘솔에서 확인가능 
4. 케이스별 테스트 소스명 
1) 랜덤한 코드의 쿠폰을 N개 생성하여 데이터베이스에 보관 : postCouponsTest.java
2) 생성된 쿠폰중 하나를 사용자에게 지급 : putCouponsPayTest.java
3) 사용자에게 지급된 쿠폰을 조회 : getCouponListPaidTest.java
4) 지급된 쿠폰중 하나를 사용 : putCouponsUseTest.java
5) 지급된 쿠폰중 하나를 사용 취소 : delCouponsTest.java
6) 발급된 쿠폰중 당일 만료된 전체 쿠폰 목록을 조회 : getCouponListExpTest.java