-- 사용자 테이블
DROP TABLE member;

CREATE TABLE member
(
    member_id number(10, 0) NOT NULL,
    member_email varchar2(100) NOT NULL,
    member_password varchar2(255) NOT NULL,
    member_name varchar2(100) NOT NULL,
    member_profile varchar2(100),
    member_status varchar2(1),
    last_login_date date,

    PRIMARY KEY (member_id)
);

CREATE SEQUENCE MEMBER_SEQ
INCREMENT BY 1 --증감숫자 1
START WITH 1 --시작숫자 1
MINVALUE 1 --최소값 1
MAXVALUE 100000 --최대값 1000
NOCYCLE --순한하지않음
NOCACHE; --메모리에 시퀀스값 미리할당

INSERT INTO member (member_id, member_email, member_password, member_name, member_status)
VALUES (MEMBER_SEQ.nextval, 'choi@naver.com', '1', '최영근', 'Y');
INSERT INTO member (member_id, member_email, member_password, member_name, member_status)
VALUES (MEMBER_SEQ.nextval, 'test1@naver.com', '1', '테스터1', 'Y');





-- 메시지
DROP TABLE messages;

CREATE TABLE messages
(
    messages_id number(10, 0) NOT NULL,
    send_id number(10, 0) NOT NULL,
    recv_id number(10, 0) NOT NULL,
    messages varchar2(255),
    send_date date,

    PRIMARY KEY (messages_id)
);

CREATE SEQUENCE MESSAGES_SEQ
INCREMENT BY 1
START WITH 1
MINVALUE 1
MAXVALUE 100000
NOCYCLE
NOCACHE;

INSERT INTO messages (messages_id, send_id, recv_id, messages, send_date)
VALUES (MESSAGES_SEQ.nextval, 2, 1, 'hi choi~', SYSDATE);
INSERT INTO messages (messages_id, send_id, recv_id, messages, send_date)
VALUES (MESSAGES_SEQ.nextval, 2, 1, 'hello', SYSDATE);
INSERT INTO messages (messages_id, send_id, recv_id, messages, send_date)
VALUES (MESSAGES_SEQ.nextval, 1, 2, '안녕 테스터1!', SYSDATE);





-- 게시판
DROP TABLE board;

CREATE TABLE board
(
    board_id number(10, 0) NOT NULL,
    title varchar2(100) NOT NULL,
    contents varchar2(255) NOT NULL,
    reg_id number(10, 0) NOT NULL,
    reg_date date,

    PRIMARY KEY (board_id)
);

CREATE SEQUENCE BOARD_SEQ
INCREMENT BY 1
START WITH 1
MINVALUE 1
MAXVALUE 100000
NOCYCLE
NOCACHE;

INSERT INTO board (board_id, title, contents, reg_id, reg_date)
VALUES (BOARD_SEQ.nextval, '홈페이지 오픈', '오픈했습니다', 1, SYSDATE);
INSERT INTO board (board_id, title, contents, reg_id, reg_date)
VALUES (BOARD_SEQ.nextval, '공지사항', '테스트입니다.', 1, SYSDATE);





-- TODO LIST
DROP TABLE todo;

CREATE TABLE todo
(
    todo_id number(10, 0) NOT NULL,
    contents varchar2(255) NOT NULL,
    reg_id number(10, 0) NOT NULL,
    todo_status varchar2(1),

    PRIMARY KEY (todo_id)
);

CREATE SEQUENCE TODO_SEQ
INCREMENT BY 1
START WITH 1
MINVALUE 1
MAXVALUE 100000
NOCYCLE
NOCACHE;

INSERT INTO todo (todo_id, contents, reg_id, todo_status)
VALUES (TODO_SEQ.nextval, '커밋하기', 1, 'N');
INSERT INTO todo (todo_id, contents, reg_id, todo_status)
VALUES (TODO_SEQ.nextval, '오류 고치기', 1, 'N');





-- 접근이력 테이블
DROP TABLE access_history;

CREATE TABLE access_history
(
    access_id number(10, 0) NOT NULL,
    access_member_id number(10, 0) NOT NULL,
    access_path varchar2(255) NOT NULL,
    access_date date,

    PRIMARY KEY (access_id)
);

CREATE SEQUENCE access_history_seq
    INCREMENT BY 1 --증감숫자 1
    START WITH 1 --시작숫자 1
    MINVALUE 1 --최소값 1
    MAXVALUE 100000 --최대값 1000
    NOCYCLE --순한하지않음
NOCACHE; --메모리에 시퀀스값 미리할당