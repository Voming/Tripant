select * from plan_spot;

select * from place order by CONTENTID;

SELECT
    *
FROM
area;


desc plan_spot;

DROP TABLE "AREA_TEMP";

CREATE TABLE "AREA_TEMP" (
	"AREA_CODE"	NUMBER		NOT NULL,
	"AREA_NAME"	VARCHAR2(50)		NOT NULL,
	"AREA_SHORT_NAME"	VARCHAR2(10)		NOT NULL,
	"AREA_ENG_NAME"	VARCHAR2(50)		NOT NULL,
	"AREA_FILE_NAME"	VARCHAR2(50)		NOT NULL,
	"AREA_LANDMARK"	VARCHAR2(50)		NOT NULL,
	"AREA_X"	VARCHAR2(50)		NOT NULL,
	"AREA_Y"	VARCHAR2(50)		NOT NULL,
	"AREA_EXPLAIN"	VARCHAR2(1000)		NOT NULL,
	"AREA_FILE_SERVER"	VARCHAR2(500)		NOT NULL,
	"AREA_ADRESS"	VARCHAR2(200)		NOT NULL
);



ALTER TABLE AREA ADD AREA_FILE_SERVER VARCHAR(500); -- NOT NULL 추가

ALTER TABLE AREA ADD AREA_ADRESS VARCHAR(200); -- NOT NULL 추가

ALTER TABLE AREA MODIFY AREA_FILE_SERVER NOT NULL;
ALTER TABLE AREA MODIFY AREA_ADRESS NOT NULL;

select * from area;

--UPDATE문1
UPDATE AREA
SET 
AREA_FILE_SERVER = 'https://res.cloudinary.com/dnhmep72p/image/upload/v1720415717/Area/gsrozgidatder8k65qdt.jpg',
AREA_ADRESS = '서울 용산구 한강대로 405'
WHERE
area_code = '1';

--UPDATE문2
UPDATE AREA
SET 
AREA_FILE_SERVER = 'https://res.cloudinary.com/dnhmep72p/image/upload/v1720410623/Area/grqninut5jwscmvaeono.jpg',
AREA_ADRESS = '인천 중구 제물량로 269'
WHERE
area_code = '2';

--UPDATE문3
UPDATE AREA
SET 
AREA_FILE_SERVER = 'https://res.cloudinary.com/dnhmep72p/image/upload/v1720410624/Area/xag2z6z6xhisdluzqg6k.jpg',
AREA_ADRESS = '대전 동구 중앙로 215'
WHERE
area_code = '3';

--UPDATE문4
UPDATE AREA
SET 
AREA_FILE_SERVER = 'https://res.cloudinary.com/dnhmep72p/image/upload/v1720410625/Area/jlyil66xxojjuue8ncqa.jpg',
AREA_ADRESS = '대구 북구 태평로 161'
WHERE
area_code = '4';

--UPDATE문5
UPDATE AREA
SET 
AREA_FILE_SERVER = 'https://res.cloudinary.com/dnhmep72p/image/upload/v1720410625/Area/nyc3mvlhdeuylw2xewtz.jpg',
AREA_ADRESS = '광주 북구 무등로 235'
WHERE
area_code = '5';

--UPDATE문6
UPDATE AREA
SET 
AREA_FILE_SERVER = 'https://res.cloudinary.com/dnhmep72p/image/upload/v1720415813/Area/rdyewsgxfwvj94gtyxcf.jpg',
AREA_ADRESS = '부산 동구 중앙대로 206'
WHERE
area_code = '6';

--UPDATE문7
UPDATE AREA
SET 
AREA_FILE_SERVER = 'https://res.cloudinary.com/dnhmep72p/image/upload/v1720410623/Area/sasyv7vjv1vb9g5zkz3z.jpg',
AREA_ADRESS = '울산 울주군 삼남읍 울산역로 177'
WHERE
area_code = '7';

--UPDATE문8
UPDATE AREA
SET 
AREA_FILE_SERVER = 'https://res.cloudinary.com/dnhmep72p/image/upload/v1720410624/Area/ufll1t7jgnityleqlgsm.jpg',
AREA_ADRESS = '세종특별자치시 다솜2로 94'
WHERE
area_code = '8';

--UPDATE문31
UPDATE AREA
SET 
AREA_FILE_SERVER = 'https://res.cloudinary.com/dnhmep72p/image/upload/v1720410625/Area/un7dsap6s8mu77kl4toc.jpg',
AREA_ADRESS = '경기 수원시 팔달구 덕영대로 924'
WHERE
area_code = '31';

--UPDATE문32
UPDATE AREA
SET 
AREA_FILE_SERVER = 'https://res.cloudinary.com/dnhmep72p/image/upload/v1720410625/Area/nus2tstkj7bqx04tvjil.jpg',
AREA_ADRESS = '강원특별자치도 춘천시 공지로 591'
WHERE
area_code = '32';

--UPDATE문33
UPDATE AREA
SET 
AREA_FILE_SERVER = 'https://res.cloudinary.com/dnhmep72p/image/upload/v1720410624/Area/pu8cpibhpn6gi0n3irtu.jpg',
AREA_ADRESS = '충북 청주시 흥덕구 청주역로 363'
WHERE
area_code = '33';

--UPDATE문34
UPDATE AREA
SET 
AREA_FILE_SERVER = 'https://res.cloudinary.com/dnhmep72p/image/upload/v1720410623/Area/asnvusbt4zxuzrx8hnrg.jpg',
AREA_ADRESS = '충남 홍성군 홍성읍 아문길 27'
WHERE
area_code = '34';

--UPDATE문35
UPDATE AREA
SET 
AREA_FILE_SERVER = 'https://res.cloudinary.com/dnhmep72p/image/upload/v1720410625/Area/czzywqqwnvj9v0f1cysm.jpg',
AREA_ADRESS = '경북 안동시 퇴계로 115'
WHERE
area_code = '35';

--UPDATE문36
UPDATE AREA
SET 
AREA_FILE_SERVER = 'https://res.cloudinary.com/dnhmep72p/image/upload/v1720410625/Area/tmlc1ol01glmaemmidww.jpg',
AREA_ADRESS = '경남 창원시 성산구 중앙대로 151'
WHERE
area_code = '36';

--UPDATE문37
UPDATE AREA
SET 
AREA_FILE_SERVER = 'https://res.cloudinary.com/dnhmep72p/image/upload/v1720410623/Area/kkqgjludmd1xdjs9h8pg.jpg',
AREA_ADRESS = '전북특별자치도 전주시 완산구 노송광장로 10'
WHERE
area_code = '37';

--UPDATE문38
UPDATE AREA
SET 
AREA_FILE_SERVER = 'https://res.cloudinary.com/dnhmep72p/image/upload/v1720410624/Area/srp0tbmi6a6f7pajfnf7.jpg',
AREA_ADRESS = '전남 무안군 무안읍 무안로 530'
WHERE
area_code = '38';

--UPDATE문39
UPDATE AREA
SET 
AREA_FILE_SERVER = 'https://res.cloudinary.com/dnhmep72p/image/upload/v1720410623/Area/my0pwzeb3wugzl3p5ojk.jpg',
AREA_ADRESS = '제주특별자치도 제주시 용담이동 2002'
WHERE
area_code = '39';

SELECT
    *
FROM area;

desc area;

drop table area_temp;

SELECT * FROM AREA_TEMP;

COMMIT;