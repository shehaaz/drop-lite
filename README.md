# Drop Lite
A simple Scala Swagger Playframework App

`brew install sbt`

`sbt run`


The route definitions
conf/routes

In this example **userId = 1**

**GET** http://localhost:9000/user/profile/1

**POST** http://localhost:9000/user/update
```$xslt
{
  "userId": 1,
  "points": 6091
}
```

**GET** http://localhost:9000/user/offers/1

**POST** http://localhost:9000/user/offers/1
```$xslt
{
  "id": 4,
  "merchant": "WealthSimple",
  "points": "75000",
  "offer" : "Sign up",
  "isActive": true
}
```

**GET** http://localhost:9000/user/rewards/1

**POST** http://localhost:9000/user/rewards/1
```$xslt

{
    "id": 4,
    "merchant": "Wholefoods",
    "pointsNeeded": 25000,
    "reward": "25$"
}
```

**POST** http://localhost:9000/user/redeem
```$xslt
{
    "userId": 1,
    "rewardId": 3
}
```





