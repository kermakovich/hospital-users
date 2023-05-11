package solvd.laba.ermakovich.hu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableReactiveMongoRepositories("solvd.laba.ermakovich.hu.repository.mongo")
@EnableReactiveElasticsearchRepositories(
        "solvd.laba.ermakovich.hu.repository.elastic"
)
public class AppInitializer {

    public static void main(final String[] args) {
        SpringApplication.run(AppInitializer.class, args);
    }

}
