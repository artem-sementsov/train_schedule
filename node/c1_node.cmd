set C1_IP=localhost
set C2_IP=10.100.0.203
set AMQ_HOME=C:\apache-activemq-5.13.3
set REST_HOME=C:\Users\Artem\Documents\rest
set NODE_HOME=C:\Users\Artem\Documents\node

cd %NODE_HOME%
start "node" mvn ^
   -Dspring.activemq.broke-url=failover:(tcp://%C1_IP%:61616,tcp://%C2_IP%:61616) ^
   -Dspring.datasource.url=jdbc:postgresql://%C1_IP%:5432/train_schedule ^
   clean spring-boot:run