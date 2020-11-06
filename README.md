# smtm
Chungnam National University, 2020 S/W Engin

### User REST API

- 회원 인증
<br>요청 메소드: `POST`
<br>요청 URL: `/login`
<br>요청 매개변수
<br>`userId:"사용자 아이디"`
<br>`password:"사용자 비밀번호"`
<br>응답: `true` 또는 `false` (문자열)

- 회원 가입 처리
<br>요청 메소드: `POST`
<br>요청 URL: `/register`
<br>요청 매개변수
<br>`userId:"사용자 아이디"`
<br>`password:"사용자 비밀번호"`
<br>`korean:"true 또는 false"`
<br>응답: `true` 또는 `false` (문자열)

- 회원 정보 변경
<br>요청 메소드: `POST`
<br>요청 URL: `/update`
<br>요청 매개변수
<br>`id: "사용자 데이터베이스 아이디"`
<br>`userId:"사용자 아이디"`
<br>`password:"사용자 비밀번호"`
<br>`korean:"true"`
<br>응답: `true` 또는 `false` (문자열)



프론트에서 응답을 응답헤더, 응답상태 이런것까지 받을 수 있나?
그냥 문자열이 아니라 HttpEntity로 응답헤더, 응답상태 이런것까지
담아 보내면 그걸 다 받을 수 있나