# springcacherestapi
springcacherestapi
   This spring framework provides supports for adding caching to an application and clear  the cache. 
   
# Prerequisites
  * Java 1.8+
  * Gradle 6.0

# Compile & build
  gradle clean build 
# Create  a jar to deploy
   gradle clean build bootJar  
## API Details   

Below Get API to access trip data
 
http://<url>/tripsbyday  

http://<url>/tripsbymed


## To clear the cache

Send a POST request to 
http://<url>/clear


Look for "Reading from DB" for cache miss in log

You can use Amazon RDS for DB and AWS memcache for redis

Add EKS for container based solutions

Improvement
 Can implement DDB+DAX for horizontal Scale and lambda for serverless solution