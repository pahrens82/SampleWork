Use GLBC_test;

alter table `User` drop foreign key fk_User_Permission;
alter table UserPermissions drop foreign key fk_UserPermissions_User;
alter table UserPermissions drop foreign key fk_UserPermissions_Permission;


drop table if exists UserPermissions;
drop table if exists `User`;
drop table if exists Permission;



Create Table Permission(
Permission_ID int Not Null auto_increment,
`Name` varchar(50),
Description varchar(180),
`Role` varchar(50),
Primary Key(Permission_ID)
);

Create Table `User`(
User_ID int Not Null auto_increment,
Permission_ID int Not Null,
Username varchar(50) not null key,
`Password` varchar(50) not null,
FirstName varchar(50),
LastName varchar(50),
Email varchar(80),
enabled boolean not null,
Primary Key (User_ID),
Constraint fk_User_Permission Foreign Key (Permission_ID) References Permission(Permission_ID)
);

create table UserPermissions(
Username varchar(50) not null,
Permission_ID int Not Null,
constraint fk_UserPermissions_User foreign key (Username) references `User`(Username),
constraint fk_UserPermissions_Permission foreign key (Permission_ID) References Permission(Permission_ID)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
create unique index ix_user_permission on UserPermissions (Username, Permission_ID);

Alter Table `Comment` Add Constraint fk_Comment_User 
Foreign Key (Username) References `User`(Username);

Insert Into Permission Values(1, "Admin", "Site Admin: No approval required and manage users", "ROLE_ADMIN"),
(2, "Super User", "Add/Edit/Remove all content. Approval Required", "ROLE_SUPER"),
(3, "User", "Allowed to Comment", "ROLE_USER"),
(4, "Guest", "Allowed to View", "ROLE_GUEST"),
(5, "Restricted", "No Access", "ROLE_RESTRICTED");

Insert Into User Values(1, 1, "admin", "passwd", "admin", "admin", "admin@swg.com"),
(2, 2, "superuser", "passwd", "Hunter", "Thompson", "hst@theedge.com"),
(3, 3, "maj5004", "passwd", "Mark", "Joiner", "maj5004@gmail.com");

insert into UserPermissions values
("admin",1),("admin",2),("admin",3),("admin",4),
("superuser",2),("superuser",3),("superuser",4),
("maj5004",3),("maj5004",4);
