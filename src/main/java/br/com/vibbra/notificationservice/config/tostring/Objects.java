package br.com.vibbra.notificationservice.config.tostring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Objects {

    public static String toString(Object o) {
        try {
            return new ObjectMapper()
                    .registerModule(new Hibernate5JakartaModule())
                    .setAnnotationIntrospector(new ToStringIgnoreIntrospector())
                    .writeValueAsString(o);
        } catch (Exception e) {
            log.error("Error on toString", e);
            return "BLANK";
        }
    }
}
