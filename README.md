# Problem/system description
A company wants to launch a business to allow the rental of electronic scooters at different stops in a capital city, for which it requires the development of a mobile application for the users of the service, and additionally a Web application for the corresponding management. The service consists of having a fleet of e-scooters, initially parked at different previously defined stops, within the city center and nearby areas. E-scooters are picked up and left at these stops, this is a basic condition for the operation of the service. The operation of the service is described below.
In order to use the e-scooter, the user must create an account in the app, associated with a Mercado Pago account. Prior to using the service, you must have loaded an amount of money into your account,
which will be deducted depending on the time of use of the e-scooter. The same Mercado Pago account can be used for several service accounts.
An account may have several associated users who will use the credits loaded into the account, and a user may be associated with more than one account. Each user will have a name and must register their
cell phone number, valid email, first and last name. The account will have an identification number and a registration date.
Once the account has money loaded, the user will be able to activate a e-scooter for use, using a QR code reader. At that moment a trip associated with the user's account will be generated.
that you are using your app, recording start date and time. The use of the skateboard is by time, the credit begins to be consumed when the skateboard is activated, and this will allow it to turn on in
that moment. From there the service user will be able to use the e-scooter, and once they no longer require it, they must leave it at a previously established stop. At this moment, select the option to cut the service, once the e-scooter is parked, ending the trip. At the end of the trip, the date and time of completion and the kilometers traveled will be recorded. It should be noted that the app should not allow you to end a trip if it does not detect, using the GPS that the skateboard has, that it is at a permitted stop.
If the user needed to stop for no more than 15 minutes at some intermediate point in the trip, the app will have a Pause option. With this option, a pause associated with the trip is recorded, to establish that the e-scooter is not being used, although credits are still being spent.

In this way, the actual use of the e-scooter can be established, and the app allows it to be turned off, but does not unassign it to the current account. Once the pause is finished, the user can indicate it through the app, to be able to turn on the skateboard. If the 15 minutes pass, the skateboard will automatically be considered in use again, and a larger amount of credit will begin to be collected until the end of the trip.
The e-scooter will have a GPS and is uniquely identified with ID, so you can determine where each e-scooter is at all times. This way, if a user needs a e-scooter they can find the closest one through an interactive map in the app that shows the e-scooters in the area.
For the maintenance of the e-scooters, a Web application will be created to record all the maintenance actions carried out by the Skateboard Maintenance Managers. To establish whether a skateboard requires maintenance, the time of use and the kilometers traveled are considered, which requires the recording of this information, as well as the generation of reports.
associated with the use of e-scooters, with respect to kilometers and time of use, including time with breaks and without breaks. It is necessary to know if a e-scooter is undergoing maintenance, or is enabled for use, when it is at a defined stop after completing maintenance.
In addition, the e-scooter Manager is the one who manages the skateboards and stops in the application (e.g. adding, removing, updating data as required), also sets the normal fare prices and extras for restarting long breaks. On the other hand, it is capable of canceling accounts when for some reason it is considered necessary.

#API Rest - Endpoints and ports

a. Registrar monopatín en mantenimiento (debe marcarse como no disponible para su uso)  

Pasa status por el body  
   PUT scooter/{id}/status  
   PUT administration/scooter/{id}/status  
   PUT maintenance/scooter/{id}/status  

b. Registrar fin de mantenimiento de monopatín  
Pasa status por el body  
   PUT scooter/{id}/status  
   PUT administration/scooter/{id}/status  
   PUT maintenance/scooter/{id}/status  

c. Ubicar monopatín en parada (opcional)  
d. Agregar monopatín  
   POST scooter  
   POST administration/scooter   

e. Quitar monopatín  
   DELETE scooter/{id}   
   DELETE administration/scooter/{id}  

f. Registrar parada  
   POST stop  
   POST administration/stop  

g. Quitar parada  
   DELETE stop/{id}  
   DELETE administration/stop/{id}  

h. Definir precio  
   POST tariff  
   POST administration/tariff  

i. Definir tarifa extra para reinicio por pausa extensa  
   POST tariff  
   POST administration/tariff  

j. Anular cuenta  
   Delete lógico.  
   Pasa status por el body  
   PUT user/id/{id}/status  
   PUT administration/user/{id}/status  

k. Generar reporte de uso de monopatines por kilómetros  
   GET scooter/reportByKm  
   GET administration/reportByKm  

l. Generar reporte de uso de monopatines por tiempo con pausas  
   GET scooter/reportByTimeWithPauses  
   GET maintenance/reportByTimeWithPauses  

m. Generar reporte de uso de monopatines por tiempo sin pausas  
   GET scooter/reportByTotalTime  
   GET maintenance/reportByTotalTime  

2. Diseñar un backend básico de (micro-)servicios que permita realizar el ABM de las entidades (para así poblar y gestionar la(s) base(s) de datos) y dar soporte a las principales
   funcionalidades antes mencionadas. En este diseño, considerar que cada microservicio contará (preferentemente) con una base de datos separada.  

administrationMS  
http://localhost:8005/administration  

maintenanceMS  
http://localhost:8004/maintenance  

scooterMS  
http://localhost:8002/scooter  
http://localhost:8002/stop  
http://localhost:8002/tariff  
http://localhost:8002/trip  

userMS  
http://localhost:8003/account  
http://localhost:8003/user  

3. Implementar los siguientes servicios/reportes:  
   a. Como encargado de mantenimiento quiero poder generar un reporte de uso de monopatines por kilómetros para establecer si un monopatín requiere de mantenimiento.
Este reporte debe poder configurarse para incluir (o no) los tiempos de pausa.  

   GET maintenance/reportByTimeWithPauses  
   GET maintenance/reportByTotalTime  

b. Como administrador quiero poder anular cuentas para inhabilitar el uso momentáneo de la misma.  
   Delete lógico.  
   Pasa status por el body  
   PUT administration/user/{id}/status  

c. Como administrador quiero consultar los monopatines con más de X viajes en un cierto año.  
   GET scooter/minimumNumberOfTrips/{number}/year/{year}  
   GET administration/minimumNumberOfTrips/{number}/year/{year}  

d. Como administrador quiero consultar el total facturado en un rango de meses de cierto año.  
   GET trip/year/{year}/fromMonth/{month1}/ToMonth/{month2}  
   GET administration/billingReport/year/{year}/fromMonth/{month1}/ToMonth/{month2}  

e. Como administrador quiero consultar la cantidad de monopatines actualmente en operación, versus la cantidad de monopatines actualmente en mantenimiento.  
   GET scooter/reportByStatus  
   GET administration/reportByScooterStatus  

f. Como administrador quiero hacer un ajuste de precios, y que a partir de cierta fecha el sistema habilite los nuevos precios.  
   POST administration/tariff  

g. Como usuario quiero un listado de los monopatines cercanos a mi zona, para poder encontrar un monopatín cerca de mi ubicación  
   GET scooter/latitude/{latitude}/longitude/{longitude}  
   GET user/scooter/latitude/{latitude}/longitude/{longitude}  

