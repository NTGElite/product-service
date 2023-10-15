package com.demo.product.intergration.kafka.consumer;


import com.demo.product.intergration.kafka.KafkaConfigProperties;
import com.demo.product.intergration.kafka.deserializer.CustomJsonDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory to create kafka consumers that listen to message whose body is a specific class
 *
 * @param <R> Kafka request
 */
public class KafkaConsumerListenerFactory<R> {
    /**
     * Create a factory of Kafka consumers that can listen to messages whose data has a custom type
     *
     * @param requestClass Type of request data
     * @param kafProp      Kafka configuration
     * @return Factory for the Kafka consumer listener
     */
    public ConcurrentKafkaListenerContainerFactory<String, R> createListenerContainer(Class<R> requestClass,
                                                                                      KafkaConfigProperties kafProp) {
        ConcurrentKafkaListenerContainerFactory<String, R> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(this.consumerFactory(requestClass, kafProp));
        return factory;
    }

    private ConsumerFactory<String, R> consumerFactory(Class<R> requestClass,
                                                       KafkaConfigProperties kafkaConfiguration) {
        Map<String, Object> factoryProperties = new HashMap<>();
        JsonDeserializer<R> valueDeserializer = new CustomJsonDeserializer<>(requestClass, false);
        valueDeserializer.addTrustedPackages("*");
        factoryProperties.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        factoryProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfiguration.getBootstrapServer());
        factoryProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        factoryProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CustomJsonDeserializer.class);
        factoryProperties.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, kafkaConfiguration.getMaxPartitionFetchBytes());
        factoryProperties.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConfiguration.getGroupId());
        factoryProperties.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, kafkaConfiguration.getRequestTimeoutMs());
        return new DefaultKafkaConsumerFactory<>(factoryProperties, new StringDeserializer(), valueDeserializer);
    }
}
