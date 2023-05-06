# eventSpot_db 데이터베이스 생성
DROP DATABASE IF EXISTS eventSpot_db;
CREATE DATABASE eventSpot_db;
USE eventSpot_db;

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
    duration INT DEFAULT 0 COMMENT '단위 (분)',
    hitCount INT UNSIGNED DEFAULT 0
);

# genre 테이블 추가
CREATE TABLE genre(
    id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `name` CHAR(50)
) AUTO_INCREMENT = 1001;

# genre 테스트 데이터 생성
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

# event 테스트 데이터 생성
INSERT INTO `event`
SET regDate = NOW(),
updateDate = NOW(),
beginDt = "2023-05-06",
endDt = "2023-05-07",
genreId = 1001,
location = "대전 평송청소년문화센터 대극장",
title = "[대전] 가족 뮤지컬 <슈퍼히어로의 똥 닦는 법>",
detail = "가족 뮤지컬",
duration = 65;

INSERT INTO `event`
SET regDate = NOW(),
updateDate = NOW(),
beginDt = "2023-05-10",
endDt = "2023-05-20",
genreId = 1005,
location = "대전 예술의 전당",
title = "피카소 전시회",
detail = "전시",
duration = 30;

INSERT INTO `event`
SET regDate = NOW(),
updateDate = NOW(),
beginDt = "2023-06-05",
endDt = "2023-06-05",
genreId = 1002,
location = "충남대학교",
title = "싸이 흠뻑쇼",
detail = "콘서트",
duration = 150;

INSERT INTO `event`
SET regDate = NOW(),
updateDate = NOW(),
beginDt = "2023-07-01",
endDt = "2023-07-03",
genreId = 1003,
location = "대전 신세계백화점",
title = "김종욱찾기",
detail = "연극",
duration = 100;

SELECT * FROM `event`;
SELECT * FROM genre;