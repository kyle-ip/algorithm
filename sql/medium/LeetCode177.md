# 第 N 高的薪水

SQL 架构
```sql
Create table If Not Exists Employee (Id int, Salary int)
Truncate table Employee
insert into Employee (Id, Salary) values ('1', '100')
insert into Employee (Id, Salary) values ('2', '200')
insert into Employee (Id, Salary) values ('3', '300')
```

编写一个 SQL 查询，获取 `Employee` 表中第 *n* 高的薪水（Salary）。

```
+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
```

例如上述 `Employee` 表，*n = 2* 时，应返回第二高的薪水 `200`。如果不存在第 *n* 高的薪水，那么查询应返回 `null`。

```
+------------------------+
| getNthHighestSalary(2) |
+------------------------+
| 200                    |
+------------------------+
```

Solution: 
```sql
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
    DECLARE ans INT DEFAULT NULL;  
    SELECT 
        DISTINCT salary INTO ans
    FROM (
        SELECT 
            salary, @r:=IF(@p=salary, @r, @r+1) AS rnk, @p:= salary 
        FROM  
            employee, (SELECT @r:=0, @p:=NULL) init 
        ORDER BY salary DESC
    ) tmp
    WHERE rnk = N;
    RETURN ans;
END
```