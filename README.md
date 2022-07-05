## aws-resilient-producer

Exercise to practice creation of a Kafka producer within AWS

---

### Local Kafka Setup and Helpful Links

- #### Kafka Setup Reference https://kafka.apache.org/quickstart

### Tutorials

- ##### Simple Producer/Consumer https://www.tutorialspoint.com/apache_kafka/apache_kafka_simple_producer_example.htm
- #### Command Line Instructions http://cloudurable.com/blog/kafka-tutorial-kafka-from-command-line/index.html
- #### Fault Tolerance http://cloudurable.com/blog/kafka-tutorial-kafka-failover-kafka-cluster/index.html
- #### Kafka Segment Information https://strimzi.io/blog/2021/12/17/kafka-segment-retention/

### Prerequisites

- **Install Kafka:** ``brew install kafka``
- **Local Kafka folders (MacOS)**
  - **Property Files:** /usr/local/etc/kafka
  - **Log Files:**: /usr/local/var/lib/kafka-logs

----

### Exercise - Produce and consume a message locally

The following instructions will show you how to produce and consume a message locally.

<mark style="background: #C5F4E0">We will need to have 4 different terminal tabs running - one for zookeeper, one for
our broker/server, one for our producer, and one for our consumer.</mark>

**1. Start zookeeper - Perform in Terminal 1**

- ``zookeeper-server-start /usr/local/etc/kafka/zookeeper.properties``

**2. Start Kafka server - Perform in Terminal 2**

- ``kafka-server-start /usr/local/etc/kafka/server.properties``

**3. Create topic - Perform in Terminal 3**

- ``kafka-topics --create --bootstrap-server localhost:9092 --topic test``

**4. Write events to topic - Perform in Terminal 3**

- ``kafka-console-producer --topic test --bootstrap-server localhost:9092``
- Provide message input, ex:
  - This is my first event
  - This is my second event

**5. Read events from topics - Perform in Terminal 4**

- ``kafka-console-consumer --topic test --from-beginning --bootstrap-server localhost:9092``
- <mark style="background: #C5F4E0">--from-beginning</mark>

**6. When completed, shutdown each terminal using ``Ctrl+C``**

---

### Optional

**Clean up logs/environment when done**

- ``rm -rf /tmp/kafka-logs /tmp/zookeeper``

**Change configuration of topic**

We have created the topic ``test`` with the default configurations (i.e. 1 partition, 1 broker, 7 day retention period).
We can update the configurations of test using the following:

- Reference: "https://docs.confluent.io/platform/current/kafka/post-deployment.html#modifying-topics"

**Must be done using kafka-configs cli**

- kafka-configs install: ``brew install kafka-configs``
- Example: Change retention period of topic to 1000 milliseconds
  - ``kafka-configs --bootstrap-server localhost:9092 --alter --entity-type topics --entity-name test --add-config retention.ms=1000``
