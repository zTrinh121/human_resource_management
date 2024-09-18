INSERT INTO JOBS(JOB_TITLE) VALUES('Manager');
INSERT INTO JOBS(JOB_TITLE) VALUES('Marketing Assistant');
INSERT INTO JOBS(JOB_TITLE) VALUES('Copywriter');
INSERT INTO JOBS(JOB_TITLE) VALUES('Account Executive');
INSERT INTO JOBS(JOB_TITLE) VALUES('Sales engineer');
INSERT INTO JOBS(JOB_TITLE) VALUES('Service Desk Engineer');
INSERT INTO JOBS(JOB_TITLE) VALUES('IT Helpdesk');
INSERT INTO JOBS(JOB_TITLE) VALUES('HR Analysit');

SELECT * FROM JOBS;


INSERT INTO DEPARTMENTS(DEPARTMENT_NAME, MANAGER_ID) VALUES('IT Support', 1);
INSERT INTO DEPARTMENTS(DEPARTMENT_NAME, MANAGER_ID) VALUES('Human Resources', 2);
INSERT INTO DEPARTMENTS(DEPARTMENT_NAME, MANAGER_ID) VALUES('Marketing', 3);
INSERT INTO DEPARTMENTS(DEPARTMENT_NAME, MANAGER_ID) VALUES('Sales', 4);

SELECT * FROM departments;


INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE,JOB_ID,SALARY, DEPARTMENT_ID) VALUES('John','Doe','johndoe@gmail.com', '0901820191', TO_DATE('2022-01-15', 'YYYY-MM-DD'),1,10000,1);
INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE,JOB_ID,SALARY, DEPARTMENT_ID) VALUES('Jane','Doe','janedoe@gmail.com', '0902020191', TO_DATE('2021-02-15', 'YYYY-MM-DD'),1,10000,2);
INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE,JOB_ID,SALARY, DEPARTMENT_ID) VALUES('Steve','Micks','stevemicks@gmail.com', '0901820215', TO_DATE('2020-11-25', 'YYYY-MM-DD'),1,15000,3);
INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE,JOB_ID,SALARY,DEPARTMENT_ID) VALUES('Taylor','Borwn','taylorbrown@gmail.com', '0502920191', TO_DATE('2021-12-12', 'YYYY-MM-DD'),1,20000,4);
INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, MANAGER_ID, DEPARTMENT_ID) VALUES('Mark','Taylor','marktaylor@gmail.com', '0901921512', TO_DATE('2023-11-01', 'YYYY-MM-DD'),6,5000,1, 1);
INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, MANAGER_ID, DEPARTMENT_ID) VALUES('John','Williams','johnwilliams@gmail.com', '0901721810', TO_DATE('2024-01-01', 'YYYY-MM-DD'),7,2000,1, 1);
INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, MANAGER_ID, DEPARTMENT_ID) VALUES('James','Wilson','jameswilson@gmail.com', '0901921107', TO_DATE('2022-11-01', 'YYYY-MM-DD'),8,7000,2, 2);
INSERT INTO EMPLOYEES(FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, MANAGER_ID, DEPARTMENT_ID) VALUES('Naomi','Smith','naomismith@gmail.com', '0501921508', TO_DATE('2023-12-12', 'YYYY-MM-DD'),2,1000,3, 3);

SELECT * FROM employees;


INSERT INTO JOB_HISTORY(EMPLOYEE_ID, START_DATE, END_DATE, JOB_ID, DEPARTMENT_ID)
VALUES(5, TO_DATE('2023-11-01', 'YYYY-MM-DD'), TO_DATE('2024-11-01', 'YYYY-MM-DD'), 1, 2 );
INSERT INTO JOB_HISTORY(EMPLOYEE_ID, START_DATE, END_DATE, JOB_ID, DEPARTMENT_ID)
VALUES(6, TO_DATE('2024-01-01', 'YYYY-MM-DD'), TO_DATE('2026-01-1', 'YYYY-MM-DD'), 1, 2 );
INSERT INTO JOB_HISTORY(EMPLOYEE_ID, START_DATE, END_DATE, JOB_ID, DEPARTMENT_ID)
VALUES(7, TO_DATE('2022-11-01', 'YYYY-MM-DD'), TO_DATE('2026-11-01', 'YYYY-MM-DD'), 1, 2 );
INSERT INTO JOB_HISTORY(EMPLOYEE_ID, START_DATE, END_DATE, JOB_ID, DEPARTMENT_ID)
VALUES(8, TO_DATE('2023-12-12', 'YYYY-MM-DD'), TO_DATE('2026-12-12', 'YYYY-MM-DD'), 1, 2 );

SELECT * FROM job_history;


INSERT INTO ROLES(ROLE_NAME) VALUES('admin');
INSERT INTO ROLES(ROLE_NAME) VALUES('user');

SELECT * FROM ROLES;


INSERT INTO USERS(USER_NAME, PASSWORD, ROLE_ID) VALUES('johndoe', 'johndoe123', 1);
INSERT INTO USERS(USER_NAME, PASSWORD, ROLE_ID) VALUES('janedoe', 'janedoe123', 1);
INSERT INTO USERS(USER_NAME, PASSWORD, ROLE_ID) VALUES('naomismith', 'naomismith123', 2);

SELECT * FROM USERS;

