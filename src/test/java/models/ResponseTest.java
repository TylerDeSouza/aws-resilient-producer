package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResponseTest {

    @Test
    void getBody() {
        Response response = new Response("Test Body", 200);
        assertEquals(response.getBody(), "Test Body");

    }

    @Test
    void getStatusCode() {
        Response response = new Response("Test Body", 200);
        assertEquals(response.getStatusCode(), 200);
    }
}