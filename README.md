# HOW TO START
- First, install Kafka, ORSM and Java.
- Start Kafka zookeeper, the Kafka server.
- Create a new topic, namely taxi_topic_1.
- Follow the instructions on ORSM's github page to start the ORSM server on port 5000.
- Start the code using Java Spring Boot.
- http://127.0.0.1:8081/kafka/driver/publish to publish data for driver side, the required parameters are  
  * 'id': int
  * 'longitude': double
  * 'latitude': double
  * 'seat': int
  * 'hexAddr': String
- http://127.0.0.1:8081/kafka/user/publish to publish data for driver side, the required parameters are
    * 'longitude': double
    * 'latitude': double
    * 'seat': int
    * 'hexAddr': String
- The hexAddr should be indexed using H3 with resolution of 10.    