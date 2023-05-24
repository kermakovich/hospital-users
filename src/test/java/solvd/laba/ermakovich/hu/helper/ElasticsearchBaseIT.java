package solvd.laba.ermakovich.hu.helper;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

/**
 * @author Ermakovich Kseniya
 */
@SuppressWarnings("JTCOP.RuleAllTestsHaveProductionClass")
abstract public class ElasticsearchBaseIT extends BaseTest {

    private final static ElasticsearchBaseContainer elasticsearchContainer = new ElasticsearchBaseContainer();

    @BeforeAll
    static void setUp() {
        elasticsearchContainer.init();
        elasticsearchContainer.start();
    }

    @DynamicPropertySource
    static void properties(final DynamicPropertyRegistry registry) {
        registry.add("spring.elasticsearch.uris",
                elasticsearchContainer::getHttpHostAddress);
    }

    @BeforeEach
    void isContainerRunning() {
        Assertions.assertTrue(elasticsearchContainer.isRunning());
    }

    @AfterAll
    static void destroy() {
        elasticsearchContainer.stop();
    }

}
