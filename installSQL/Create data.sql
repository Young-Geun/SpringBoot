/* ORACLE, H2 */
-- 사용자 테이블
DROP TABLE user_info;
CREATE TABLE user_info
(
    user_id number(10, 0) NOT NULL,
    user_email varchar2(100) NOT NULL,
    user_password varchar2(255) NOT NULL,
    user_name varchar2(100) NOT NULL,
    user_status varchar2(1),

    PRIMARY KEY (user_id)
);
INSERT INTO user_info (user_id, user_email, user_password, user_name, user_status)
VALUES (1, 'younggeunn@naver.com', '1234', '최영근', 'Y');
INSERT INTO user_info (user_id, user_email, user_password, user_name, user_status)
VALUES (2, 'choi@gmail.com', '1234', '최자바', 'Y');

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
INSERT INTO messages (messages_id, send_id, recv_id, messages, send_date)
VALUES (1, 2, 1, 'hi choi~', SYSDATE);
INSERT INTO messages (messages_id, send_id, recv_id, messages, send_date)
VALUES (2, 2, 1, 'hello', SYSDATE);
INSERT INTO messages (messages_id, send_id, recv_id, messages, send_date)
VALUES (3, 1, 2, '안녕 최자바!', SYSDATE);

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
INSERT INTO board (board_id, title, contents, reg_id, reg_date)
VALUES (1, '홈페이지 오픈', '오픈했습니다', 1, SYSDATE);
INSERT INTO board (board_id, title, contents, reg_id, reg_date)
VALUES (2, '공지사항', '테스트입니다.', 1, SYSDATE);

-- TODO LIST
DROP TABLE todo_list;
CREATE TABLE todo_list
(
    todo_id number(10, 0) NOT NULL,
    contents varchar2(255) NOT NULL,
    reg_id number(10, 0) NOT NULL,
    todo_status varchar2(1),

    PRIMARY KEY (todo_id)
);
INSERT INTO todo_list (todo_id, contents, reg_id, todo_status)
VALUES (1, '커밋하기', 1, 'N');
INSERT INTO todo_list (todo_id, contents, reg_id, todo_status)
VALUES (2, '오류 고치기', 1, 'N');