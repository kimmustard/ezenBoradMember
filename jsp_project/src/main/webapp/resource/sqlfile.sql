
//Board DB
create table board(
bno int not null auto_increment,
title varchar(200) not null,
writer varchar(100) not null,
content text,
regdate datetime default now(),
moddate datetime default now(),
-- 첨부파일 추가
primary key(bno));




//Member DB
create table member(
id varchar(100),
pwd varchar(100) not null,
email varchar(100) not null,
age int default 0,
regdate datetime default now(),
lastlogin datetime default now(),
primary key(id));



//Comment DB
create table comment(
cno int auto_increment,
bno int not null,
writer varchar(200) not null default "unknown",
content varchar(1000),
regdate datetime default now(),
primary key(cno)
);


-- 이미지 업로드 추가
//BoardDB
alter table board add image_File varchar(500);


