package com.springdemo.helloworld;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // Allows us to test the application with a random port
public class CheckHTTPResponse {

    @LocalServerPort                                                        // Injects the port field with the random port number
    private int port;

    @Autowired                                                              // Gets the TestRestTemplate bean
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldPassIfStringMatches() {
        // Sends a GET request to the root URL and expects the response to be "Hello, World!"
        String response = this.testRestTemplate.getForObject("http://localhost:" + port + "/", String.class);
        assert(response.equals("Hello, World!"));
    }

}
