-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS moviedb;

-- 데이터베이스 선택
USE moviedb;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    age INT -- 나이 컬럼 추가
);
