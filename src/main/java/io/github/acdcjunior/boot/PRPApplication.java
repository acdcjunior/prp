package io.github.acdcjunior.boot;

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

        @RequestMapping("/")
        public ResponseEntity<String> prp() {
            return ResponseEntity.ok("It works!!<br> " + String.join(";", "java", "8", "test") + " Now: " + java.time.ZonedDateTime.now());
        }

    }

}
