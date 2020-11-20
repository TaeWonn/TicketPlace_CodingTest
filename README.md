### 티켓플레이스 코딩테스트
Java version : 15               <br/>
Spring boot : 2.3.3.RELEASE     <br/>
Port : 8081                     <br/>
DB : h2                         <br/>
DB_user : test                  <br/>
DB_password : 1234              <br/>
                                <br/>
                                <br/>
네이버 영화를 참고하여 엔티티를 작성하였으며, <br/>
JPA를 사용하여 DB와 패러다임을 맞췄습니다. <br/>

<br/>

restdoc로 api를 명세하여 알아보기 쉽게 사용했으며, <br/>
과제 형식에 가장 알맞는 h2를 사용했습니다. <br/>

<br/>

또한 status 코드 200을 제외한 코드는 json 데이터를 보내지 않습니다. <br/>
이렇게 작성한 이유는 status 코드 json 데이터가 아닌 다양한 status가 있기에 처리 할 수 있다고 생각하여 작성했습니다. <br/>

<br/>

java 15로 빌드를 했지만, 1.8 버전 이상만 유지한다면 돌아가며, <br/>
굳이 자바 15를 사용한 이유는 자바9 이상에서 적용되는 특징(gc, 메모리 관리 등)을 활용하고 <br/>
최신 버전의 jdk에 관심을 어필하기 위해서 입니다. 


### 초기화 /빌드 / 테스트

초기화 및 빌드는 파일을 압축하여 보낼 것이기 떄문에 작성하지 않습니다. <br/>
테스트는 http://localhost:8081/docs/api-guide.html 의 api가 있으며, <br/>
api에 따라 parameter 를 변경하며 테스트가 가능하고, <br/>
test 코드도 작성되어 있어 확인할 수 있습니다. 

### DB 테스트 정보
1. localhost:8081/h2-db 접속
2. JDBC URL 을 jdbc:h2:./test/h2-db 로변경
3. user : test
4. password : 1234

