# eventSpot_db 데이터베이스 생성
DROP DATABASE IF EXISTS eventSpot_db;
CREATE DATABASE eventSpot_db;
USE eventSpot_db;

###################################

# `event` 테이블 추가
CREATE TABLE `event`(
    id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    beginDt DATE NOT NULL,
    endDt DATE NOT NULL,
    genreId INT NOT NULL,
    location VARCHAR(100) NOT NULL,
    title VARCHAR(100) NOT NULL,
    detail TEXT NOT NULL,
    imgId INT NOT NULL,
    duration INT DEFAULT 0 COMMENT '단위 (분)',
    hitCount INT UNSIGNED DEFAULT 0
);

# event 테스트 데이터 생성
INSERT INTO `event`
SET regDate = NOW(),
updateDate = NOW(),
beginDt = "2023-07-01",
endDt = "2023-07-01",
genreId = 1003,
location = "충남대학교 정심화홀",
title = "김창옥 토크콘서트 시즌 3",
detail = "2023년 7월 1일 (토요일) 오후 1시, 6시",
imgId = 23,
duration = 120;

INSERT INTO `event`
SET regDate = NOW(),
updateDate = NOW(),
beginDt = "2023-05-19",
endDt = "2023-05-21",
genreId = 1002,
location = "대전예술의전당 아트홀",
title = "뮤지컬 〈캣츠〉 오리지널 내한－대전（Musical CATS）",
detail = "2023년 5월 19일(금) 오후 7시 30분",
imgId = 14,
duration = 160;

INSERT INTO `event`
SET regDate = NOW(),
updateDate = NOW(),
beginDt = "2023-06-01",
endDt = "22023-07-30",
genreId = 1001,
location = "대전 상상아트홀",
title = "NEW달을 품은 슈퍼맨",
detail = "평일 19시 30분 / 주말,공휴일 16시 (월요일 공연 없음)",
imgId = 20,
duration = 110;

INSERT INTO `event`
SET regDate = NOW(),
updateDate = NOW(),
beginDt = "2023-06-02",
endDt = "2023-06-02",
genreId = 1005,
location = "대전시립연정국악원 작은마당",
title = "피아니스트 강미연 리사이틀",
detail = "2023년 6월 2일 금요일 오후 7시 30분",
imgId = 78,
duration = 90;

###################################

# genre 테이블 추가
CREATE TABLE genre(
    id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `name` CHAR(50)
) AUTO_INCREMENT = 1001;

# genre 데이터 생성
INSERT INTO genre
SET regDate = NOW(),
updateDate = NOW(),
`name` = '기타';

INSERT INTO genre
SET regDate = NOW(),
updateDate = NOW(),
`name` = '뮤지컬';

INSERT INTO genre
SET regDate = NOW(),
updateDate = NOW(),
`name` = '콘서트';

INSERT INTO genre
SET regDate = NOW(),
updateDate = NOW(),
`name` = '연극';

INSERT INTO genre
SET regDate = NOW(),
updateDate = NOW(),
`name` = '클래식/무용';

INSERT INTO genre
SET regDate = NOW(),
updateDate = NOW(),
`name` = '전시/행사';

INSERT INTO genre
SET regDate = NOW(),
updateDate = NOW(),
`name` = '레저';


###################################

# member 테이블 추가
CREATE TABLE `member`(
    id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(20) NOT NULL UNIQUE,
    loginPw CHAR(100) NOT NULL,
    authLevel SMALLINT(2) UNSIGNED DEFAULT 2 COMMENT '권한 레벨 (2 일반, 8 관리자)',
    nickname CHAR(50) NOT NULL,
    email CHAR(50) NOT NULL UNIQUE,
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '탈퇴 여부 (0 유지, 1 탈퇴)',
    delDate DATETIME COMMENT '탈퇴 날짜'
    #CONSTRAINT check_password_length CHECK (LENGTH(loginPw) >= 6)
);

# member 테스트 데이터 생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin1',
loginPw = 'admin123',
authLevel = 7,
nickname = 'kkwo',
email = 'leeplus0414@naver.com';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test2',
loginPw = 'pwpwpw1',
nickname = '준하',
email = 'junha0414@naver.com';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test3',
loginPw = 'pwpwpw2',
nickname = '명수',
email = 'myungsu@naver.com';

###################################

CREATE TABLE reply(
    id INT

);

###################################

# eventSchedule 테이블 추가
CREATE TABLE eventSchedule(
    id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    eventId INT NOT NULL,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    eventDate DATE NOT NULL ,
    startTime TIME NOT NULL,
    endTime TIME NOT NULL
);

# eventSchedule 테스트 데이터 생성
INSERT INTO eventSchedule
SET regDate = NOW(),
updateDate = NOW(),
eventId = 1,
eventDate = '2023-05-06',
startTime = '12:00:00',
endTime = '13:30:00';

INSERT INTO eventSchedule
SET regDate = NOW(),
updateDate = NOW(),
eventId = 2,
eventDate = '2023-05-10',
startTime = '12:00:00',
endTime = '20:00:00';

INSERT INTO eventSchedule
SET regDate = NOW(),
updateDate = NOW(),
eventId = 1,
eventDate = '2023-05-06',
startTime = '12:00:00',
endTime = '13:30:00';

###################################

SELECT LAST_INSERT_ID();

SELECT * FROM `event`;
SELECT * FROM eventSchedule;
SELECT * FROM genre;
SELECT * FROM `member`;
