create table users (
id bigint auto_increment,
name varchar(50),
balance DOUBLE,
primary key(id)
);

create table user_transaction(
id bigint auto_increment,
user_id bigint,
amt DOUBLE,
LOCAL_DATE_TIME timestamp,
foreign key (user_id) references users(id) on delete cascade
);