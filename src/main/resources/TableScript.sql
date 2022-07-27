drop database if exists employee_maintenance;

create database employee_maintenance default character set latin1 collate latin1_general_cs;

use employee_maintenance;

drop table if exists Department;

create table Department(
department_id int(10) not null,
dept_name varchar(50) not null,
updated_at timestamp default current_timestamp not null,
created_at timestamp default current_timestamp not null,
constraint department_id_PK primary key (department_id)
);

insert into Department values(1001, 'Support', STR_TO_DATE("7-22-2022", "%m-%d-%Y"), STR_TO_DATE("7-22-2022", "%m-%d-%Y"));
insert into Department values(1002, 'Production', STR_TO_DATE("7-22-2022", "%m-%d-%Y"), STR_TO_DATE("7-22-2022", "%m-%d-%Y"));
insert into Department values(1003, 'Maintenance', STR_TO_DATE("7-22-2022", "%m-%d-%Y"), STR_TO_DATE("7-22-2022", "%m-%d-%Y"));

select * from department;

drop table if exists Employee;

create table Employee(
employee_id int(10) not null auto_increment,
employee_name varchar(50) not null,
job_description varchar(50) not null,
manager_id int(10) not null,
salary int(10) not null,
updated_at timestamp default current_timestamp not null,
created_at timestamp default current_timestamp not null,
department_id int(10) not null,
constraint employee_id_PK primary key (employee_id),
constraint department_id_FK foreign key (department_id) references Department(department_id)
);

insert into Employee values(1, 'Vinay Nikam', 'Technology Analyst', 2, 60000, STR_TO_DATE("7-22-2022", "%m-%d-%Y"), STR_TO_DATE("7-22-2022", "%m-%d-%Y"), 1002);
insert into Employee values(2, 'Pradeep Kumar Verma', 'Technology Lead', 9, 100000, STR_TO_DATE("7-22-2022", "%m-%d-%Y"), STR_TO_DATE("7-22-2022", "%m-%d-%Y"), 1002);
insert into Employee values(3, 'Soham Sawant', 'Senior System Engineer', 2, 50000, STR_TO_DATE("7-22-2022", "%m-%d-%Y"), STR_TO_DATE("7-22-2022", "%m-%d-%Y"), 1002);
insert into Employee values(4, 'Manisha Mondal', 'Technology Analyst', 6, 80000, STR_TO_DATE("7-22-2022", "%m-%d-%Y"), STR_TO_DATE("7-22-2022", "%m-%d-%Y"), 1003);
insert into Employee values(5, 'Pooja Jagtap', 'Linux Administrator', 8, 70000, STR_TO_DATE("7-22-2022", "%m-%d-%Y"), STR_TO_DATE("7-22-2022", "%m-%d-%Y"), 1001);

select * from employee;


commit;