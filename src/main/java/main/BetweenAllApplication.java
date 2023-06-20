package main;

import main.pe.com.betweenAll.entities.ZoneEvent;
import main.pe.com.betweenAll.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BetweenAllApplication {
    public static void main(String[] args) {
        SpringApplication.run(BetweenAllApplication.class, args);
    }

    @Bean
    public CommandLineRunner mappingDemo(
        CardRepository cardRepository,
        CategoryRepository categoryRepository,
        DateSocialEventRepository dateSocialEventRepository,
        GroupRepository groupRepository,
        GroupUserRepository groupUserRepository,
        PurchaseRepository purchaseRepository,
        SocialEventRepository socialEventRepository,
        TicketRepository ticketRepository,
        UserCategoryRepository userCategoryRepository,
        UserRepository userRepository,
        ZoneEventRepository zoneEventRepository
    ){
        return args -> {};
    }



}