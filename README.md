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

- **김보민** : 팀장, 메인, 장소 등록, 일정 생성 및 일정 저장
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
  <summary><h3> 📌 서재원</h3></summary>
  
  ### 1. 주요 기능 및 시현
  
  ### 2. ERD

  ### 3. 플로우 차트

  ### 4. 화면설계서

  ### 5.  ClassDiagram
</details>
<details>
  <summary><h3> 📌 김보민 </h3></summary>
   
  ### 1. 주요 기능 및 시현
  
   <br>

   
  > **메인 지역 검색 및 여행기 이동**


  <div align=center>

  ![메인 지역 검색](https://github.com/user-attachments/assets/3852d891-cec2-450c-9936-65baa9680a63)
  
  </div>
 
  **주요기능 설명**
 
   *지역 검색 및 여행기 이동*
  
  - 검색어를 입력하면 해당 단어가 포함된 지역이 나타납니다.
  - 지역 클릭시 여행기로 이동하여 해당 지역에 올라온 여행기 리스트를 볼 수 있습니다. 
    
  <br>

  > **일정 만들기 시작(로그인 체크) 및 지역과 제목 입력하기**

   <div align=center>
     
   ![메인 지역 제목 입력](https://github.com/user-attachments/assets/598d7a58-5aeb-49eb-a567-ed1518620ed9)
   </div>
   
  **주요기능 설명**
 
  *지역과 제목 입력하기*
  
  - 대한민국의 17개 지역을 선택 가능합니다.
  - 지역에 따른 사진, 설명 정보가 출력됩니다.
  - 지역과 제목을 입력 받으면 일정 계속 만들기가 활성화됩니다.
    
   <br>

  > **방문 일정 입력하기**

   <div align=center>
     
   ![날짜확인-1](https://github.com/user-attachments/assets/376036bb-8581-4076-9840-7571669f9cb6)

   </div>
   <div align=center>
     
  ![날짜확인-2](https://github.com/user-attachments/assets/a43ebefb-91c1-4b37-8c24-e4ccf7487550)

   </div>
   
  **주요기능 설명**
 
  *날짜 및 일정 시작하는 시각, 일정 끝나는 시각*
  
  - 달력 모달을 통해 여행을 할 날짜를 입력 받습니다.
  - 해당 날짜마다의 시작하는 시각과 끝나는 시각을 입력 받습니다.
  - 처음이 끝나는 시각 보다 크거나 같을때는 빨간 글씨로 변하게 됩니다.
  - 하루에 2시간 이상이 되도록 입력해야 합니다.

   <br>

  > **장소 선택**
  
   <div align=center> 

  ![장소선택-1](https://github.com/user-attachments/assets/69f5bfcc-a7b6-4ba1-a6b0-d1f9aadd2ea6)
 
   </div>
   <div align=center> 

  ![장소선택-2](https://github.com/user-attachments/assets/2023d85d-68b4-49cf-a6ba-b1fe3d94fbfc)
 
   </div>
   
  **주요기능 설명**
  
  *장소 탭, 장소 추가, 장소 취소, 장소 검색, 장소 초기화, 방문 시간 설정*
  
  - 장소 탭을 통해 타입별 장소를 확인할 수 있습니다.
  - 이전에 입력한 달력 정보를 토대로 총 사용 가능한 시간을 계산하여 보여줍니다.
  - 버튼을 클릭하여 장소를 추가하고 취소 할 수 있습니다.
  - 버튼 클릭시 지도가 이동하고 마커가 생성됩니다.
  - 장소 검색이 가능합니다.
  - 장소 초기화를 누르면 입력된 장소가 모두 없어집니다.
  - 장소별 방문 시간을 설정할 수 있습니다.
  - 장소별 방문 시간 합을 계산하여 보여줍니다.

   <br>

  > **숙소 선택**


  <div align=center>
     
  ![숙소선택-1](https://github.com/user-attachments/assets/ff15f889-a146-42d6-939d-3e2e0ea7e8ce)
   
   </div>
  <div align=center>
     
 ![숙소선택-2](https://github.com/user-attachments/assets/da18c7fc-0778-4805-af0b-bcaf408986b1)
   
   </div>
 
  **주요기능 설명**
 
   *숙소 탭, 날짜별 숙소 추가, 숙소 삭제, 숙소 검색, 숙소 초기화*
  
  - 숙소 탭을 통해 타입별 장소를 확인할 수 있습니다.
  - 입력한 기간을 통하여 총 숙박할 날짜를 계산하여 보여줍니다.
  - 숙소의 버튼을 클릭하면 방문 일정 입력 모달이 발생합니다
  - 날짜별로 숙소 정보를 넣을 수 있습니다.
  - 완료를 클릭 시 숙소 정보가 저장됩니다.
  - 버튼 클릭 시 지도가 이동하고 마커가 생성됩니다.
  - 휴지통 아이콘으로 숙소를 삭제할 수 있습니다. 
  - 숙소 검색이 가능합니다.
  - 숙소 초기화를 누르면 입력된 숙소 일정이 모두 없어집니다.
  - 날짜별 입력된 숙소 정보를 계산하여 보여줍니다.
    
  <br>

  ### 2. ERD

   <img src="Readme/여행일정-ERD.png">

  ### 3. 플로우 차트

   <img src="Readme/일정-플로우차트.png">

  ### 4. 화면설계서
  <img src="Readme/1메인 페이지 로그인 이전.png">
  <img src="Readme/2메인 페이지 로그인 이후.png">
  <img src="Readme/3메인페이지.png">
  <img src="Readme/4메인페이지.png">
  <img src="Readme/5일정-지역&제목 모달.png">
  <img src="Readme/6일정-날짜 모달.png">
  <img src="Readme/7일정-날짜 확인.png">
  <img src="Readme/8일정-장소 선택.png">
  <img src="Readme/9일정-장소 방문시간 설정.png">
  <img src="Readme/10알정-숙소선택.png">
  <img src="Readme/11일정-숙소 방문일 선택.png">
  <img src="Readme/12일정-생성확인.png">
     
  ### 5.  ClassDiagram
  <img src="Readme/일정-클래스다이어그램.png">

 ### 6. 개발 이슈
  <img src="Readme/일정 개발이슈1.png">
  <img src="Readme/일정 개발이슈2.png">
  <img src="Readme/일정 개발이슈3.png">
  <img src="Readme/일정 개발이슈4.png">
  <img src="Readme/일정 개발이슈5.png">
  <img src="Readme/일정 개발이슈6.png">
  <img src="Readme/일정 개발이슈7.png">
  <img src="Readme/일정 개발이슈8.png">

</details>
<details>
  <summary><h3> 📌 김은진</h3></summary>
  
  ### 1. 주요 기능 및 시현
  
   <br>

  > **메모, 방문순서, 장소 추가 및 삭제, 머무는 시간 변경하기**

   <div align=center>
     
   ![일정편집3](https://github.com/user-attachments/assets/aad6163c-9b95-4167-8aa7-f58f7041ee20)

   </div>
   
  **주요기능 설명**
 
  *여행편집*
  
  - 사용자는 생성된 일정을 자유롭게 수정하고 커스터마이징할 수 있습니다.
  - 드래그 앤 드롭을 사용하여 방문 순서를 변경합니다.
  - 장소마다 메모를 작성하고, 장소에서 머물 시간을 변경할 수 있습니다.
  - 장소를 삭제하고 추가하여 일정을 편집하고 저장할 수 있습니다.
  - 변경된 내용은 공유자 모두에게 반영됩니다.
    
   <br>

  > **여행 일별 상세내용 보기**

   <div align=center>
     
   ![여행세부일정3](https://github.com/user-attachments/assets/added985-a5e8-42d6-9cf0-d99f3ca20795)

   </div>
   
  **주요기능 설명**
 
  *여행세부정보*
  
  - 일정을 지도상에서 시각적으로 확인할 수 있으며, 각 장소의 상세 정보를 제공합니다.
  - 각 장소 간의 이동 시간과 거리를 고려하여 현실적인 여행 일정을 제공합니다. 
  - 이동 시간은 다음 장소 도착시각에 반영됩니다.
  - 이동시간을 클릭하면 카카오맵 길찾기 창을 열어줍니다.
  - 메모아이콘에 마우스를 올리면 작성된 메모를 확인할 수 있습니다.

   <br>

  > **여행장소 추가하기**
  
   <div align=center> 

   ![장소추가3](https://github.com/user-attachments/assets/95f2ffa1-1de6-4edc-9b02-dc51f1642129)
 
   </div>
   
  **주요기능 설명**
  
  *장소추가*
  
  - 사용자는 일정을 생성할 때 빠뜨렸던 장소를 추가할 수 있습니다.
  - 검색을 하거나 태그를 통해 추가할 장소를 찾을 수 있습니다. 
  - 머무는 시간을 지정하고 드래그 앤 드롭을 통해 유저가 원하는 위치에 추가할 수 있습니다.

   <br>

  > **여행목록 확인 및 삭제, 일행 추가와 삭제**


  <div align=center>
     
  ![여행목록v](https://github.com/user-attachments/assets/5c34b5d9-774d-4fde-a31c-04fb65a231c4)
   
   </div>
 
  **주요기능 설명**
 
  *여행 및 일행 관리*
  
  - 유저가 만들거나 추가된 여행목록을 확인할 수 있습니다. 
  - 여행 생성자라면 일정에 일행을 추가하여 계획한 일정을 공유할 수 있습니다.
  - 일행은 일정 생성자가 닉네임을 통해 추가하고 삭제할 수 있습니다.
  - 삭제를 통해 나의 여행목록에서 여행을 삭제할 수 있습니다.
  - 생성자가 삭제할 경우 공유자들의 목록에서도 삭제되며 공유자일 경우 해당 유저의 목록에서만 여행이 삭제됩니다.
    
  <br>

  ### 2. ERD

   <img src="Readme/여행일정-ERD.png">

  ### 3. 플로우 차트

   <img src="Readme/여행일정_플로우차트.png">

  ### 4. 화면설계서
  <img src="Readme/화면설계서_여행목록1.png">
  <img src="Readme/화면설계서_여행목록2-공유.png">
  <img src="Readme/화면설계서_여행삭제3.png">
  <img src="Readme/화면설계서_여행일정보기4.png">
  <img src="Readme/화면설계서_여행일정-일별보기5.png">
  <img src="Readme/화면설계서_여행일정-편집6.png">
  <img src="Readme/화면설계서_여행일정-메모편집7.png">
  <img src="Readme/화면설계서_여행일정-저장8.png">
  <img src="Readme/화면설계서_여행일정-장소추가9.png">
     
  ### 5.  ClassDiagram
  <img src="Readme/여행일정_클래스다이어그램.png">

 ### 6. 개발 이슈

</details>
<details>
  <summary><h3> 📌 배소진 </h3></summary>

  ### 1. 주요 기능 및 시현

 
 <br>

> **여행기 탐색 및 지역 태그 정렬**
 <div align=center>
   
  ![tripAnt_dairy_정렬,지역 ](https://github.com/user-attachments/assets/00fb3e42-7947-4f73-84b1-57142273e84b)
 
 </div>

**주요기능 설명**
 
  *여행기 탐색*
  
  - 공개된 여행기 리스트를 전체 또는 지역별로 확인할 수 있습니다.
  - 더보기 눌러 여행기를 3개씩 더 확인 할 수 있습니다. 
  - 최신순으로 보여지며, 인기순(좋아요) 그리고 조회순으로도 볼 수 있습니다.
  - 여행기 내용 미리보기로 글  500자까지 그리고 첨부된 첫번째 사진을 보여 줍니다. 

  
 <br>
 
 >**전체공개 목록, 비공개 목록**
<div align=center>
   
  ![tripAnt_diary_목록,나의목록, 글보기](https://github.com/user-attachments/assets/d2ddb5d3-9205-41b7-8c58-27f4c85589b1)

</div>

**주요기능 설명**

*공개 설정*

- 작성한 여행기는 다른 사용자들이 볼 수 있도록 공개하거나 비공개로 설정할 수 있습니다.

*나의 여행기*

- 내가 작성한 여행기를 비공개글을 포함한 모든 글을 확인, 수정하고 삭제할 수 있습니다. 
- 여행기 미리보기가 가능하며 더보기 기능이 있어 더 많은 여행기를 미리 볼 수 있습니다.
- 공유하기 기능을 통해 SNS에 나의 여행기 링크를 업로드 할 수 있습니다.

<br>

>**글 상세보기, 신고하기, 수정하기, 공유하기**
<div align=center>
  
   ![tripAnt_diary_글보기,신고하기,수정하기,공유](https://github.com/user-attachments/assets/55833d32-9d4f-4277-aefe-31356042208c)

</div>

**주요기능 설명**

*신고하기*

- 적절하지 못한 글은 신고 할 수 있습니다.  중복 신고 할 수 없습니다.
- 게시글 신고 수 5개 이상은 게시글 삭제, 사용자 전체 게시글 신고수 합 10개 이상은 계정 사용이 정지가 됩니다. 

*좋아요, 조회수*

 - 마음에 드는 여행기는 좋아요를 남길 수 있습니다. 
 - 좋아요 수와 조회수는 관리자가 사용자에 대한 분석에 활용할 수 있습니다.

<br>

> **글 작성하기**
<div align=center>
  
  ![tripAnt_diary_write ](https://github.com/user-attachments/assets/ba1bde62-b3e7-4458-9791-b2cb41104db6)

</div>

**주요기능 설명**

*여행기 작성*

- 트립앤트에서 만든 일정으로 여행 중 경험한 일들을 글과 사진으로 기록할 수 있습니다.
- 스토어에서 구매한 폰트나 테마가 있다면 적용하여 글을 더 이쁘게 꾸밀 수 있습니다.

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

   ### 6. 개발 이슈
<img src ="Readme/여행기 게시판 -Cloudinary-0.png">
<img src ="Readme/여행기 게시판 -Cloudinary-01.png">
<img src ="Readme/여행기 게시판 -Cloudinary-02.png">
<img src ="Readme/여행기 게시판 post-01.png">
<img src ="Readme/여행기 게시판 post-02.png">
<img src ="Readme/여행기 게시판 select -01.png">
<img src ="Readme/여행기 게시판 DataAccesException -01.png">
<img src ="Readme/여행기 게시판 DataAccesException -02.png">

</details>
<details>
  <summary><h3>  📌 오예인 </h3></summary>

 ### 1. 주요 기능 및 시현

 <br>

  > **검색 기능**


  <div align=center>
    
   ![검색](https://github.com/user-attachments/assets/4a0cc34a-8d3d-422e-819b-87e1a94d671f)
   </div>
 
  **주요기능 설명**
 
  *검색*
  
  - 회원 닉네임으로 회원 검색이 가능합니다.
  - 제목과 닉네임 중 원하는 카테고리를 선택 후 검색어를 입력하면 카테고리에 맞게 검색 결과가 나옵니다.


   <br>
   
  > **정렬 기능**


  <div align=center>
     
  ![정렬 (1)](https://github.com/user-attachments/assets/76b53a3c-fc8b-4b20-ba61-d1c856ea1f2c)

   </div>
 
  **주요기능 설명**
 
  *정렬*
  
  - 정렬 버튼 클릭 시 닉네임 내림차순으로 정렬됩니다.
  - 좋아요, 조회수 많은 순으로 정렬됩니다.

  ### 2. 플로우 차트
  
<img src="Readme/관리자_플로우차트.png">

  ### 3. 화면설계서
<img src="Readme/관리자_화면설계서01.png">
<img src="Readme/관리자_화면설계서02.png">
<img src="Readme/관리자_화면설계서03.png">
<img src="Readme/관리자_화면설계서04.png">
<img src="Readme/관리자_화면설계서05.png">
<img src="Readme/관리자_화면설계서06.png">
<img src="Readme/관리자_화면설계서06.png">
<img src="Readme/관리자_화면설계서07.png">
<img src="Readme/관리자_화면설계서08.png">
<img src="Readme/관리자_화면설계서09.png">
<img src="Readme/관리자_화면설계서10.png">

  ### 4.  ClassDiagram

  <img src="Readme/관리자_ClassDiagram.png">
  
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

<https://www.erdcloud.com/d/6PaqBmMRJg9eH3AHq>

<br>

## 4.요구사항 정의서
<img src="Readme/tripAnt_요구사항정의서.png">

<https://docs.google.com/spreadsheets/d/1YjSeZamWDXXPPEuw_9O2A4YJXcJwCLBIP5fWJi4_YxA/edit?gid=470723450#gid=470723450>

<br>

## 5.플로우 차트
<img src="Readme/tripAnt_플로우 차트 전체.png">

<https://www.figma.com/board/H19KR33mtEAFC4aA1jiaS3/%ED%94%8C%EB%A1%9C%EC%9A%B0%EC%B0%A8%ED%8A%B8?node-id=0-1>
<br>

## 6. ClassDiagram

<img src="Readme/member_config.png">
<img src="Readme/trip.png">
<img src="Readme/store.png">
<img src="Readme/plan_place.png">
<img src="Readme/diary.png">
<img src="Readme/admin.png">

## 7. SequenceDiagram

<img src="Readme/SequenceDiagram.png">
