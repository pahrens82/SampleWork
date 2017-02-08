
Drop Database If Exists GLBC_test;
Create Database GLBC_test;
Use GLBC_test;


Create Table Category(
Category_ID int Not Null auto_increment,
CategoryName varchar(30),
Primary Key(Category_ID)
);


Create Table Tag(
Tag_ID int Not Null auto_increment,
`Name` varchar(50),
Primary Key(Tag_ID)
);


Create Table `Permission`(
Permission_ID int Not Null auto_increment,
`Name` varchar(50),
Description varchar(180),
`authority` varchar(50),
Primary Key(Permission_ID)
);

Create Table Beer(
Beer_ID int Not Null auto_increment,
Brewery_ID int Not Null,
Approval bool,
BeerName varchar(50),
Summary varchar(180),
TextBody blob,
Style varchar(30),
Hop varchar(30),
ABV DECIMAL(5,2),
IBU DECIMAL(5,2),
Primary Key(Beer_ID)
);

Create Table Brewery(
Brewery_ID int Not Null auto_increment,
Approval bool,
BreweryName varchar(50),
Summary varchar(180),
TextBody blob,
City varchar(50),
State varchar(50),
Brewmaster varchar(50),
Primary Key(Brewery_ID)
);

Create Table Article(
Article_ID int Not Null auto_increment,
username varchar(50) not null,
Category_ID int,
Approval bool,
ArticleName varchar(50),
Summary varchar(180),
TextBody blob,
CreateDate varchar(10),
EditDate varchar(10),
PublishDate varchar(10),
ApproveDate varchar(10),
Primary Key(Article_ID)
);

Create Table `User`(
User_ID int Not Null auto_increment,
Permission_ID int Not Null,
username varchar(50) not null,
`password` varchar(50),
FirstName varchar(50),
LastName varchar(50),
Email varchar(80),
`enabled` tinyint(1) not null,
Primary Key (User_ID),
Key(UserName)
);

Create Table ArticleTag(
Article_ID int Not Null,
Tag_ID int Not Null
);

create table UserPermissions(
username varchar(50) not null,
Permission_ID int Not Null,
constraint fk_UserPermissions_User foreign key (username) references `User`(username),
constraint fk_UserPermissions_Permission foreign key (Permission_ID) References `Permission`(Permission_ID)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
create unique index ix_user_permission on UserPermissions (username, Permission_ID);


Alter Table Beer Add Constraint fk_Beer_Brewery 
Foreign Key (Brewery_ID) References Brewery(Brewery_ID);

Alter Table Article Add Constraint fk_Article_Category 
Foreign Key (Category_ID) References Category(Category_ID);

Alter Table User Add Constraint fk_User_Permission 
Foreign Key (Permission_ID) References `Permission`(Permission_ID);


Alter Table Article Add Constraint fk_Article_User 
Foreign Key (username) References `User`(username);


Alter Table ArticleTag Add Constraint fk_ArticleTag_Article 
Foreign Key (Article_ID) References Article(Article_ID);

Alter Table ArticleTag Add Constraint fk_ArticleTag_Tag 
Foreign Key (Tag_ID) References Tag(Tag_ID);

Insert Into Brewery Values(1, true, "Foundation Brewing Company", "THIS IS A DESCRIPTION", "This is a body to the static page", "Portland", "Maine", "Jason Perkins"),
(2, true, "Bissell Brothers Brewing", "THIS IS A DESCRIPTION", "This is a body to the static page", "Portland", "Maine", "Noah Bissell"),
(3, true, "Barreled Souls Brewing Company", "THIS IS A DESCRIPTION", "This is a body to the static page", "Saco", "Maine", "Matthew Mills");

Insert Into Beer Values(1, 1, true, "Epiphany", "DESCRIPTION", "This is a body to the static page", "Imperial IPA", "Citra", 8.00, 100),
(2, 2, true, "The Substance", "DESCRIPTION", "This is a body to the static page", "American IPA", "Centennial", 6.60, 49.27),
(3, 3, true, "Space Gose", "DESCRIPTION", "This is a body to the static page", "Gose", "", 4.90,"");

Insert Into `Permission` Values(1, "Admin", "Site Admin: No approval required and manage users", "ROLE_ADMIN"),
(2, "Super User", "Add/Edit/Remove all content. Approval Required", "ROLE_SUPER"),
(3, "User", "Allowed to Comment", "ROLE_USER"),
(4, "Guest", "Allowed to View", "ROLE_GUEST"),
(5, "Restricted", "No Access", "ROLE_RESTRICTED");

Insert Into `User` Values(1, 1, "admin", "passwd", "admin", "admin", "admin@swg.com", true),
(2, 2, "superuser", "passwd", "Hunter", "Thompson", "hst@theedge.com", true),
(3, 3, "maj5004", "passwd", "Mark", "Joiner", "maj5004@gmail.com", true);

Insert Into Category Values(1, "IPA"), (2, "AIPA"), (3, "Gose");

Insert Into Article (username, Category_ID, Approval, ArticleName, Summary, TextBody, CreateDate, EditDate, PublishDate, ApproveDate) Values("admin", 1, true, "Artical on Epiphany", "Best. Beer. Ever.", "Body of article", "11-16-16", "11-16-16", "11-16-16", "11-16-16"),
("admin", 1, true, "Artical on Substance", "Very hard to get.. but worthing it you can.", "Body of article", "11-16-16", "11-16-16", "11-16-16", "11-16-16"),
("superuser", 1, true, "Artical on Space Gose", "Fantastic on a hot summer day.", "Body of article", "11-16-16", "11-16-16", "11-16-16", "11-16-16");

Insert Into Tag Values(1, "Top10BeerList"), 
(2, "PlayingHardToGet"), 
(3, "SunnyDaySipper");

Insert Into ArticleTag Values(1, 1), (2, 2), (3, 3);

insert into UserPermissions values
("admin",1),("admin",2),("admin",3),("admin",4),
("superuser",2),("superuser",3),("superuser",4),
("maj5004",3),("maj5004",4);