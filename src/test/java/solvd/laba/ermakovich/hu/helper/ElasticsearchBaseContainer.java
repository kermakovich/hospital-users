package solvd.laba.ermakovich.hu.helper;

import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.utility.DockerImageName;

/**
 * @author Ermakovich Kseniya
 */
@SuppressWarnings("JTCOP.RuleAllTestsHaveProductionClass")
public class ElasticsearchBaseContainer extends ElasticsearchContainer {

    private static final String IMAGE_NAME = "elasticsearch:8.5.3";

    public ElasticsearchBaseContainer() {
        super(DockerImageName.parse(IMAGE_NAME)
                .asCompatibleSubstituteFor("docker.elastic.co/elasticsearch/elasticsearch"));
        this.addFixedExposedPort(9200, 9200);
        this.addEnv("discovery.type", "single-node");
        this.addEnv("xpack.security.enabled", "false");
    }

}
