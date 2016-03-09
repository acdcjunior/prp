package io.github.acdcjunior.prp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @Value("${aa.bb.cc}")
    private String ymlProp;

    @RequestMapping("/")
    public ResponseEntity<String> prp() {
        return ResponseEntity.ok(
                "It works!!<br> " + String.join(";", "java", "8", "test") +
                        "<br><br>Now: " + java.time.ZonedDateTime.now() +
                        "<br><br>Luna is " + ymlProp);
    }

}