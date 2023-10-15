package com.demo.product.intergration.kafka.producer;


import com.demo.product.intergration.kafka.KafkaConfigProperties;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory of {@link KafkaTemplate}
 *
 * @param <T> Class of sending values
 */
public class KafkaTemplateFactory<T> {

    private ProducerFactory<String, T> producerFactory(KafkaConfigProperties kafProp) {
        return new DefaultKafkaProducerFactory<>(getStringObjectMap(kafProp));
    }

    /**
     * Create a {@link Map} of {@link String} as the property name and {@link Object} as a property value of Kafka configuration properties from {@link KafkaConfigurationProperties}
     *
     * @param kafProp Kafka configuration
     * @return a {@link Map} of name and value of Kafka configuration properties
     */
    public static Map<String, Object> getStringObjectMap(KafkaConfigProperties kafProp) {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafProp.getBootstrapServer());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        properties.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, kafProp.getMaxRequestSize());
        properties.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, kafProp.getRequestTimeoutMs());
        return properties;
    }

    /**
     * Create {@link KafkaTemplate} that sends message with {@link String} as key and a custom type as value to a give topic
     *
     * @param kafProp   Kafka configuration
     * @param topicName Topic where the message is sent to
     * @return a {@link KafkaTemplate} that sends message to a given topic
     */
    public KafkaTemplate<String, T> getKafkaTemplate(KafkaConfigProperties kafProp, String topicName) {
        KafkaTemplate<String, T> kafkaTemplate = new KafkaTemplate<>(producerFactory(kafProp));
        kafkaTemplate.setDefaultTopic(topicName);
        return kafkaTemplate;
    }
}