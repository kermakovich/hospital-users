package solvd.laba.ermakovich.hu.kafka.consumer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import solvd.laba.ermakovich.hu.event.IntegrationEvent;

/**
 * @author Ermakovich Kseniya
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig {

    private static final String TOPIC_KEY = "topic";
    private final ResourceLoader resourceLoader;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;


    protected Map<String, Object> kafkaConsumerProperties() {
        Map<String, Object> kafkaPropertiesMap = new HashMap<>(7);
        kafkaPropertiesMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        kafkaPropertiesMap.put(ConsumerConfig.GROUP_ID_CONFIG,
                "groupId");
        kafkaPropertiesMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        kafkaPropertiesMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class);
        kafkaPropertiesMap.put(JsonDeserializer.USE_TYPE_INFO_HEADERS,
                false);
        kafkaPropertiesMap.put(JsonDeserializer.VALUE_DEFAULT_TYPE,
                IntegrationEvent.class);
        kafkaPropertiesMap.put(TOPIC_KEY, "saga");
        return kafkaPropertiesMap;
    }

    @Bean
    public ReceiverOptions<String, IntegrationEvent> kafkaReceiverOptions() {
        var properties = kafkaConsumerProperties();
        ReceiverOptions<String, IntegrationEvent> options = ReceiverOptions.create(properties);
        return options.subscription(
                        (Collections.singletonList(
                                (String) properties.get(TOPIC_KEY)
                        )))
                .addAssignListener(receiverPartitions -> log.debug("assign consumer {}", receiverPartitions))
                .addRevokeListener(receiverPartitions -> log.debug("revoke consumer {}", receiverPartitions));

    }

    @Bean
    public KafkaReceiver<String, IntegrationEvent> kafkaReceiver() {
        return KafkaReceiver.create(kafkaReceiverOptions());
    }
}
