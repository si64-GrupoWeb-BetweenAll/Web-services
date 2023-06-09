package main;

import main.pe.com.betweenAll.entities.*;
import main.pe.com.betweenAll.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

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
        ZoneEventRepository zoneEventRepository,
        AuthorityRepository authorityRepository
    ){
        return args -> {

            authorityRepository.saveAll(List.of(
                            new Authority(AuthorityName.ROLE_ADMIN)
                    )
            );

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            User user1=userRepository.save(new User("Carlos","Alipio","DNI",12345678,
                    "999252444","carl@gmail.com",new BCryptPasswordEncoder().encode("123456"),"https://www.debate.com.mx/__export/1637600677199/sites/debate/img/2021/11/22/arcangel_crop1637600571455.jpg_1902800913.jpg","lima",
                    List.of(
                            authorityRepository.findByName(AuthorityName.ROLE_ADMIN)
                    )
            ));

            User user2=userRepository.save(new User("Camila","Alessandra","DNI",74775218,
                    "942764237","cam@gmail.com",new BCryptPasswordEncoder().encode("1234567"),"https://elcomercio.pe/resizer/-xgss_aaDX5td4CusY4lHs9o77k=/1200x1200/smart/filters:format(jpeg):quality(75)/cloudfront-us-east-1.images.arcpublishing.com/elcomercio/6V6ZSTT7TRFGROOAAQXLNNX5GY.jpg","lima",
                    List.of(
                            authorityRepository.findByName(AuthorityName.ROLE_ADMIN)

            )));

            User user3=userRepository.save(new User("Neil","Angel","DNI",74125896,
                    "942764237","neil@gmail.com",new BCryptPasswordEncoder().encode("12345"),"","abancay",
                    List.of(
                            authorityRepository.findByName(AuthorityName.ROLE_ADMIN)
                    )
            ));


            Card card=cardRepository.save(new Card("Alipio",Long.valueOf("123456781234"),123,"03/23",
                    "Activo",user1));
            /*Card card2=cardRepository.save(new Card("Alipio",Long.valueOf("123456781234"),123,"03/23",
                    "Activo",user2));*/
            Card card3=cardRepository.save(new Card("Alipio",Long.valueOf("123456781234"),123,"03/23",
                    "Activo",user3));

            Category category= categoryRepository.save(new Category("Social","Activo"));
            Category category1= categoryRepository.save(new Category("Cultural","Activo"));
            Category category2= categoryRepository.save(new Category("Deportivo","Activo"));
            Category category3= categoryRepository.save(new Category("Empresarial","Activo"));
            Category category4= categoryRepository.save(new Category("Academico","Activo"));
            Category category5= categoryRepository.save(new Category("Recaudacion","Activo"));
            Category category6= categoryRepository.save(new Category("Religioso","Activo"));
            Category category7= categoryRepository.save(new Category("Politico","Activo"));

            UserCategory userCategory3=userCategoryRepository.save(new UserCategory(user1,category3));
            UserCategory userCategory=userCategoryRepository.save(new UserCategory(user1,category));
            UserCategory userCategory1=userCategoryRepository.save(new UserCategory(user3,category1));
            UserCategory userCategory2=userCategoryRepository.save(new UserCategory(user1,category2));


            Group group= groupRepository.save(new Group("Los inmortales","Solo gente seria","https://www.bbva.com/wp-content/uploads/2020/05/festival2.jpg",category,user1));
            Group group2= groupRepository.save(new Group("Los reales","Solo gente real","https://www.bbva.com/wp-content/uploads/2020/05/festival2.jpg",category,user1));
            Group group3= groupRepository.save(new Group("Los bandidos","Solo gente bandida","https://blog.oncosalud.pe/hubfs/Por-qu%C3%A9-nos%20causa-felicidad-ir%20a-un-concierto.jpg",category,user2));

            //Group group= groupRepository.save(new Group("Los inmortales","Solo gente seria","https://www.bbva.com/wp-content/uploads/2020/05/festival2.jpg"));
            //Group group2= groupRepository.save(new Group("Los reales","Solo gente real","https://www.bbva.com/wp-content/uploads/2020/05/festival2.jpg"));
            //Group group3= groupRepository.save(new Group("Los bandidos","Solo gente bandida","https://blog.oncosalud.pe/hubfs/Por-qu%C3%A9-nos%20causa-felicidad-ir%20a-un-concierto.jpg"));



            GroupUser groupUser=groupUserRepository.save(new GroupUser(user1,group));
            GroupUser groupUser2=groupUserRepository.save(new GroupUser(user2,group));
            GroupUser groupUser3=groupUserRepository.save(new GroupUser(user2,group3));

            SocialEvent socialEvent1=socialEventRepository.save(new SocialEvent("Morat","https://akamai.sscdn.co/uploadfile/letras/fotos/a/1/1/7/a11750e4abcbf07109b386364cc190f7.jpg","Lima","no lleven nada",user1,category));
            SocialEvent socialEvent2=socialEventRepository.save(new SocialEvent("Grupo 5","https://akamai.sscdn.co/uploadfile/letras/fotos/a/1/1/7/a11750e4abcbf07109b386364cc190f7.jpg","Lima","no lleven nada",user1,category));

            DateSocialEvent dateSocialEvent1 = dateSocialEventRepository.save(new DateSocialEvent(dateFormat.parse("2023-02-13"),
                    LocalTime.of(10,10,10),LocalTime.of(18,10,10),socialEvent1));
            DateSocialEvent dateSocialEvent2 = dateSocialEventRepository.save(new DateSocialEvent(dateFormat.parse("2023-04-18"),
                    LocalTime.of(10,10,10),LocalTime.of(18,10,10),socialEvent2));
            DateSocialEvent dateSocialEvent3 = dateSocialEventRepository.save(new DateSocialEvent(dateFormat.parse("2023-01-21"),
                    LocalTime.of(10,10,10),LocalTime.of(18,10,10),socialEvent1));

            ZoneEvent zoneEvent1= zoneEventRepository.save(new ZoneEvent("Platino",123.5,50,dateSocialEvent1));
            ZoneEvent zoneEvent2= zoneEventRepository.save(new ZoneEvent("VIP",235.5,540,dateSocialEvent2));
            ZoneEvent zoneEvent3= zoneEventRepository.save(new ZoneEvent("General",135.5,540,dateSocialEvent3));
            ZoneEvent zoneEvent4= zoneEventRepository.save(new ZoneEvent("Meet and Greet",210.0,540,dateSocialEvent3));


            Purchase purchase1=purchaseRepository.save(new Purchase(Long.valueOf("20"),dateFormat.parse("2023-03-23"),user1,card,200.0));
            Purchase purchase2=purchaseRepository.save(new Purchase(Long.valueOf("20"),dateFormat.parse("2023-03-23"),user1,card,300.0));
            Purchase purchase3=purchaseRepository.save(new Purchase(Long.valueOf("20"),dateFormat.parse("2023-03-23"),user2,card,530.0));
            Purchase purchase4=purchaseRepository.save(new Purchase(Long.valueOf("20"),dateFormat.parse("2023-03-23"),user3,card3,564.0));


            Ticket ticket1=ticketRepository.save(new Ticket(purchase1,zoneEvent1));
            Ticket ticket2=ticketRepository.save(new Ticket(purchase2,zoneEvent2));
            Ticket ticket3=ticketRepository.save(new Ticket(purchase3,zoneEvent2));
            Ticket ticket4=ticketRepository.save(new Ticket(purchase4,zoneEvent4));

    };
}}




