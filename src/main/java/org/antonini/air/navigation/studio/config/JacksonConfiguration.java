package org.antonini.air.navigation.studio.config;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.antonini.air.navigation.studio.domain.util.CustomDateTimeDeserializer;
import org.antonini.air.navigation.studio.domain.util.CustomDateTimeSerializer;
import org.antonini.air.navigation.studio.domain.util.CustomLocalDateSerializer;
import org.antonini.air.navigation.studio.domain.util.ISO8601LocalDateDeserializer;
import com.fasterxml.jackson.datatype.joda.JodaModule;

@Configuration
public class JacksonConfiguration {

    @Bean
    public JodaModule jacksonJodaModule() {
        JodaModule module = new JodaModule();
        module.addSerializer(DateTime.class, new CustomDateTimeSerializer());
        module.addDeserializer(DateTime.class, new CustomDateTimeDeserializer());
        module.addSerializer(LocalDate.class, new CustomLocalDateSerializer());
        module.addDeserializer(LocalDate.class, new ISO8601LocalDateDeserializer());
        return module;
    }

    @Bean
    public JtsModule jacksonJTSModule() {
        JtsModule jtsModule = new JtsModule();
        return jtsModule;
    }
}
