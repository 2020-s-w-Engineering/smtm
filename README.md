# smtm
Chungnam National University, 2020 S/W Engineering

### User REST API

- 회원 인증
<br>요청 메소드: `POST`
<br>요청 URL: `/users/login`
<br>요청 매개변수
<br>`userId:"사용자 아이디"`
<br>`password:"사용자 비밀번호"`
<br>응답: `true` 또는 `false` (문자열)

- 회원 가입 처리
<br>요청 메소드: `POST`
<br>요청 URL: `/users/register`
<br>요청 매개변수
<br>`userId:"사용자 아이디"`
<br>`password:"사용자 비밀번호"`
<br>`korean:"true 또는 false"`
<br>응답: `true` 또는 `false` (문자열)

- 회원 정보 변경
<br>요청 메소드: `POST`
<br>요청 URL: `/users/update`
<br>요청 매개변수
<br>`id: "사용자 데이터베이스 아이디"`
<br>`userId:"사용자 아이디"`
<br>`password:"사용자 비밀번호"`
<br>`korean:"true"`
<br>응답: `true` 또는 `false` (문자열)

### User REST API

- 하루 식단 정보
<br>요청 메소드: `GET`
<br>요청 URL: `/menus/{yyyy-MM-dd}`
<br>응답:
```json
{
    "date": "2020-11-04",
    "avgOfPreference": 0.0,
    "breakfastMains": {
        "A": {
            "calories": "781kcal",
            "menus": [
                {
                    "korName": "연근조림",
                    "engName": "Lotus Root In Soy Sauce",
                    "img": "////"
                },
                {
                    "korName": "김치",
                    "engName": "Kimchi",
                    "img": "////"
                },
                {
                    "korName": "쌀밥",
                    "engName": "Rice",
                    "img": "////"
                }
            ]
        },
        "C": {
            "calories": "709kcal",
            "menus": [
                {
                    "korName": "식빵과 쨈",
                    "engName": "Bread And Jam",
                    "img": "////"
                },
                {
                    "korName": "씨리얼",
                    "engName": "Cereal",
                    "img": "////"
                },
                {
                    "korName": "브라우니",
                    "engName": "Brownie",
                    "img": "////"
                }
            ]
        }
    },
    "lunchMains": {
        "A": {
            "calories": "806kcal",
            "menus": [
                {
                    "korName": "김치",
                    "engName": "Kimchi",
                    "img": "////"
                },
                {
                    "korName": "쌀밥",
                    "engName": "Rice",
                    "img": "////"
                },
                {
                    "korName": "미역줄기볶음",
                    "engName": "Sauteed Seaweed",
                    "img": "////"
                }
            ]
        },
        "C": {
            "calories": null,
            "menus": []
        }
    },
    "dinnerMains": {
        "A": {
            "calories": "806kcal",
            "menus": [
                {
                    "korName": "김치",
                    "engName": "Kimchi",
                    "img": "////"
                },
                {
                    "korName": "연두부찜",
                    "engName": "Steamed Soft Bean Curd",
                    "img": "////"
                },
                {
                    "korName": "기장밥",
                    "engName": "Millet Rice",
                    "img": "////"
                }
            ]
        },
        "C": {
            "calories": null,
            "menus": []
        }
    }
}
```


프론트에서 응답을 응답헤더, 응답상태 이런것까지 받을 수 있나?
그냥 문자열이 아니라 HttpEntity로 응답헤더, 응답상태 이런것까지
담아 보내면 그걸 다 받을 수 있나