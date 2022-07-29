package RESTaurantteehee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner initDatabase(RestaurantRepository repository){
        return args -> {
            log.info("Preloading " + repository.save(new Restaurant("ThaiXpress", "Thai", "$", 8L)));
            log.info("Preloading ", repository.save(new Restaurant("Daebok", "Korean", "$$$", 9L)));
        };
    }
}
