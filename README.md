# Tripant
----
트립앤트 
KH 정보 교육원 자바공공데이터 융합과정 FINAL_PROJECT
<img src="Readme/tripant_1.png">


##  1️⃣프로젝트 소개
  트립앤트는 사용자들이 여행계획을 쉽게 세우고 여행 후기를 기록할 수 있는 여행 기록용 웹 사이트 입니다.
 여행할 날짜와 방문하고 싶은 장소를 입력하면 시간과 거리를 고려하여 최적의 일정을 제공하며, 여행기를 작성하여 다른 사람들과 공유하거나 개인적으로 보관할 수 있습니다. 또한, 사용자가 여행기를 더욱 개성 있게 꾸밀 수 있도록 다양한 폰트와 테마를 제공하는 스토어 기능을 갖추고 있습니다.

  관리자 페이지로 회원 관리가 가능하며 전체 게시글 관리로 좋아요 수와 조회수를 확인할 수 있습니다.

## 2️⃣ 개발자 소개
<img src="Readme/tripant_개발자소개_2.png">

- **김보민** : 팀장, 일정 생성 및 일정 저징
- **김은진** : 팀원, 여행 목록, 일정 편집
- **배소진** : 팀원, 여행기, 나의 여행기 
- **서재원** : 팀원, 로그인 및 회원가입, 스토어
- **오예인** : 팀원, 관리자 


## 3️⃣ 개발환경
<img src="Readme/tripant_개발환경_3.png">

- **사용언어** : Java, HTML5, CSS3, JavaScript
- **DB** :  OracleDB, PL/SQL, JOB 
- **Tool** : STS4, VSCode, Sql Developer,Chrome Developer Tool, ERD-cloud, draw.io, figma, IntelliJ 
- **WAS** :  Spring Boot 3 내장 Tomcat
- **Collaboration** : GitHub, Github Desktop,Notion,Google Drive/Docs
- **FrameWork/ Library** : Thymeleaf, JQuery, ckEditor(wysiwyg),FullCalendar, Date Picker, Spring Boot 3, lombok, Spring Security,Oauth2,MyBatis,Cloudinary(File Storage),aspectj-weaver, log4jdbc, log4j2, Jackson, Gson, Document
- **API** : 한국관광공사_국문 관광정보 서비스_GW, Kakao Mobility Developers길찾기 API,Kakao Postcode API

## 4️⃣ 기능 구현 / 화면
<details>
  <summary><h3>서재원</h3></summary>
  
  ### 1. 주요 기능 및 시현
  
  ### 2. ERD

  ### 3. 플로우 차트

  ### 4. 화면설계서

  ### 5.  ClassDiagram
</details>
<details>
  <summary><h3>김보민</h3></summary>
</details>
<details>
  <summary><h3>김은진</h3></summary>
</details>
<details>
  <summary><h3>배소진</h3></summary>

  ### 1. 주요 기능 및 시현
  
 **<p>여행기 주요 기능 및 시현 </p>** 
 
 <br>

