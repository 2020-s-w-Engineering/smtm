# smtm
Chungnam National University, 2020 S/W Engineering

20.10.17. 공지사항
-
1. 같은 프로젝트 폴더 안에서 따로 작업할 수 있도록
프로젝트 구조를 변경하였습니다.
<br>FRONT-END 개발 위치: `smtm/src/main/react-frontend`
<br>BACK-END 개발 위치: `smtm/src/main/java`

2. spring boot와 react를 연동하였습니다.
연동된 프로젝트를 실행하고 배포하기 위해서는 다음과 같은
준비가 필요합니다.
- maven 3.6.3(최신버전) 설치 및 환경변수 설정
- java jdk 1.8 설치 및 환경변수 설정
<br><br>연동된 프로젝트를 실행하는 방법: `...smtm/target> java -jar smtm-0.0.1-SNAPSHOT.jar`
<br><br>개발하실 때는 spring boot와 react를 따로 실행시켜서 개발하셔도 됩니다.


Q. back은 REST API만 제공하나?
<br> 예를 들어 회원가입 요청이 POST로 왔는데
<br> form에 어떤 정보가 채워지지 않은 경우
<br> 스프링만 쓸 때는 내가 직접 에러코드 심어서 redirect했는데
<br> react와 협업할 경우는 어떻게 해야하지?