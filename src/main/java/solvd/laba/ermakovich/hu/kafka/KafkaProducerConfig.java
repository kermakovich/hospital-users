package solvd.laba.ermakovich.hu.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.serialization.UUIDSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String BOOTSTRAP_SERVERS;

    @Value("${spring.kafka.topic}")
    private String TOPIC;

    protected Map<String, Object> kafkaProducerProperties() {
        Map<String, Object> kafkaPropertiesMap = new HashMap<>(3);
        kafkaPropertiesMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        kafkaPropertiesMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaPropertiesMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, UUIDSerializer.class);
        return kafkaPropertiesMap;
    }

    @Bean
    KafkaSender<String, UUID> kafkaSender() {
        SenderOptions<String, UUID> options = SenderOptions.create(kafkaProducerProperties());
        return KafkaSender.create(options);
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(TOPIC)
                .partitions(2)
                .build();
    }

}
