/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  apprentice
 * Created: Nov 28, 2016
 */

use GLBC_test;
drop table if exists Beer;
create table Beer (Beer_ID int Not Null auto_increment,Brewery_ID int Not Null,Approval bool Not Null,`BeerName` varchar(50),Summary varchar(180),TextBody blob,Style varchar(30),Hop varchar(30),ABV varchar(10),IBU varchar(10),Primary Key(Beer_ID));
--recreate fk relationships
Alter Table Beer Add Constraint fk_Beer_Brewery Foreign Key (Brewery_ID) References Brewery(Brewery_ID);
--insert mock data
insert into Beer (Beer_ID, Brewery_ID, Approval, BeerName, Summary, TextBody, Style, Hop, ABV, IBU) Values(1, 1, true, "Epiphany", "DESCRIPTION", "This is a body to the static page", "Imperial IPA", "Citra", "8.00", "100");
insert into Beer (Beer_ID, Brewery_ID, Approval, BeerName, Summary, TextBody, Style, Hop, ABV, IBU) Values(2, 2, true, "The Substance", "DESCRIPTION", "This is a body to the static page", "American IPA", "Centennial", "6.60", "49.27");
insert into Beer (Beer_ID, Brewery_ID, Approval, BeerName, Summary, TextBody, Style, Hop, ABV, IBU) Values(3, 3, true, "Space Gose", "DESCRIPTION", "This is a body to the static page", "Gose", "", "4.90", "");