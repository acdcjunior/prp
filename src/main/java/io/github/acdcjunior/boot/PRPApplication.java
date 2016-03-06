package io.github.acdcjunior.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class PRPApplication {

    public static void main(String[] args) {
        SpringApplication.run(PRPApplication.class, args);
    }

    @RestController
    static class PRPApplicationController {

        @Value("${luna}")
        private String luna;

        @RequestMapping("/")
        public ResponseEntity<String> prp() {
            return ResponseEntity.ok(
                    "It works!!<br> " + String.join(";", "java", "8", "test") +
                    "<br><br>Now: " + java.time.ZonedDateTime.now() +
                    "<br><br>Luna is "+luna);
        }

    }

}
