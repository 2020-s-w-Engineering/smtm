# REST API

/users: 모든 사용자 정보 요청
<br>method: GET
<br>params: -

POST /users: 사용자 추가
<br>method: POST
<br>params: userId:{userId}, password:{password}, isKorean:{isKorean}


/users/{id}: 해당 id의 사용자 정보 요청(이때 id는 db 구분자로써 id)
<br>method: GET
<br>params: -