**<h3> 1. 여행기 탐색 및 지역 태그 정렬 </h3>**
 <div align=center>
   
  ![tripAnt_dairy_정렬,지역 ](https://github.com/user-attachments/assets/00fb3e42-7947-4f73-84b1-57142273e84b)
 
 </div>

**주요기능 설명**
* 여행기 탐색
 공개된 여행기 리스트를 전체 또는 지역별로 확인할 수 있습니다.
 더보기 눌러 여행기를 3개씩 더 확인 할 수 있습니다. 
 최신순으로 보여지며, 인기순(좋아요) 그리고 조회순으로도 볼 수 있습니다
 여행기 내용 미리보기로 글  500자까지 그리고 첨부된 첫번째 사진을 보여 줍니다.
  
 <br>
 
 **<h3> 2. 전체공개 목록, 비공개 목록  </h3>**
<div align=center>
   
  ![tripAnt_diary_목록,나의목록, 글보기](https://github.com/user-attachments/assets/d2ddb5d3-9205-41b7-8c58-27f4c85589b1)

</div>

**주요기능 설명**
*공개 설정
작성한 여행기는 다른 사용자들이 볼 수 있도록 공개하거나 비공개로 설정할 수 있습니다.

* 나의 여행기
 내가 작성한 여행기를 비공개글을 포함한 모든 글을 확인, 수정하고 삭제할 수 있습니다. 
 여행기 미리보기가 가능하며 더보기 기능이 있어 더 많은 여행기를 미리 볼 수 있습니다.
 공유하기 기능을 통해 SNS에 나의 여행기 링크를 업로드 할 수 있습니다

<br>

**<h3> 3. 글 상세보기, 신고하기, 수정하기, 공유하기  </h3>**
<div align=center>
  
   ![tripAnt_diary_글보기,신고하기,수정하기,공유](https://github.com/user-attachments/assets/55833d32-9d4f-4277-aefe-31356042208c)

</div>

**주요기능 설명**
* 신고하기 
적절하지 못한 글은 신고 할 수 있습니다.  중복 신고 할 수 없습니다.
게시글 신고 수 5개 이상은 게시글 삭제, 사용자 전체 게시글 신고수 합 10개 이상은 계정 사용이 정지가 됩니다. 

* 좋아요, 조회수
마음에 드는 여행기는 좋아요를 남길 수 있습니다. 
좋아요 수와 조회수는 관리자가 사용자에 대한 분석에 활용할 수 있습니다.

<br>

 **<h3> 4. 글 작성하기  </h3>**
<div align=center>
  
  ![tripAnt_diary_write ](https://github.com/user-attachments/assets/ba1bde62-b3e7-4458-9791-b2cb41104db6)

</div>

**주요기능 설명**
* 여행기 작성
 트립앤트에서 만든 일정으로 여행 중 경험한 일들을 글과 사진으로 기록할 수 있습니다.
 스토어에서 구매한 폰트나 테마가 있다면 적용하여 글을 더 이쁘게 꾸밀 수 있습니다.

<br>

  ### 2. ERD

  <img src="Readme/여행기_ERD_21.png">

  ### 3. 플로우 차트

  <img src="Readme/여행기_플로우차트_14.png">

  ### 4. 화면설계서
  
 <img src="Readme/여행기_화면설계서01_.png">
  <img src="Readme/여행기_화면설계서02_.png">
 <img src="Readme/여행기_화면설계서03_.png">
 <img src="Readme/여행기_화면설계서04_.png">
 <img src="Readme/여행기_화면설계서05_.png">
 <img src="Readme/여행기_화면설계서06_.png">
 <img src="Readme/여행기_화면설계서07_.png">
 <img src="Readme/여행기_화면설계서08_.png">
 <img src="Readme/여행기_화면설계서08_01_.png">
 <img src="Readme/여행기_화면설계서09_.png">
 <img src="Readme/여행기_화면설계서09_01_.png">
 <img src="Readme/여행기_화면설계서10_.png">
     <img src="Readme/여행기_화면설계서10_01_.png">
     
  ### 5.  ClassDiagram
   <img src="Readme/여행기_ClassDiagram.png">
   
</details>
<details>
  <summary><h3>오예인</h3></summary>
</details>


## 5️⃣ Document

## 1. 개발일정
<img src="Readme/tripAnt_개발일정.png">

<br>

## 2. 정보구조도
<img src="Readme/tripAnt_정보구조도.png">

<https://www.figma.com/board/b4Op7qiCo54hgTa3ZvGFag/%EA%B8%B0%ED%9A%8D?node-id=0-1&t=IgwNAPOdrCnXxUeR-0>

<br>

## 3. ERD
<img src="Readme/tripAnt_ERD전체.png">

<br>

## 4.요구사항 정의서
<img src="Readme/tripAnt_요구사항정의서.png">

<https://docs.google.com/spreadsheets/d/1YjSeZamWDXXPPEuw_9O2A4YJXcJwCLBIP5fWJi4_YxA/edit?gid=470723450#gid=470723450>

<br>

## 5.플로우 차트
<img src="Readme/tripAnt_플로우 차트 전체.png">

<https://www.figma.com/board/H19KR33mtEAFC4aA1jiaS3/%ED%94%8C%EB%A1%9C%EC%9A%B0%EC%B0%A8%ED%8A%B8?node-id=0-1>
<br>

## 6. 화면설계서

<br>

## 7. ClassDiagram
<img src="Readme/tripAnt_ClassDiagram.png">


