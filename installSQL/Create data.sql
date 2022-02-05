-- ����� ���̺�
DROP TABLE member;

CREATE TABLE member
(
    member_id number(10, 0) NOT NULL,
    member_email varchar2(100) NOT NULL,
    member_password varchar2(255) NOT NULL,
    member_name varchar2(100) NOT NULL,
    member_status varchar2(1),

    PRIMARY KEY (member_id)
);

CREATE SEQUENCE MEMBER_SEQ
INCREMENT BY 1 --�������� 1
START WITH 1 --���ۼ��� 1
MINVALUE 1 --�ּҰ� 1
MAXVALUE 100000 --�ִ밪 1000
NOCYCLE --������������
NOCACHE; --�޸𸮿� �������� �̸��Ҵ�

INSERT INTO member (member_id, member_email, member_password, member_name, member_status)
VALUES (1, 'choi', '1', '�ֿ���', 'Y');
INSERT INTO member (member_id, member_email, member_password, member_name, member_status)
VALUES (2, 'test1@naver.com', '1', '�׽���1', 'Y');





-- �޽���
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
VALUES (1, 2, 1, 'hi choi~', SYSDATE);
INSERT INTO messages (messages_id, send_id, recv_id, messages, send_date)
VALUES (2, 2, 1, 'hello', SYSDATE);
INSERT INTO messages (messages_id, send_id, recv_id, messages, send_date)
VALUES (3, 1, 2, '�ȳ� �׽���1!', SYSDATE);





-- �Խ���
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
VALUES (1, 'Ȩ������ ����', '�����߽��ϴ�', 1, SYSDATE);
INSERT INTO board (board_id, title, contents, reg_id, reg_date)
VALUES (2, '��������', '�׽�Ʈ�Դϴ�.', 1, SYSDATE);





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
VALUES (1, 'Ŀ���ϱ�', 1, 'N');
INSERT INTO todo (todo_id, contents, reg_id, todo_status)
VALUES (2, '���� ��ġ��', 1, 'N');