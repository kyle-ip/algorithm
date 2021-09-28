# 组合两个表

SQL 架构
```sql
Create table If Not Exists Person (PersonId int, FirstName varchar(255), LastName varchar(255))
Create table If Not Exists Address (AddressId int, PersonId int, City varchar(255), State varchar(255))
Truncate table Person
insert into Person (PersonId, LastName, FirstName) values ('1', 'Wang', 'Allen')
insert into Person (PersonId, LastName, FirstName) values ('2', 'Alice', 'Bob')
Truncate table Address
insert into Address (AddressId, PersonId, City, State) values ('1', '2', 'New York City', 'New York')
insert into Address (AddressId, PersonId, City, State) values ('2', '3', 'Leetcode', 'California')
```

表1: `Person`
```
+-------------+---------+
| 列名         | 类型     |
+-------------+---------+
| PersonId    | int     |
| FirstName   | varchar |
| LastName    | varchar |
+-------------+---------+
PersonId 是上表主键
```

表2: `Address`
```
+-------------+---------+
| 列名         | 类型    |
+-------------+---------+
| AddressId   | int     |
| PersonId    | int     |
| City        | varchar |
| State       | varchar |
+-------------+---------+
AddressId 是上表主键
```

编写一个 SQL 查询，满足条件：无论 person 是否有地址信息，都需要基于上述两表提供 person 的以下信息：
```
FirstName, LastName, City, State
```

Solution:
```sql
SELECT p.FirstName, p.LastName, a.City, a.State
FROM Person AS p
LEFT JOIN Address AS a ON p.PersonId = a.PersonId
```