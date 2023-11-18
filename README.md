# TPEArqui

Documentar los endpoint RE$ST con OPEN API:

ScooterMs
http://localhost:8002/swagger-ui/index.html

MaintenanceMs
http://localhost:8004/swagger-ui/index.html

AdministrationMs
http://localhost:8005/swagger-ui/index.html


#API Rest - Endpoints and ports

1. Un primer análisis del problema reveló las siguientes funcionalidades (si bien podrían existir otras funcionalidades no detectadas, o variaciones de las planteadas):  

a. Registrar monopatín en mantenimiento (debe marcarse como no disponible para su uso)   
   PUT scooter/{id}/status  

b. Registrar fin de mantenimiento de monopatín  
Pasa status por el body  
   PUT scooter/{id}/status  

c. Ubicar monopatín en parada (opcional)  
d. Agregar monopatín  
   POST scooter  

e. Quitar monopatín  
   DELETE scooter/{id}   
   
f. Registrar parada  
   POST stop  

g. Quitar parada  
   DELETE stop/{id}  

h. Definir precio  
   POST tariff  

i. Definir tarifa extra para reinicio por pausa extensa  
   POST tariff  

j. Anular cuenta  
   Delete lógico.  
   Pasa status por el body  
   PUT user/id/{id}/status   

k. Generar reporte de uso de monopatines por kilómetros  
   GET scooter/reportByKm  

l. Generar reporte de uso de monopatines por tiempo con pausas  
   GET scooter/reportByTimeWithPauses    

m. Generar reporte de uso de monopatines por tiempo sin pausas  
   GET scooter/reportByTotalTime    

2. Diseñar un backend básico de (micro-)servicios que permita realizar el ABM de las entidades (para así poblar y gestionar la(s) base(s) de datos) y dar soporte a las principales
   funcionalidades antes mencionadas. En este diseño, considerar que cada microservicio contará (preferentemente) con una base de datos separada.  

administrationMS  
http://localhost:8005/administration 
http://localhost:8005/account  
http://localhost:8005/user  

maintenanceMS  
http://localhost:8004/maintenance  

scooterMS  
http://localhost:8002/scooter  
http://localhost:8002/stop  
http://localhost:8002/tariff  
http://localhost:8002/trip  


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

d. Como administrador quiero consultar el total facturado en un rango de meses de cierto año.  
   GET trip/year/{year}/fromMonth/{month1}/ToMonth/{month2}  
   GET administration/billingReport/year/{year}/fromMonth/{month1}/ToMonth/{month2}  

e. Como administrador quiero consultar la cantidad de monopatines actualmente en operación, versus la cantidad de monopatines actualmente en mantenimiento.  
   GET scooter/reportByStatus    

f. Como administrador quiero hacer un ajuste de precios, y que a partir de cierta fecha el sistema habilite los nuevos precios.  
   POST administration/tariff  

g. Como usuario quiero un listado de los monopatines cercanos a mi zona, para poder encontrar un monopatín cerca de mi ubicación  
   GET scooter/latitude/{latitude}/longitude/{longitude}  

