package software.spring.caching.presentation.controller;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TripControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;



    private static Integer crudId;


    @BeforeEach
    void initUseCase() {

    }

    @Test
    @Order(1)
    public void getTripsbydate() throws Exception {
        String uri="http://localhost:" + port + "/tripsbyday";
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("pickup_date","2013-12-31" );
        String response = restTemplate.getForObject(uri,String.class,map);
        assertNotNull(response);
    }

    @Test
    @Order(2)
    public void getTripsbymodel() throws Exception {
        String uri="http://localhost:" + port + "/tripsbymed";
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("medallions","D7D598CD99978BD012A87A76A7C891B7,5455D5FF2BD94D10B304A15D4B7F2735" );
        String response = restTemplate.getForObject(uri,String.class,map);
        assertNotNull(response);
    }

    @Test
    @Order(3)
    public void clearCache() throws Exception {
        String uri="http://localhost:" + port + "/tripsbymed";
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("clear","clear" );
        String response = restTemplate.postForObject(uri, map,String.class);
        assertEquals(response,"success");

    }



}