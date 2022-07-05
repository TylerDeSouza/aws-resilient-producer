package models;

import org.junit.jupiter.api.Test;

class SimpleProducerTest {

    @Test
    void send() {
        SimpleProducer simpleProducer = new SimpleProducer();
        simpleProducer.send();
    }
}