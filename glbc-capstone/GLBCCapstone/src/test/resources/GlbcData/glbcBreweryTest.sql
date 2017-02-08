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
drop table if exists Brewery;
Create Table Brewery(Brewery_ID int Not Null auto_increment,Approval bool, BreweryName varchar(50),Summary varchar(180),TextBody blob,City varchar(50),State varchar(50),Brewmaster varchar(50),Primary Key(Brewery_ID));

--insert mock data
Insert Into Brewery(Brewery_ID, Approval, BreweryName, Summary, TextBody, City, State, Brewmaster) Values(1, true, "Foundation Brewing Company", "THIS IS A DESCRIPTION", "This is a body to the static page", "Portland", "Maine", "Jason Perkins");
Insert Into Brewery(Brewery_ID, Approval, BreweryName, Summary, TextBody, City, State, Brewmaster) Values(2, true, "Bissell Brothers Brewing", "THIS IS A DESCRIPTION", "This is a body to the static page", "Portland", "Maine", "Noah Bissell");
Insert Into Brewery(Brewery_ID, Approval, BreweryName, Summary, TextBody, City, State, Brewmaster) Values(3, true, "Barreled Souls Brewing Company", "THIS IS A DESCRIPTION", "This is a body to the static page", "Saco", "Maine", "Matthew Mills");
