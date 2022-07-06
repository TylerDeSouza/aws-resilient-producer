package services;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

@Slf4j
public class SimpleProducerImpl implements SimpleProducer {
    public static Producer<String, String> producer;
    public static Properties props;
    public static String topicName;

    public SimpleProducerImpl() {
        //TODO: Store these in AWS secret manager and retrieve from there instead of env variable
        String username = System.getenv().getOrDefault("USERNAME", "username");
        String password = System.getenv().getOrDefault("PASSWORD", "password");
        String srApiKey = System.getenv().getOrDefault("SR_API_KEY", "SR_API_KEY");
        String srApiSecret = System.getenv().getOrDefault("SR_API_SECRET", "SR_API_SECRET");

        //Assign topicName to string variable
        topicName = System.getenv().getOrDefault("TOPIC_NAME", "test_topic");

        // create instance for properties to access producer configs
        props = new Properties();

        //Assign localhost id
        props.put("bootstrap.servers", System.getenv().getOrDefault("BOOTSTRAP_SERVERS", "localhost:9092"));

        //Set acknowledgements for producer requests.
        props.put("acks", "all");

        //If the request fails, the producer can automatically retry,
        props.put("retries", 0);

        //Specify buffer size in config
        props.put("batch.size", 16384);

        //Reduce the no of requests less than 0
        props.put("linger.ms", 1);

        //The buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33554432);

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        props.put("session.timeout.ms", 45000);

        props.put("client.dns.lookup", "use_all_dns_ips");

        props.put("sasl.mechanism", "PLAIN");

        props.put("sasl.jaas.config", String.format("org.apache.kafka.common.security.plain.PlainLoginModule required username='%s' password='%s';", username, password));

        props.put("security.protocol", "SASL_SSL");

        props.put("schema.registry.url", System.getenv().getOrDefault("SR_ENDPOINT", "SR_ENDPOINT"));

        props.put("basic.auth.credentials.source", "USER_INFO");

        props.put("basic.auth.user.info", String.format("%s:%s", srApiKey, srApiSecret));

        producer = new KafkaProducer<>(props);

    }

    public void send(String key, String value) {
        for (int i = 0; i < 10; i++) {
            log.info("Sending message with key-value pair {}:{} to topic {}", key, value, topicName);
            producer.send(new ProducerRecord<>(topicName, key, i + "-" + value));
        }
    }

}
