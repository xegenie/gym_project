/* 유저 추가 */

/* 1번 사용자 */
INSERT INTO users (id, password, name, phone, gender, email, birth, enabled, question, answer)
VALUES ('john123', 'securePass123!', '김영수', '01012345678', 'M', 'john.doe@example.com', '1990-01-15', true, 'What is your favorite color?', 'Blue');
SET @userNo = LAST_INSERT_ID();
INSERT INTO user_auth (user_no, auth)
VALUES (@userNo, 'ROLE_USER');

/* 2번 사용자 */
INSERT INTO users (id, password, name, phone, gender, email, birth, enabled, question, answer)
VALUES ('jane456', 'passJane@456', '이수진', '01098765432', 'F', 'jane.smith@example.com', '1985-05-20', true, 'What is your mother’s maiden name?', 'Johnson');
SET @userNo = LAST_INSERT_ID();
INSERT INTO user_auth (user_no, auth)
VALUES (@userNo, 'ROLE_USER');

/* 3번 사용자 */
INSERT INTO users (id, password, name, phone, gender, email, birth, enabled, question, answer)
VALUES ('alex2023', 'Alex!Secure2023', '박철수', '01056781234', 'M', 'alex.brown@example.com', '2000-12-10', true, 'What was your first pet’s name?', 'Buddy');
SET @userNo = LAST_INSERT_ID();
INSERT INTO user_auth (user_no, auth)
VALUES (@userNo, 'ROLE_USER');

/* 4번 사용자 */
INSERT INTO users (id, password, name, phone, gender, email, birth, enabled, question, answer)
VALUES ('sara789', 'Sara_789$pass', '최은영', '01034567890', 'F', 'sara.lee@example.com', '1995-07-25', true, 'What city were you born in?', 'Seoul');
SET @userNo = LAST_INSERT_ID();
INSERT INTO user_auth (user_no, auth)
VALUES (@userNo, 'ROLE_USER');

/* 5번 사용자 */
INSERT INTO users (id, password, name, phone, gender, email, birth, enabled, question, answer)
VALUES ('chris888', 'Chris#888Pass', '정민호', '01087654321', 'M', 'chris.johnson@example.com', '1988-03-30', true, 'What is your favorite food?', 'Pizza');
SET @userNo = LAST_INSERT_ID();
INSERT INTO user_auth (user_no, auth)
VALUES (@userNo, 'ROLE_USER');

/* 6번 사용자 */
INSERT INTO users (id, password, name, phone, gender, email, birth, enabled, question, answer)
VALUES ('lisa101', 'Lisa$101pass', '김수정', '01011112222', 'F', 'lisa.kim@example.com', '1992-06-12', true, 'What was the name of your first school?', 'Sunny School');
SET @userNo = LAST_INSERT_ID();
INSERT INTO user_auth (user_no, auth)
VALUES (@userNo, 'ROLE_USER');

/* 7번 사용자 */
INSERT INTO users (id, password, name, phone, gender, email, birth, enabled, question, answer)
VALUES ('daniel777', 'Daniel!777pass', '박준형', '01033334444', 'M', 'daniel.park@example.com', '1998-02-18', true, 'What is your dream job?', 'Pilot');
SET @userNo = LAST_INSERT_ID();
INSERT INTO user_auth (user_no, auth)
VALUES (@userNo, 'ROLE_USER');

/* 8번 사용자 */
INSERT INTO users (id, password, name, phone, gender, email, birth, enabled, question, answer)
VALUES ('emma2024', 'Emma#2024$', '이지혜', '01055556666', 'F', 'emma.lee@example.com', '1993-09-09', true, 'What is your father’s middle name?', 'Michael');
SET @userNo = LAST_INSERT_ID();
INSERT INTO user_auth (user_no, auth)
VALUES (@userNo, 'ROLE_USER');

/* 9번 사용자 */
INSERT INTO users (id, password, name, phone, gender, email, birth, enabled, question, answer)
VALUES ('mike999', 'Mike@999pass', '최성훈', '01077778888', 'M', 'mike.choi@example.com', '1991-11-11', true, 'What was the model of your first car?', 'Civic');
SET @userNo = LAST_INSERT_ID();
INSERT INTO user_auth (user_no, auth)
VALUES (@userNo, 'ROLE_USER');

/* 10번 사용자 */
INSERT INTO users (id, password, name, phone, gender, email, birth, enabled, question, answer)
VALUES ('nina555', 'Nina#555pass', '강은주', '01099990000', 'F', 'nina.kang@example.com', '1996-03-03', true, 'Who was your childhood hero?', 'Wonder Woman');
SET @userNo = LAST_INSERT_ID();
INSERT INTO user_auth (user_no, auth)
VALUES (@userNo, 'ROLE_USER');






/* 기본 출석 횟수 세팅 */
INSERT INTO
    `attendance` (
        `qr_id`,
        `user_no`,
        `check_time`
    )
