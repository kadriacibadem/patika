
1. test veritabanınızda employee isimli sütun bilgileri id(INTEGER), name VARCHAR(50), birthday DATE, email VARCHAR(100) olan bir tablo oluşturalım.

```
CREATE DATABASE test;

CREATE TABLE employee(
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	birthday DATE,
	email VARCHAR(100)
);
```

2. Oluşturduğumuz employee tablosuna 'Mockaroo' servisini kullanarak 50 adet veri ekleyelim.

```
insert into employee (name, birthday, email) values ('Merlina Glasson', '2014-06-07', 'mglasson0@altervista.org');
insert into employee (name, birthday, email) values ('Jillene Jansens', null, 'jjansens1@moonfruit.com');
insert into employee (name, birthday, email) values ('Carey Gehrels', '2021-05-14', 'cgehrels2@answers.com');
...
```

3. Sütunların her birine göre diğer sütunları güncelleyecek 5 adet UPDATE işlemi yapalım.

```
UPDATE employee
SET name= 'kadri acıbadem',
	birthday= '1994-02-15'
WHERE id=1;

UPDATE employee
SET email= 'kadriacibadem@gmail.com'
WHERE name= 'kadri acıbadem';

UPDATE employee
SET name= 'Masal'
WHERE name LIKE 'A%'
RETURNING *;

UPDATE employee
SET email= 'kadriacibadem@gmail.com'
WHERE name LIKE 'A%'
RETURNING *;

UPDATE employee
SET name= 'Yasemin',
	email= 'yasemin@gmail.com',
	birthday= '2000-02-25'
WHERE email= 'hsendley8@tinyurl.com'
RETURNING *;
```

4. Sütunların her birine göre ilgili satırı silecek 5 adet DELETE işlemi yapalım.

```
DELETE FROM employee
WHERE id=7;

DELETE FROM employee
WHERE email='kadriacibadem@gmail.com'
RETURNING *;

DELETE FROM employee
WHERE name ILIKE 'a%'
RETURNING *;

DELETE FROM employee
WHERE birthday='2000-02-25'
RETURNING *;

DELETE FROM employee
WHERE id BETWEEN 3 AND 25
RETURNING *;
```