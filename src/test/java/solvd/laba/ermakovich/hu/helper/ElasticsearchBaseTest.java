package solvd.laba.ermakovich.hu.helper;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;

/**
 * @author Ermakovich Kseniya
 */
@SuppressWarnings("JTCOP.RuleAllTestsHaveProductionClass")
abstract public class ElasticsearchBaseTest extends BaseTest {

    @Container
    private static final ElasticsearchBaseContainer elasticsearchContainer = new ElasticsearchBaseContainer();

    @BeforeAll
    static void setUp() {
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
