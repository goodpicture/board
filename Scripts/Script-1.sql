create database board;

create table member(
memberid varchar(50) not null primary key,
name varchar(50) not null,
password varchar(10) not null,
regdate datetime not null
) ENGINE=InnoDB default CHARSET=utf8;

SELECT memberid, name, password, regdate
FROM board.`member`;

insert into member(memberid,name,password,regdate) values('2','이소음','111','2017.11.11');
