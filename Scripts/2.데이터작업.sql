select user(), database();

desc title;

insert 
  into erp.title (code, name)
values 
(1, '사장'),
(2, '부장'),
(3, '과장'),
(4, '대리'),
(5, '사원');

select code, name from title;

insert into erp.department (code, name, floor) values(1, '기획', 10);
insert into erp.department values (2, '인사', 9), (3, '마케팅', 8);

select * from department;

insert 
  into erp.employee (empno, empname, title, manager, salary, dno) 
values (4377, '이성래', 1, null, 4500000, 1);

select * from employee;


