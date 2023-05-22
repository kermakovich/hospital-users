package solvd.laba.ermakovich.hu.helper;

import org.testcontainers.elasticsearch.ElasticsearchContainer;

/**
 * @author Ermakovich Kseniya
 */
@SuppressWarnings("JTCOP.RuleAllTestsHaveProductionClass")
public class ElasticsearchBaseContainer extends ElasticsearchContainer {

    public ElasticsearchBaseContainer() {
        super("docker.elastic.co/elasticsearch/elasticsearch:8.5.3");
    }

    public void init() {
        this.addFixedExposedPort(9200, 9200);
        this.addEnv("discovery.type", "single-node");
        this.addEnv("xpack.security.enabled", "false");
    }

}
