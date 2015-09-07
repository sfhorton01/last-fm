package com.gstv.scott.runner;

import com.gstv.scott.api.MusicInformationController;
import com.gstv.scott.core.MusicRepository;
import com.gstv.scott.lastfm.LastFMRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;


/**
 * An application to play around with the practice web service
 */
@SpringBootApplication
@ComponentScan(basePackageClasses = MusicInformationController.class)
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject("http://localhost:8080/topalbums/" + strings[0], String.class);
        log.info(json);
    }

    @Bean
    public MusicRepository musicRepository() {
        return new LastFMRepository(restTemplate(), "http://ws.audioscrobbler.com/2.0?format=json", System.getProperty("api_key"));
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
