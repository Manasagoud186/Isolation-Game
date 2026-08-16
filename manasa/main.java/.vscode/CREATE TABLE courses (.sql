CREATE TABLE courses (
    id INTEGER PRIMARY KEY,
    code TEXT,
    name TEXT,
    schedule TEXT
);

CREATE TABLE exams (
    id INTEGER PRIMARY KEY,
    course TEXT,
    date TEXT,
    time TEXT
);

CREATE TABLE facilities (
    id INTEGER PRIMARY KEY,
    name TEXT,
    timing TEXT
);

CREATE TABLE feedback (
    id INTEGER PRIMARY KEY,
    message TEXT
);

INSERT INTO courses VALUES (1,'CS101','Programming','Mon/Wed 9:00–10:30');
INSERT INTO courses VALUES (2,'MA201','Discrete Mathematics','Tue/Thu 11:00–12:30');

INSERT INTO exams VALUES (1,'CS101','10-Apr-2025','10:00–1:00');
INSERT INTO exams VALUES (2,'MA201','12-Apr-2025','10:00–1:00');

INSERT INTO facilities VALUES (1,'library','Mon–Fri 8AM–8PM');
INSERT INTO facilities VALUES (2,'canteen','8AM–6PM');
INSERT INTO facilities VALUES (3,'office','10AM–4PM');
