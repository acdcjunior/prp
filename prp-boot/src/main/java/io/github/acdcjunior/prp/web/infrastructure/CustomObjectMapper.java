package io.github.acdcjunior.prp.web.infrastructure;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

import java.time.LocalDate;

public class CustomObjectMapper extends ObjectMapper {

    public CustomObjectMapper() {
        configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        addJavaTimeLocalDateSerialization();
    }

    private void addJavaTimeLocalDateSerialization() {
        // serializes java.time.LocalDate to yyyy-MM-dd
        CustomSerializerFactory sf = new CustomSerializerFactory();
        sf.addSpecificMapping(LocalDate.class, new LocalDateSerializer());
        this.setSerializerFactory(sf);
    }

}