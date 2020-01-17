# SpringBootDemo

This is a demo application to demostrate water order syustem for farmers. This application is biult on SprintBoot framework. 

Database structure consideration.

Farmer_detail
----------------------------------------
farmer_id Integer
Name String
Address String
Email String

Farm_detail
------------------------------------------
farm_id Integer
Address String
farmer_id (ref key) Integer

Order_detail
-----------------------------------------
order_id Integer
farm_id
farmer_id
status
duration
startdate
enddate
comments

