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
	[rotate_delay]	integer,
	[start_button]  varchar(50),
	[stop_button]	varchar(50),
	[back_button]	varchar(50),
	[rotate_start]	varchar(50),
	[rotate_stop]	varchar(50),
	[rotate_back]	varchar(50)
);

create table access(
	[id]			integer PRIMARY KEY autoincrement,
	[name]			varchar(50),
	[type]			integer,
	[back_delay]	integer,
	[drone_id]		integer,
	[open_status]	integer,
	[pin_num]		integer
);


create table raspclient(
	[id]			integer PRIMARY KEY autoincrement,
	[ip]			varchar(50),
	[port]			varchar(50),
	[name]			varchar(50),
	[app_name]		varchar(50),
	[remark]		varchar(200)
);

create table auto_plan(
	[id]			integer PRIMARY KEY autoincrement,
	[name]			varchar(50),
	[create_time]	datetime,
	[remark]		varchar(200)
);

create table plan_detail(
	[id]			integer PRIMARY KEY autoincrement,
	[drone_id]		integer,
	[rasp_id]		integer,
	[sort_num]		integer,
	[plan_id]		integer
);