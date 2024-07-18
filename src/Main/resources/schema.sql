-- 데이터베이스 생성 (이미 있다면 사용)
CREATE DATABASE IF NOT EXISTS moviedb;

-- 데이터베이스 선택
USE moviedb;

-- 사용자 테이블 생성 (이미 존재한다면 변경하지 않음)
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    age INT
);

-- 영화 테이블 생성
CREATE TABLE IF NOT EXISTS movie (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    price INT NOT NULL,
    age_limit INT NOT NULL,
    running_time INT
);

-- 상영관 위치 테이블 생성
CREATE TABLE IF NOT EXISTS place (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20),
    addr VARCHAR(100)
);

-- 상영 정보 테이블 생성
CREATE TABLE IF NOT EXISTS screen (
    id INT AUTO_INCREMENT PRIMARY KEY,
    movie_id INT NOT NULL,
    place_id INT NOT NULL,
    start_date DATE,
    end_date DATE,
    start_time VARCHAR(100),
    FOREIGN KEY (movie_id) REFERENCES movie(id),
    FOREIGN KEY (place_id) REFERENCES place(id)
);

-- 예약 테이블 생성
CREATE TABLE IF NOT EXISTS reserve (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    movie_id INT NOT NULL,
    place_id INT NOT NULL,
    reserve_date DATE NOT NULL,
    reserve_time TIME NOT NULL,
    reserve_cnt INT NOT NULL,
    seat VARCHAR(50) NOT NULL,
    price INT,
    card_no VARCHAR(16),
    ins_dt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    delete_fg CHAR(1) DEFAULT 'N',
    del_dt TIMESTAMP NULL,
    FOREIGN KEY (username) REFERENCES users(username),
    FOREIGN KEY (movie_id) REFERENCES movie(id),
    FOREIGN KEY (place_id) REFERENCES place(id)
);

-- 데이터 삽입
-- 사용자 데이터
INSERT INTO users (username, password, age) VALUES
('admin', 'sys', 30),
('user1', 'test', 25),
('test', '123', 35);

-- 영화 데이터
INSERT INTO movie (title, price, age_limit, running_time) VALUES
('듄: 파트 2', 15000, 12, 166),
('웡카', 12000, 0, 116),
('데드맨', 12000, 15, 108),
('건국전쟁', 12000, 15, 139);

-- 상영관 위치 데이터
INSERT INTO place (name, addr) VALUES
('광주 충장로', '광주광역시 동구 충장로'),
('광주 상무', '광주광역시 서구 상무중앙로'),
('광주 용봉', '광주광역시 북구 용봉동');

-- 상영 정보 데이터
INSERT INTO screen (movie_id, place_id, start_date, end_date, start_time) VALUES
(1, 1, '2024-07-18', '2024-08-02', '11:00|14:00|17:00|20:00|23:00'),
(1, 2, '2024-07-18', '2024-08-02', '12:00|15:00'),
(1, 3, '2024-07-18', '2024-08-02', '13:00|16:00|19:00|22:00'),
(2, 1, '2024-07-16', '2024-07-31', '15:00'),
(2, 2, '2024-07-16', '2024-08-01', '12:00|17:00|22:00'),
(2, 3, '2024-07-16', '2024-08-02', '14:00|19:00');

-- 예약 데이터
INSERT INTO reserve (username, movie_id, place_id, reserve_date, reserve_time, reserve_cnt, seat, price, card_no) VALUES
('user1', 1, 1, '2024-07-18', '11:00', 4, '2,3,12,13', 60000, '1234123412341234'),
('user1', 2, 1, '2024-07-19', '15:00', 2, '1,6', 24000, '1234123412341234'),
('test', 1, 1, '2024-07-18', '11:00', 3, '7,8,9', 45000, '1234123412341234'),
('test', 3, 1, '2024-07-20', '07:00', 1, '13', 12000, '1234123412341234');

COMMIT;