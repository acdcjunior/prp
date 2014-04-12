package net.acdcjunior.prp.web.infrastructure;

import java.text.SimpleDateFormat;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

public class CustomObjectMapper extends ObjectMapper {

    public CustomObjectMapper() {
        configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);            
        setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);            
    }

}