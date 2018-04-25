set C1_IP=10.100.0.202
set C2_IP=10.100.0.203
set AMQ_HOME=C:/activemq
set NODE_HOME=C:\Users\aleks\Documents\IdeaProjects\train_schedule

start "activemq" %AMQ_HOME%\bin\activemq start

cd %NODE_HOME%
mvn ^
   -Dspring.activemq.broke-url=failover:(tcp://%C1_IP%:61616,tcp://%C2_IP%:61616) ^
   -Dspring.datasource.url=jdbc:postgresql://%C2_IP%:5432/train_schedule ^
   clean spring-boot:run