VALUES ('QR112345', 10, NOW()),
    ('QR122346', 10, NOW()),
    ('QR123347', 10, NOW()),
    ('QR124348', 10, NOW()),
    ('QR152349', 10, NOW()),
    ('QR162350', 10, NOW()),
    ('QR172351', 10, NOW()),
    ('QR120354', 10, NOW());

    INSERT INTO
    `attendance` (
        `qr_id`,
        `user_no`,
        `check_time`
    )
VALUES ('QR12345', 2, NOW()),
    ('QR1112346', 2, NOW()),
    ('QR1231147', 2, NOW()),
    ('QR1232251', 2, NOW()),
    ('QR1233352', 2, NOW()),
    ('QR1442353', 2, NOW()),
    ('QR1552354', 2, NOW());

    INSERT INTO
    `attendance` (
        `qr_id`,
        `user_no`,
        `check_time`
    )
VALUES ('QR1dsfdsfsd2345', 3, NOW()),
    ('QR11232346', 3, NOW()),
    ('QR12342137', 3, NOW()),
    ('QR12213348', 3, NOW()),
    ('QR143432349', 3, NOW()),
    ('QR1742354', 3, NOW());

    INSERT INTO
    `attendance` (
        `qr_id`,
        `user_no`,
        `check_time`
    )
VALUES ('QR14235235232345', 4, NOW()),
    ('QR12342342351', 4, NOW()),
    ('QR2342312352', 4, NOW()),
    ('QR12242356353', 4, NOW()),
    ('QR123422354', 4, NOW());

    INSERT INTO
    `attendance` (
        `qr_id`,
        `user_no`,
        `check_time`
    )
VALUES ('QR1243634622132345', 5, NOW()),
    ('QR123743352', 5, NOW()),
    ('QR12332453', 5, NOW()),
    ('QR1623354', 5, NOW());

    INSERT INTO
    `attendance` (
        `qr_id`,
        `user_no`,
        `check_time`
    )
VALUES ('QR342352212345', 6, NOW()),
    ('QR1252622353', 6, NOW()),
    ('QR1223423354', 6, NOW());

    INSERT INTO
    `attendance` (
        `qr_id`,
        `user_no`,
        `check_time`
    )
VALUES ('QR122342356345', 7, NOW()),
    ('QR189872346', 7, NOW()),
    ('QR123779747', 7, NOW()),
    ('QR12889876348', 7, NOW()),
    ('QR1288987246348', 7, NOW()),
    ('QR124747349', 7, NOW()),
    ('QR12242350', 7, NOW()),
    ('QR12242351', 7, NOW()),
    ('QR12142352', 7, NOW()),
    ('QR1263346353', 7, NOW()),
    ('QR12214264354', 7, NOW());

    INSERT INTO
    `attendance` (
        `qr_id`,
        `user_no`,
        `check_time`
    )
VALUES ('QR12452345', 8, NOW()),
    ('QR12325225354', 8, NOW());

    INSERT INTO
    `attendance` (
        `qr_id`,
        `user_no`,
        `check_time`
    )
VALUES ('QR121412546345', 9, NOW()),
    ('QR12342141342316', 9, NOW()),
    ('QR36346316356352347', 9, NOW()),
    ('QR2525243512348', 9, NOW()),
    ('QR2436262612349', 9, NOW()),
    ('QR122142135werwe235350', 9, NOW()),
    ('QR12241324werwe235351', 9, NOW()),
    ('QR1224132rwer4235351', 9, NOW()),
    ('QR12241324rwerwe235351', 9, NOW()),
    ('QR12241324235351', 9, NOW()),
    ('QR122523523352', 9, NOW()),
    ('QR125235235353', 9, NOW()),
    ('QR1235232354', 9, NOW());

INSERT INTO
    `attendance` (
        `qr_id`,
        `user_no`,
        `check_time`
    )
VALUES 
    ('QR174574742354', 1, NOW());



/* 출석 횟수 올리기 */
INSERT INTO
    `attendance` (
        `qr_id`,
        `user_no`,
        `check_time`
    )
VALUES ('QR12134134312345', 1, NOW()),
    ('QR1241414346', 1, NOW()),
    ('QR12124636213347', 1, NOW()),
    ('QR12123433534513347', 1, NOW()),
    ('QR164673432425722347', 1, NOW()),
    ('QR12123413347', 1, NOW()),
    ('QR14534543523347', 1, NOW()),
    ('QR12123347', 1, NOW()),
    ('QR1213345435435347', 1, NOW()),
    ('QR1212341131223213347', 1, NOW()),
    ('QR12123412321133213347', 1, NOW()),
    ('QR12123411543454523213347', 1, NOW()),
    ('QR121234365476123213347', 1, NOW()),
    ('QR121237658784123213347', 1, NOW()),
    ('QR1212797834123213347', 1, NOW()),
    ('QR12123079094123213347', 1, NOW()),
    ('QR1212380-0-4123213347', 1, NOW()),
    ('QR121213214123213347', 1, NOW()),
    ('QR121212321231234123213347', 1, NOW()),
    ('QR12132146462348', 1, NOW());





