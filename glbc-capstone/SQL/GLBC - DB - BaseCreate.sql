#username = glbc
#Passwd = hopHead1337

Drop Database If Exists GLBC;
Create Database GLBC;
Use GLBC;

#~~~~~~~~~~~~~~~~~~~~~~~~
#START - Create Tables
#~~~~~~~~~~~~~~~~~~~~~~~~
#----Start - Base tables, no FK relationships----#
#Category Table + PK
Create Table Category(
Category_ID int Not Null auto_increment,
`CategoryName` varchar(30),
Primary Key(Category_ID)
);

#Tag Table + PK
Create Table Tag(
Tag_ID int Not Null auto_increment,
`TagName` varchar(50),
Primary Key(Tag_ID)
);

#Permission Table + PK
Create Table Permission(
Permission_ID int Not Null auto_increment,
`Name` varchar(50),
Description varchar(180),
`authority` varchar(50),
Primary Key(Permission_ID)
);
#----End - Base tables, no FK relationships----#

###

#----Start - Tables with FSs, NOT setting FK relationship----#
#Beer Table + PK
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

#Brewery Table + PK
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

#Article Table + PK
##! Added 'Catetory_ID' for '1 category per Article'.
##!  Remove for <1 cat per article
Create Table Article(
Article_ID int Not Null auto_increment,
username varchar(50) not null,
Category_ID int,
Approval bool,
ArticleName varchar(50),
Summary varchar(180),
TextBody blob,
CreateDate date,
EditDate date,
ApproveDate date,
PublishDate date,
Primary Key(Article_ID)
);

#User Table + PK
Create Table `User`(
User_ID int Not Null auto_increment,
Permission_ID int Not Null,
username varchar(50) not null,
`password` varchar(100) not null,
FirstName varchar(50),
LastName varchar(50),
Email varchar(80),
`enabled` tinyint(1) not null,
Primary Key (User_ID),
Key(username)
);
create unique index ix_username on User (username);

#Comment Table + PK
Create Table `Comment`(
Comment_ID int Not Null auto_increment,
Article_ID int Not Null,
username varchar(50) not null,
Permission_ID int Not Null,
TextBody blob,
DateCreated date,
DateEdited date,
Primary Key(Comment_ID)
);

#----End - Tables with FSs, NOT setting FK relationship----#

###

#----Start - Bridge tables, NOT setting FK relationship----#
#CategoryArticle Table
##! Commenting out for now, as we're going to have 1 category per article
##!  Uncomment for <1 cat per article
#Create Table CategoryArticle(
#Category_ID int Not Null,
#Article_ID int Not Null
#);

#ArticleTag Table
Create Table ArticleTag(
Article_ID int Not Null,
Tag_ID int Not Null
);

#BreweryBeer Table
Create Table BreweryBeer(
Brewery_ID int Not Null,
Beer_ID int Not Null
);

