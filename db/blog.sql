CREATE DATABASE IF NOT EXISTS blog DEFAULT CHARSET utf8mb4 ;
create table user (
                      id int primary key auto_increment,
                      user_name varchar(32) not null ,
                      gmt_create datetime not null,
                      gmt_modified datetime not null
) comment '用户';