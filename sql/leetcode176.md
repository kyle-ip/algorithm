# [第二高的薪水](https://leetcode-cn.com/problems/second-highest-salary)

#### [Second Highest Salary](https://leetcode-cn.com/problems/second-highest-salary/)

SQL架构

```sql
Create table If Not Exists Employee (Id int, Salary int)
Truncate table Employee
insert into Employee (Id, Salary) values ('1', '100')
insert into Employee (Id, Salary) values ('2', '200')
insert into Employee (Id, Salary) values ('3', '300')
```

Write a SQL query to get the second highest salary from the `Employee` table.

```
+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
```

For example, given the above Employee table, the query should return `200` as the second highest salary. If there is no second highest salary, then the query should return `null`.

```
+---------------------+
| SecondHighestSalary |
+---------------------+
| 200                 |
+---------------------+
```

```sql
SELECT (
    SELECT
        DISTINCT Salary
    FROM
        Employee
    ORDER BY
        Salary DESC
    LIMIT 1 OFFSET 1
) AS SecondHighestSalary;
```

```sql
SELECT
    IFNULL((
        SELECT DISTINCT Salary
        FROM Employee
        ORDER BY Salary DESC
        LIMIT 1 OFFSET 1
    ),NULL) AS SecondHighestSalary
```