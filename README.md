Services:
1.electricalbikes/ bikes
Methods:
 	1. GET:
		- Get bikes of a stations sorted by kms asc  /sortedbikesbykm/:idStation
 		- Get bike /getbike/:idStation/:idUser
 		- Get bikes by user /getbikes/:idUser
	2. POST:
		- Add user /adduser
 		- Add station /addstation
 		- Add bike /addbike

Models:
1. Bike:
 	String idBike;
     String description;
     double kms;
     String idStation;

 2. Station:
 	String idStation;
     String description;
     int max;
     double lat;
     double lon;
 3. User:
    String idUser;
     String name;
     String surname;
