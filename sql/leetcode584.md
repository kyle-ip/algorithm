# [寻找用户推荐人](https://leetcode-cn.com/problems/find-customer-referee) 

#### [Find Customer Referee](https://leetcode-cn.com/problems/find-customer-referee/)

SQL架构

```sql
CREATE TABLE IF NOT EXISTS customer (id INT,name VARCHAR(25),referee_id INT);
Truncate table customer
insert into customer (id, name, referee_id) values ('1', 'Will', 'None')
insert into customer (id, name, referee_id) values ('2', 'Jane', 'None')
insert into customer (id, name, referee_id) values ('3', 'Alex', '2')
insert into customer (id, name, referee_id) values ('4', 'Bill', 'None')
insert into customer (id, name, referee_id) values ('5', 'Zack', '1')
insert into customer (id, name, referee_id) values ('6', 'Mark', '2')
```

Given a table `customer` holding customers information and the referee.

```
+------+------+-----------+
| id   | name | referee_id|
+------+------+-----------+
|    1 | Will |      NULL |
|    2 | Jane |      NULL |
|    3 | Alex |         2 |
|    4 | Bill |      NULL |
|    5 | Zack |         1 |
|    6 | Mark |         2 |
+------+------+-----------+
```

Write a query to return the list of customers **NOT** referred by the person with id '2'.

For the sample data above, the result is:

```
+------+
| name |
+------+
| Will |
| Jane |
| Bill |
| Zack |
+------+
```