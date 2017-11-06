create database global_db;
create user global_user;
grant all on global_db.* to 'global_user'@'localhost' identified by 'password1!';
grant all on global_db.* to 'global_user'@'%' identified by 'password1!';
grant select, insert, delete, update on global_db.* to 'global_user'@'localhost' identified by 'password1!';
grant select, insert, delete, update on global_db.* to 'global_user'@'%' identified by 'password1!';