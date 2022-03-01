create table sbr_common_all_simple (
	id bigint identity not null,
	idsbr nvarchar(255),
	nmperusahaan nvarchar(255),
	nmkomersial nvarchar(255), 
	alamat nvarchar(255), 
	kddesa nvarchar(255), 
	kdkab nvarchar(255), 
	kdkec nvarchar(255), 
	kdprop nvarchar(255),
	kodearea nvarchar(255), 
	notelp nvarchar(255), 
	kegutama nvarchar(255), 
	kdkbli nvarchar(255), 
	[end] nvarchar(255), 
	nofax nvarchar(255), 
	kodepos nvarchar(255),
	totaltk int not null, 
	skalausaha nvarchar(255),
	email nvarchar(255), 
	flaglbu nvarchar(255), 
	tahunberdiri nvarchar(255), 
	tahunoperasi nvarchar(255), 
	latitude nvarchar(255), 
	longitude nvarchar(255), 
	uploaded_datetime datetime2, 
	uploaded_filename nvarchar(255), 
	uploaded_petugas int not null, 
	primary key (id))
create table sbr_temps (
	id bigint identity not null,
	nmperusahaan nvarchar(255),
	alamat nvarchar(255),
	kddesa nvarchar(255), 
	kdkab nvarchar(255),
	kdkec nvarchar(255), 
	kdprop nvarchar(255),
	kodepos nvarchar(255),
	kodearea nvarchar(255),
	notelp nvarchar(255),
	noekstensi nvarchar(255),
	nofax nvarchar(255), 
	email nvarchar(255),
	cpnama nvarchar(255), 
	cpnotelp nvarchar(255),
	kegutama nvarchar(255), 
	kdkategori nvarchar(255), 
	kdkbli nvarchar(255), 
	badanhukum nvarchar(255), 
	produk nvarchar(255), 
	jkpengusaha nvarchar(255),
	pendapatan_per_tahun bigint not null, 
	totaltk int not null,
	[output] int not null, 
	status_perusahaan nvarchar(255),
	aset int not null,
	institusi nvarchar(255),
	sm nvarchar(255), 
	kegiatan nvarchar(255),
	tahun nvarchar(255), 
	petugas int not null,
	is_locked datetime2, 
	idsbr nvarchar(255), 
	uploaded_datetime datetime2, 
	uploaded_filename nvarchar(255), 
	uploaded_petugas int not null, 
	primary key (id))
create table users (
	id bigint identity not null, 
	email nvarchar(255),
	username nvarchar(255),
	[name] nvarchar(255), 
	nip nvarchar(255), 
	[password] nvarchar(255), 
	photo nvarchar(255),
	[role] nvarchar(255),
	primary key (id))

create table roles(
	id bigint identity not null, 
	[name] nvarchar(90),
	primary key (id)
)

create table user_roles(
	id bigint identity not null, 
	user_id bigint not null,
	role_id bigint not null,
	primary key (id)
)

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_SUPERVISOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

INSERT INTO users(name, username, email, password) VALUES('Admin', 'admin', 'admin@bps.go.id', '$10$zwo0Yx5lUYuQWCXSBVOXMuCD5OXcZBapBtj48nkh5Y3oNKJkYs5pe');
INSERT INTO user_roles(user_id, role_id) VALUES(1,1);
INSERT INTO user_roles(user_id, role_id) VALUES(1,2);
INSERT INTO user_roles(user_id, role_id) VALUES(1,3);


USE sbrdevdb;  
GO
CREATE UNIQUE INDEX ui_sbr_common_all_simple_id ON sbr_common_all_simple(id);  
CREATE FULLTEXT CATALOG ft AS DEFAULT;  
CREATE FULLTEXT INDEX ON sbr_common_all_simple(
	nmperusahaan, 
	alamat,
	kdkec,
	kdkab,
	kdprop)   
   KEY INDEX ui_sbr_common_all_simple_id   
   WITH STOPLIST = SYSTEM;  
GO