create table UserPermissions(
username varchar(50) not null,
Permission_ID int Not Null,
constraint fk_UserPermissions_User foreign key (username) references `User`(username),
constraint fk_UserPermissions_Permission foreign key (Permission_ID) References Permission(Permission_ID)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
create unique index ix_user_permission on UserPermissions (username, Permission_ID);

#----End - Bridge tables----#
#~~~~~~~~~~~~~~~~~~~~~~~~
#END - Create Tables
#~~~~~~~~~~~~~~~~~~~~~~~~


#~~~~~~~~~~~~~~~~~~~~~~~~
#START - Create FK Relationships
#~~~~~~~~~~~~~~~~~~~~~~~~
#Beer Table
Alter Table Beer Add Constraint fk_Beer_Brewery 
Foreign Key (Brewery_ID) References Brewery(Brewery_ID);

#CategoryArticle Table
##! Commenting out for now, as we're going to have 1 category per article
##!  Uncomment for <1 cat per article
#Alter Table CategoryArticle Add Constraint fk_CategoryArticle_Category 
#Foreign Key (Category_ID) References Category(Category_ID);
#
#Alter Table CategoryArticle Add Constraint fk_CategoryArticle_Article 
#Foreign Key (Article_ID) References Article(Article_ID);

#User Table
Alter Table User Add Constraint fk_User_Permission 
Foreign Key (Permission_ID) References Permission(Permission_ID);

#Article Table
Alter Table Article Add Constraint fk_Article_User 
Foreign Key (username) References `User`(username);
#
##! Added in for '1 category per article'
##!  Remove for <1 cat per article
Alter Table Article Add Constraint fk_Article_Category_ID
Foreign Key (Category_ID) References Category(Category_ID);

#ArticleTag Table
Alter Table ArticleTag Add Constraint fk_ArticleTag_Article 
Foreign Key (Article_ID) References Article(Article_ID);
#
Alter Table ArticleTag Add Constraint fk_ArticleTag_Tag 
Foreign Key (Tag_ID) References Tag(Tag_ID);

#Comment Table
Alter Table `Comment` Add Constraint fk_Comment_Article 
Foreign Key (Article_ID) References Article(Article_ID);
#
Alter Table `Comment` Add Constraint fk_Comment_User 
Foreign Key (username) References `User`(username);
#
Alter Table `Comment` Add Constraint fk_Comment_Permission 
Foreign Key (Permission_ID) References Permission(Permission_ID);

-- alter table UserPermissions add constraint fk_UserPermissions_User foreign key (username) references `User`(username);

-- alter table UserPermissions add constraint fk_UserPermissions_Permission foreign key (Permission_ID) References Permission(Permission_ID);

#~~~~~~~~~~~~~~~~~~~~~~~~
#END - Create FK Relationships
#~~~~~~~~~~~~~~~~~~~~~~~~


#~~~~~~~~~~~~~~~~~~~~~~~~
#START - Add Mock Data
#  Order here is VERY important!!
#~~~~~~~~~~~~~~~~~~~~~~~~

#Brewery
#(Brewery_ID, Approval, Name, Summary, TextBody, City, State, Brewmaster)
Insert Into Brewery Values(1, true, "Foundation Brewing Company", "THIS IS A DESCRIPTION", "This is a body to the static page", "Portland", "Maine", "Jason Perkins"),
(2, true, "Bissell Brothers Brewing", "THIS IS A DESCRIPTION", "This is a body to the static page", "Portland", "Maine", "Noah Bissell"),
(3, false, "Barreled Souls Brewing Company", "THIS IS A DESCRIPTION", "This is a body to the static page", "Saco", "Maine", "Matthew Mills");

#Beer
#(Beer_ID, Brewery_ID, Approval, BeerName, Summary, TextBody, Style, Hop, ABV, IBU)
Insert Into Beer Values(1, 1, true, "Epiphany", "DESCRIPTION", "This is a body to the static page", "Imperial IPA", "Citra", 8.00, 100),
(2, 2, true, "The Substance", "DESCRIPTION", "This is a body to the static page", "American IPA", "Centennial", 6.60, 49.27),
(3, 3, false, "Space Gose", "DESCRIPTION", "This is a body to the static page", "Gose", "", 4.90,"");

#Permission
#(Permission_ID, Name, Description, Role)
Insert Into Permission Values(1, "Admin", "Site Admin: No approval required and manage users", "ROLE_ADMIN"),
(2, "Super User", "Add/Edit/Remove all content. Approval Required", "ROLE_SUPER"),
(3, "User", "Allowed to Comment", "ROLE_USER"),
(4, "Guest", "Allowed to View", "ROLE_GUEST"),
(5, "Restricted", "No Access", "ROLE_RESTRICTED");

#User
#(User_ID, Permission_ID, username, password, FirstName, LastName, Email, enabled)
Insert Into User Values(1, 1, "admin", "passwd", "admin", "admin", "admin@swg.com", true),
(2, 2, "superuser", "passwd", "Hunter", "Thompson", "hst@theedge.com", true),
(3, 3, "maj5004", "passwd", "Mark", "Joiner", "maj5004@gmail.com", true),
(4,4,"Guest", "guest", "Guest", "", "no one should be using this account, ever!",true);

#Category
#(Category_ID, Name)
Insert Into Category Value(1, "Beer Review"), (2, "Brewery Review"), (3, "Beer Event");

#Article
#Article_ID, username, Approval, name, Summmary, TextBody, DateCreated, DateEdited
##! Commenting out for now, as we're going to have 1 category per article
##!  Uncomment for <1 cat per article
#Insert Into Article Values(1, "admin", true, "Artical on Epiphany", "Best. Beer. Ever.", "Body of article", "2016-11-16", "2016-11-16", null, null),
#(2, "superuser", true, "Artical on Substance", "Very hard to get.. but worthing it you can.", "Body of article", "2016-11-16", "2016-11-16", null, null),
#(3, "superuser", false, "Artical on Space Gose", "Fantastic on a hot summer day.", "Body of article", "2016-11-16", "2016-11-16", null, null);
##! Added in for '1 category per article'
##!  Remove for <1 cat per article
#Article_ID, username, Category_ID, username, Approval, name, Summmary, TextBody, DateCreated, DateEdited Approval, name, Summmary, TextBody, DateCreated, DateEdited
Insert Into Article Values(1, "admin", 1, true, "Artical on Epiphany", "Best. Beer. Ever.", "Body of article", "2016-11-16", "2016-11-16", null, null),
(2, "superuser", 2, true, "Artical on Foundation", "Very hard to get.. but worthing it you can.", "Body of article", "2016-11-16", "2016-11-16", null, null),
(3, "superuser", 3, false, "Artical on Brew Fest 2016", "Fantastic on a hot summer day.", "Body of article", "2016-11-16", "2016-11-16", null, null);

#Comment
#(Comment_ID, Article_ID, username, Permission_ID, TextBody, DateCreated, DateEdited)
Insert Into `Comment` Values(1, 1, "admin", 1, "I'm the admin, test comment", "2016-11-16", "2016-11-16"),
(2, 2, "superuser", 2, "I'm the superuser, test comment", "2016-11-16", "2016-11-16"),
(3, 3, "maj5004", 3, "I'm the user, test comment", "2016-11-16", "2016-11-16");

 
#CategoryArticle
#(Article_ID, Category_ID)
##! Commenting out for now, as we're going to have 1 category per article
##!  Uncomment for <1 cat per article
#Insert Into CategoryArticle Value(1, 1), (2, 2), (3, 3);

#Tag
#(Tag_ID, Name)
Insert Into Tag Values(1, "Top10BeerList"), 
(2, "PlayingHardToGet"), 
(3, "SunnyDaySipper");

#ArticleTag
#(Article_ID, Tag_ID)
Insert Into ArticleTag Values(1, 1), (2, 2), (3, 3);

insert into UserPermissions values
("admin",1),("admin",2),("admin",3),("admin",4),
("superuser",2),("superuser",3),("superuser",4),
("maj5004",3),("maj5004",4),
("Guest", 4);



#~~~~~~~~~~~~~~~~~~~~~~~~
#END - Add Mock Data
#~~~~~~~~~~~~~~~~~~~~~~~~

