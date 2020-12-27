# [员工奖金](https://leetcode-cn.com/problems/employee-bonus) 

#### [Employee Bonus](https://leetcode-cn.com/problems/employee-bonus/)

SQL架构

```sql
Create table If Not Exists Employee (EmpId int, Name varchar(255), Supervisor int, Salary int)
Create table If Not Exists Bonus (EmpId int, Bonus int)
Truncate table Employee
insert into Employee (EmpId, Name, Supervisor, Salary) values ('3', 'Brad', 'None', '4000')
insert into Employee (EmpId, Name, Supervisor, Salary) values ('1', 'John', '3', '1000')
insert into Employee (EmpId, Name, Supervisor, Salary) values ('2', 'Dan', '3', '2000')
insert into Employee (EmpId, Name, Supervisor, Salary) values ('4', 'Thomas', '3', '4000')
Truncate table Bonus
insert into Bonus (EmpId, Bonus) values ('2', '500')
insert into Bonus (EmpId, Bonus) values ('4', '2000')
```

Select all employee's name and bonus whose bonus is < 1000.

Table:`Employee`

```
+-------+--------+-----------+--------+
| empId |  name  | supervisor| salary |
+-------+--------+-----------+--------+
|   1   | John   |  3        | 1000   |
|   2   | Dan    |  3        | 2000   |
|   3   | Brad   |  null     | 4000   |
|   4   | Thomas |  3        | 4000   |
+-------+--------+-----------+--------+
empId is the primary key column for this table.
```

Table: `Bonus`

```
+-------+-------+
| empId | bonus |
+-------+-------+
| 2     | 500   |
| 4     | 2000  |
+-------+-------+
empId is the primary key column for this table.
```

Example ouput:

```
+-------+-------+
| name  | bonus |
+-------+-------+
| John  | null  |
| Dan   | 500   |
| Brad  | null  |
+-------+-------+
```