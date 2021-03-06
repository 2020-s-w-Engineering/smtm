# smtm
Chungnam National University, 2020 S/W Engineering

### User REST API

- 회원 인증
<br>요청 메소드: `POST`
<br>요청 URL: `/users/login`
<br>요청 매개변수
<br>`userId:"사용자 아이디"`
<br>`password:"사용자 비밀번호"`
<br>응답: `true` 또는 `false` (사용자가 한국인인지 여부)

- 회원 가입 처리
<br>요청 메소드: `POST`
<br>요청 URL: `/users/register`
<br>요청 매개변수
<br>`userId:"사용자 아이디"`
<br>`password:"사용자 비밀번호"`
<br>`korean:"true 또는 false"`
<br>응답: `true` 또는 `false` (단순 문자열)

- 회원 정보 변경
<br>요청 메소드: `POST`
<br>요청 URL: `/users/update`
<br>요청 매개변수
<br>`korean:"true"`
<br>응답: `true` 또는 `false` (단순 문자열)

- 선호도 기입
<br>요청 메소드: `POST`
<br>요청 URL: `/users/preference`
<br>요청 매개변수
<br>`korName:"메뉴 한글 이름"`
<br>`preference:"선호도(1~5)"`
<br>응답: `true` 또는 `false` (단순 문자열)

- 한 달 식단에 대한 사용자의 평균 선호도 요청
<br>요청 메소드: `GET`
<br>요청 URL: `/users/preference`
<br>응답:
```json
{
    "2020-11-06": 4.3,
    "2020-11-05": 4.42,
    "2020-11-04": 4.5
}
```

### Menu REST API

각각의 메뉴에 대한 사용자의 선호도 정보를 하루 식단 정보 요청시 같이 전달

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
                    "id": 9,
                    "korName": "연근조림",
                    "engName": "Lotus Root In Soy Sauce",
                    "img": "////",
                },
                {
                    "id": 1,
                    "korName": "김치",
                    "engName": "Kimchi",
                    "img": "////"
                },
                {
                    "id": 21,
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
                    "id": 10,
                    "korName": "씨리얼",
                    "engName": "Cereal",
                    "img": "////"
                },
                {
                    "id": 12,
                    "korName": "브라우니",
                    "engName": "Brownie",
                    "img": "////"
                },
                {
                    "id": 11,
                    "korName": "식빵과 쨈",
                    "engName": "Bread And Jam",
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
                    "id": 1,
                    "korName": "김치",
                    "engName": "Kimchi",
                    "img": "////"
                },
                {
                    "id": 17,
                    "korName": "미역줄기볶음",
                    "engName": "Sauteed Seaweed",
                    "img": "////"
                },
                {
                    "id": 21,
                    "korName": "쌀밥",
                    "engName": "Rice",
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
                    "id": 19,
                    "korName": "연두부찜",
                    "engName": "Steamed Soft Bean Curd",
                    "img": "////"
                },
                {
                    "id": 18,
                    "korName": "기장밥",
                    "engName": "Millet Rice",
                    "img": "////"
                },
                {
                    "id": 1,
                    "korName": "김치",
                    "engName": "Kimchi",
                    "img": "////"
                }
            ]
        },
        "C": {
            "calories": null,
            "menus": []
        }
    },
    "allMenus": [
        {
            "id": 10,
            "korName": "씨리얼",
            "engName": "Cereal",
            "img": "////"
        },
        {
            "id": 19,
            "korName": "연두부찜",
            "engName": "Steamed Soft Bean Curd",
            "img": "////"
        },
        {
            "id": 18,
            "korName": "기장밥",
            "engName": "Millet Rice",
            "img": "////"
        },
        {
            "id": 9,
            "korName": "연근조림",
            "engName": "Lotus Root In Soy Sauce",
            "img": "////"
        },
        {
            "id": 1,
            "korName": "김치",
            "engName": "Kimchi",
            "img": "////"
        },
        {
            "id": 12,
            "korName": "브라우니",
            "engName": "Brownie",
            "img": "////"
        },
        {
            "id": 17,
            "korName": "미역줄기볶음",
            "engName": "Sauteed Seaweed",
            "img": "////"
        },
        {
            "id": 21,
            "korName": "쌀밥",
            "engName": "Rice",
            "img": "////"
        },
        {
            "id": 11,
            "korName": "식빵과 쨈",
            "engName": "Bread And Jam",
            "img": "////"
        }
    ]
}
```
- 메뉴 이미지 요청
<br>요청 메소드: `GET`
<br>요청 URL: `/menus/images`
<br>요청 매개변수
<br>`id:{메뉴아이디}`
<br>응답 헤더
<br>`Content-Type:"image/jpeg"`
<br>`Content-Length:"{이미지의 크기}"`

- 메뉴 이미지 업로드
<br>요청 메소드: `POST`
<br>요청 URL: `/menus/images`
<br>요청 헤더
<br>`Content-Type:"multipart/form-data"`
<br>응답: `true` 또는 `false` (단순 문자열)