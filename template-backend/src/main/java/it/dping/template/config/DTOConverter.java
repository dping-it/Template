package it.dping.template.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DTOConverter {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
