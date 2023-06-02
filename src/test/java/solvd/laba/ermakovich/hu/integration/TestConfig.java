package solvd.laba.ermakovich.hu.integration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.graphql.GraphQlAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;

/**
 * @author Ermakovich Kseniya
 */
@SuppressWarnings("JTCOP.RuleAllTestsHaveProductionClass")
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, GraphQlAutoConfiguration.class})
@ComponentScan("solvd.laba.ermakovich.hu.repository.elastic")
@EnableReactiveElasticsearchRepositories("solvd.laba.ermakovich.hu.repository.elastic")
public class TestConfig { }
