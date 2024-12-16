INSERT INTO task (title, description, type, status, completed_at, day, week, month, year, user_id)
VALUES
    ('Day Task 1', 'Description for day task 1', 'DAY', 'PENDING', NULL, 15, NULL, 12, 2024, 1),
    ('Day Task 2', 'Description for day task 2', 'DAY', 'COMPLETED', '2024-12-16 10:00:00', 16, NULL, 12, 2024, 1);

-- Task 테이블에 week 타입 데이터 삽입
INSERT INTO task (title, description, type, status, completed_at, day, week, month, year, user_id)
VALUES
    ('Week Task 1', 'Description for week task 1', 'WEEK', 'PENDING', NULL, NULL, 50, 12, 2024, 1),
    ('Week Task 2', 'Description for week task 2', 'WEEK', 'COMPLETED', '2024-12-14 15:00:00', NULL, 50, 12, 2024, 1);

-- Task 테이블에 month 타입 데이터 삽입
INSERT INTO task (title, description, type, status, completed_at, day, week, month, year, user_id)
VALUES
    ('Month Task 1', 'Description for month task 1', 'MONTH', 'PENDING', NULL, NULL, NULL, 12, 2024, 1),
    ('Month Task 2', 'Description for month task 2', 'MONTH', 'COMPLETED', '2024-12-20 12:00:00', NULL, NULL, 12, 2024, 1);
