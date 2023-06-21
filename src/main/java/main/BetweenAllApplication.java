package main;

import main.pe.com.betweenAll.entities.*;
import main.pe.com.betweenAll.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

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
        return args -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            User user=userRepository.save(new User("Carlos","Alipio","DNI",12345678,
                    "999252444","carl@gmail.com","123456","","lima"));
            Card card=cardRepository.save(new Card("Alipio",Long.valueOf("123456781234"),123,dateFormat.parse("2020-03-23"),
                    "Activo",user));
            Category category= categoryRepository.save(new Category("POP","Activo"));
            UserCategory userCategory=userCategoryRepository.save(new UserCategory(user,category));
            SocialEvent socialEvent=socialEventRepository.save(new SocialEvent("Morat","","Lima","no lleven nada",user,category));
            DateSocialEvent dateSocialEvent = dateSocialEventRepository.save(new DateSocialEvent(dateFormat.parse("2023-03-23"),
                    LocalTime.of(10,10,10),LocalTime.of(18,10,10),socialEvent));
            ZoneEvent zoneEvent= zoneEventRepository.save(new ZoneEvent("Platino",123.5,50,dateSocialEvent));
            Group group= groupRepository.save(new Group("Los inmortales","Solo gente seria","",category));
            GroupUser groupUser=groupUserRepository.save(new GroupUser(user,group));
            Purchase purchase=purchaseRepository.save(new Purchase(Long.valueOf("20"),dateFormat.parse("2023-03-23"),user,card));
            Ticket ticket=ticketRepository.save(new Ticket(purchase,zoneEvent));
        };
    }
}