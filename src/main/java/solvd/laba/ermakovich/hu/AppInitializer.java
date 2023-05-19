package solvd.laba.ermakovich.hu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AppInitializer {

    public static void main(final String[] args) {
        SpringApplication.run(AppInitializer.class, args);
    }

}
