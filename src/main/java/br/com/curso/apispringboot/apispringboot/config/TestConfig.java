package br.com.curso.apispringboot.apispringboot.config;

import br.com.curso.apispringboot.apispringboot.service.DBService;
import br.com.curso.apispringboot.apispringboot.service.EmailService;
import br.com.curso.apispringboot.apispringboot.service.MockEmailService;
import br.com.curso.apispringboot.apispringboot.service.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        dbService.instantiateTestDatabase();
        return true;
    }

//    @Bean
//    public EmailService emailService(){
//        return new MockEmailService();
//    }

    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }

}
