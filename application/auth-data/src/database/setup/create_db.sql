create database auth_db;
create user auth_user;
grant all on auth_db.* to 'auth_user'@'localhost' identified by 'password1!';
grant all on auth_db.* to 'auth_user'@'%' identified by 'password1!';
grant select, insert, delete, update on auth_db.* to 'auth_user'@'localhost' identified by 'password1!';
grant select, insert, delete, update on auth_db.* to 'auth_user'@'%' identified by 'password1!';