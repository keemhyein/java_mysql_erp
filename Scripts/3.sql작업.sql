select code, name from title;
  
select code, name from title where code = 1;
 
insert into title values (6, '인턴');

update title set name = '계약직' where code = 6;

delete from title where code = 6;



select code, name, floor from department;

select code, name, floor from department where code = 1;

insert into department values (4, '영업', 6);

update department set name = '개발' where code = 4;

delete from department where code = 4;