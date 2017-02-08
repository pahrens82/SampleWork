/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  apprentice
 * Created: Nov 29, 2016
 */

use GLBC_test;

drop table if exists Article;

create table Article(
Article_ID int Not Null auto_increment,
User_ID int Not Null,
Approval bool,
ArticleName varchar(50),
Summary varchar(180),
TextBody blob,
DateCreated varchar(10),
DateEdited varchar(10),
Primary Key(Article_ID)
);

Alter Table CategoryArticle Add Constraint fk_CategoryArticle_Article 
Foreign Key (Article_ID) References Article(Article_ID);

Alter Table Article Add Constraint fk_Article_User 
Foreign Key (User_ID) References `User`(User_ID);

Alter Table ArticleTag Add Constraint fk_ArticleTag_Article 
Foreign Key (Article_ID) References Article(Article_ID);

Alter Table `Comment` Add Constraint fk_Comment_Article 
Foreign Key (Article_ID) References Article(Article_ID);

Insert Into Article (Article_ID, User_ID, Approval, ArticleName, Summmary, TextBody, DateCreated, DateEdited) Values(1, 1, true, "Artical on Epiphany", "Best. Beer. Ever.", "Body of article", "11-16-16", ""),
(2, 2, true, "Artical on Substance", "Very hard to get.. but worthing it you can.", "Body of article", "11-16-16", ""),
(3, 2, true, "Artical on Space Gose", "Fantastic on a hot summer day.", "Body of article", "11-16-16", "");