The whole project can not be public, but here are some basic templates and sometimes they can be used for convenience.


For HurricaneReport :
1. create a HurricaneReport.jar with external libraries included in Eclipse.

   a.  Right mouse on project -> Export -> Runnable jar 
   
   b.  Choose Launch conifg and export destination
   
   c.  click-Package required libraries into generate JAR
   
   d.  click-finish

2. HurricaneReport.jar :
  direct to the path for the HurricaneReport.jar
  java -jar HurricaneReport.jar
 
  
  
For HurricaneReport-DB :
1. java -jar reportDb.jar init /Users/air/Desktop/data.txt
2. java -jar reportDb.jar create /Users/air/Desktop



For UpdateFromNCBI :
set arguments before run to avoid java heap space not enough: -Xms2048m -Xmx4096m


