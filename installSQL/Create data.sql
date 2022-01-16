/* ORACLE, H2 */
-- 사용자 테이블
CREATE TABLE user_info
(
    user_id varchar2(100) NOT NULL,
    user_password varchar2(255) NOT NULL,
    user_name varchar2(100) NOT NULL,
    user_stats varchar2(2),

    PRIMARY KEY (user_id)
);
INSERT INTO user_info (user_id, user_password, user_name, user_stats) VALUES ('choi@gmail.com', '1234', '최자바', '1');
INSERT INTO user_info (user_id, user_password, user_name, user_stats) VALUES ('younggeunn@naver.com', '1234', '최영근', '1');

-- 메시지
CREATE TABLE messages
(
    messages_id number(10, 0) NOT NULL,
    send_id varchar2(100) NOT NULL,
    recv_id varchar2(100) NOT NULL,
    messages varchar2(255),

    PRIMARY KEY (messages_id)
);
INSERT INTO messages (messages_id, send_id, recv_id, messages) VALUES (1, 'choi@gmail.com', 'younggeunn@naver.com', 'hello_1');
INSERT INTO messages (messages_id, send_id, recv_id, messages) VALUES (2, 'choi@gmail.com', 'younggeunn@naver.com', 'hello_2');
INSERT INTO messages (messages_id, send_id, recv_id, messages) VALUES (3, 'choi@gmail.com', 'younggeunn@naver.com', 'hello_3');

-- 게시판
CREATE TABLE board
(
    board_id number(10, 0) NOT NULL,
    title varchar2(100) NOT NULL,
    contents varchar2(255) NOT NULL,
    reg_id varchar2(100) NOT NULL,
    reg_date varchar2(14),
    noti_flag varchar2(1),

    PRIMARY KEY (board_id)
);
INSERT INTO board (board_id, title, contents, reg_id, reg_date, noti_flag) VALUES (1, '공지사항 1', '내용입니다.', 'younggeunn@naver.com', '20220116105100', 'Y');
INSERT INTO board (board_id, title, contents, reg_id, reg_date, noti_flag) VALUES (2, '공지사항 2', '내용입니다.', 'younggeunn@naver.com', '20220117105100', 'Y');

-- TODO LIST
CREATE TABLE todo_list
(
    todo_id number(10, 0) NOT NULL,
    contents varchar2(255) NOT NULL,
    reg_id varchar2(100) NOT NULL,
    complete_flag varchar2(1),

    PRIMARY KEY (todo_id)
);
INSERT INTO todo_list (todo_id, contents, reg_id, complete_flag) VALUES (1, 'commit하기', 'younggeunn@naver.com', 'Y');
INSERT INTO todo_list (todo_id, contents, reg_id, complete_flag) VALUES (2, '책 구매', 'younggeunn@naver.com', 'N');






/* MySQL */
-- 사용자 테이블
CREATE TABLE user_info (
                           user_id varchar(100) NOT NULL,
                           user_password varchar(255) NOT NULL,
                           user_name varchar(100) NOT NULL,
                           user_stats varchar(2),
                           PRIMARY KEY (user_id)
);
INSERT INTO user_info (user_id, user_password, user_name, user_stats) VALUES ('choi@gmail.com', '1234', '최자바', '1');
INSERT INTO user_info (user_id, user_password, user_name, user_stats) VALUES ('younggeunn@naver.com', '1234', '최영근', '1');

-- 메시지
CREATE TABLE messages
(
    messages_id int(10) NOT NULL,
    send_id varchar(100) NOT NULL,
    recv_id varchar(100) NOT NULL,
    messages varchar(255),

    PRIMARY KEY (messages_id)
);
INSERT INTO messages (messages_id, send_id, recv_id, messages) VALUES (1, 'choi@gmail.com', 'younggeunn@naver.com', 'hello_1');
INSERT INTO messages (messages_id, send_id, recv_id, messages) VALUES (2, 'choi@gmail.com', 'younggeunn@naver.com', 'hello_2');
INSERT INTO messages (messages_id, send_id, recv_id, messages) VALUES (3, 'choi@gmail.com', 'younggeunn@naver.com', 'hello_3');

-- 게시판
CREATE TABLE board
(
    board_id int(10) NOT NULL,
    title varchar(100) NOT NULL,
    contents varchar(255) NOT NULL,
    reg_id varchar(100) NOT NULL,
    reg_date varchar(14),
    noti_flag varchar(1),

    PRIMARY KEY (board_id)
);
INSERT INTO board (board_id, title, contents, reg_id, reg_date, noti_flag) VALUES (1, '공지사항 1', '내용입니다.', 'younggeunn@naver.com', '20220116105100', 'Y');
INSERT INTO board (board_id, title, contents, reg_id, reg_date, noti_flag) VALUES (2, '공지사항 2', '내용입니다.', 'younggeunn@naver.com', '20220117105100', 'Y');

-- TODO LIST
CREATE TABLE todo_list
(
    todo_id int(10) NOT NULL,
    contents varchar(255) NOT NULL,
    reg_id varchar(100) NOT NULL,
    complete_flag varchar(1),

    PRIMARY KEY (todo_id)
);
INSERT INTO todo_list (todo_id, contents, reg_id, complete_flag) VALUES (1, 'commit하기', 'younggeunn@naver.com', 'Y');
INSERT INTO todo_list (todo_id, contents, reg_id, complete_flag) VALUES (2, '책 구매', 'younggeunn@naver.com', 'N');