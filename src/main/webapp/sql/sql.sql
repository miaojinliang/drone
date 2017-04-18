create table user (
    [id]            integer PRIMARY KEY autoincrement,                -- 设置主键
    [username]         varchar (50),
    [password]         varchar (50)
);

create table drone (
	[id]			integer PRIMARY KEY autoincrement, 
	[name]			varchar(50),
	[seq]			integer,
	[type]			integer,
	[start_delay]	integer,
	[dir_pin]		integer,
	[pul_pin]		integer,
	[direction]		integer,
	[before_pin]	integer,
	[back_pin]		integer,
	[rotate_delay]	integer
);

create table access(
	[id]			integer PRIMARY KEY autoincrement,
	[name]			varchar(50),
	[type]			integer,
	[back_delay]	integer,
	[drone_id]		integer,
	[open_status]	integer
);