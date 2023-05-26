# event_scheduler_DB 데이터베이스 생성
DROP DATABASE IF EXISTS event_scheduler_DB;
CREATE DATABASE event_scheduler_DB;
USE event_scheduler_DB;

###################################

# TB_EVENT 테이블 추가
CREATE TABLE TB_EVENT(
    id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    genreId INT NOT NULL,
    location VARCHAR(100) NOT NULL,
    title VARCHAR(100) NOT NULL,
    detail TEXT NOT NULL,
    duration INT DEFAULT 0 COMMENT '단위 (분)'
);

# TB_EVENT 테스트 데이터 생성
INSERT INTO TB_EVENT
SET regDate = NOW(),
updateDate = NOW(),
genreId = 1003,
location = "충남대학교 정심화홀",
title = "김창옥 토크콘서트 시즌 3",
detail = "2023년 7월 1일 (토요일) 오후 1시, 6시",
duration = 120;

INSERT INTO TB_EVENT
SET regDate = NOW(),
updateDate = NOW(),
genreId = 1002,
location = "대전예술의전당 아트홀",
title = "뮤지컬 〈캣츠〉 오리지널 내한－대전（Musical CATS）",
detail = "2023년 5월 19일(금) 오후 7시 30분",
duration = 160;

INSERT INTO TB_EVENT
SET regDate = NOW(),
updateDate = NOW(),
genreId = 1001,
location = "대전 상상아트홀",
title = "NEW달을 품은 슈퍼맨",
detail = "평일 19시 30분 / 주말,공휴일 16시 (월요일 공연 없음)",
duration = 110;

INSERT INTO TB_EVENT
SET regDate = NOW(),
updateDate = NOW(),
genreId = 1005,
location = "대전시립연정국악원 작은마당",
title = "피아니스트 강미연 리사이틀",
detail = "2023년 6월 2일 금요일 오후 7시 30분",
duration = 90;

INSERT INTO TB_EVENT
SET regDate = NOW(),
updateDate = NOW(),
genreId = 1005,
location = "대전예술의전당 아트홀",
title = "2023 일리야 라쉬코프스키, 라흐마니노프 3개의 피아노협주곡",
detail = "2023년 9월 2일(토) 오후 5시",
duration = 150;

INSERT INTO TB_EVENT
SET regDate = NOW(),
updateDate = NOW(),
genreId = 1005,
location = "대전예술의전당 아트홀",
title = "대전시립무용단 제73회 정기공연 춤극 ＂로미오와 줄리엣－유성과 예랑＂",
detail = "2023년 6월 2일 금 19시 30분
2023년 6월 3일 토 17시",
duration = 70;

###################################

# TB_GENRE 테이블 추가
CREATE TABLE TB_GENRE(
    id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `name` CHAR(50)
) AUTO_INCREMENT = 1001;

# TB_GENRE 데이터 생성
INSERT INTO TB_GENRE
SET regDate = NOW(),
updateDate = NOW(),
`name` = '기타';

INSERT INTO TB_GENRE
SET regDate = NOW(),
updateDate = NOW(),
`name` = '뮤지컬';

INSERT INTO TB_GENRE
SET regDate = NOW(),
updateDate = NOW(),
`name` = '콘서트';

INSERT INTO TB_GENRE
SET regDate = NOW(),
updateDate = NOW(),
`name` = '연극';

INSERT INTO TB_GENRE
SET regDate = NOW(),
updateDate = NOW(),
`name` = '클래식/무용';

INSERT INTO TB_GENRE
SET regDate = NOW(),
updateDate = NOW(),
`name` = '전시/행사';

INSERT INTO TB_GENRE
SET regDate = NOW(),
updateDate = NOW(),
`name` = '레저';


###################################

# TB_MEMBER 테이블 추가
CREATE TABLE TB_MEMBER(
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

# TB_MEMBER 테스트 데이터 생성
INSERT INTO TB_MEMBER
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin1',
loginPw = 'admin123',
authLevel = 7,
nickname = 'kkwo',
email = 'kkwo@naver.com';

INSERT INTO TB_MEMBER
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test2',
loginPw = 'test2',
nickname = 'java001',
email = 'java0414@naver.com';

INSERT INTO TB_MEMBER
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'test3',
loginPw = 'test3',
nickname = 'spring404',
email = 'spring@naver.com';

# TB_SCHEDULE 테이블 추가
CREATE TABLE TB_SCHEDULE(
    id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    eventId INT NOT NULL,
    eventDate DATETIME NOT NULL
);

# TB_SCHEDULE 테스트 데이터 생성
INSERT INTO TB_SCHEDULE
SET regDate = NOW(),
updateDate = NOW(),
eventId = 1,
eventDate = '2023-05-06 13:30:00';

INSERT INTO TB_SCHEDULE
SET regDate = NOW(),
updateDate = NOW(),
eventId = 2,
eventDate = '2023-05-10 16:00:00';

INSERT INTO TB_SCHEDULE
SET regDate = NOW(),
updateDate = NOW(),
eventId = 1,
eventDate = '2023-05-06 17:30:00';

###################################

# TB_REPLY 테이블 생성
CREATE TABLE TB_REPLY(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    relTypeCode CHAR(50) NOT NULL COMMENT '관련 데이터 타입 코드',
    relId INT(10) NOT NULL COMMENT '관련 데이터 번호',
    `body` TEXT NOT NULL
);

# reply 테스트 데이터
# 2번 회원이 2번 글에 댓글
INSERT INTO TB_REPLY
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
relTypeCode = 'event',
relId = FLOOR(1 + RAND() * 6),
`body` = CONCAT(SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', FLOOR(1 + RAND() * 52), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1));

# 2번 회원이 2번 글에 댓글
INSERT INTO TB_REPLY
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
relTypeCode = 'event',
relId = FLOOR(1 + RAND() * 6),
`body` = CONCAT(SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', FLOOR(1 + RAND() * 52), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1));

# 3번 회원이 2번 글에 댓글
INSERT INTO TB_REPLY
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
relTypeCode = 'event',
relId = FLOOR(1 + RAND() * 6),
`body` = CONCAT(SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', FLOOR(1 + RAND() * 52), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1));

# 3번 회원이 3번 글에 댓글
INSERT INTO TB_REPLY
SET regDate = NOW(),
updateDate = NOW(),
memberId = 3,
relTypeCode = 'event',
relId = FLOOR(1 + RAND() * 6),
`body` = CONCAT(SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ', FLOOR(1 + RAND() * 52), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1),
                SUBSTRING('abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', FLOOR(1 + RAND() * 62), 1));


CREATE TABLE TB_BOOKMARK (
	id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    eventId INT(10) NOT NULL COMMENT '관련 이벤트 번호'
);

INSERT INTO TB_BOOKMARK
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
eventId = 2;

INSERT INTO TB_BOOKMARK
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
eventId = 3;

INSERT INTO TB_BOOKMARK
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
eventId = 5;


###################################

###################################

SELECT LAST_INSERT_ID();

SELECT * FROM TB_EVENT;
SELECT * FROM TB_SCHEDULE;
SELECT * FROM TB_GENRE;
SELECT * FROM TB_MEMBER;
SELECT * FROM TB_REPLY;
SELECT * FROM TB_COLLECTION;

# 먼저 일어나
SELECT * FROM TB_SCHEDULE
ORDER BY eventDate, startTime;