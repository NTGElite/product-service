package com.demo.product.intergration.kafka.consumer;


import com.demo.product.intergration.kafka.KafkaConfigProperties;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

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
   * @param properties   Kafka configuration
   * @return Factory for the Kafka consumer listener
   */
  public ConcurrentKafkaListenerContainerFactory<String, R> createListenerContainer(Class<R> requestClass,
                                                                                    KafkaConfigProperties properties) {
    ConcurrentKafkaListenerContainerFactory<String, R> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(this.consumerFactory(requestClass, properties));
    return factory;
  }

  private ConsumerFactory<String, R> consumerFactory(Class<R> requestClass,
                                                     KafkaConfigProperties properties) {
    Map<String, Object> configurations = new HashMap<>();
    JsonDeserializer<R> valueDeserializer = new JsonDeserializer<>(requestClass, false);
    valueDeserializer.addTrustedPackages("*");
    configurations.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
    configurations.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServer());
    configurations.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, properties.getMaxPartitionFetchBytes());
    configurations.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, properties.getRequestTimeoutMs());
    configurations.put(ConsumerConfig.GROUP_ID_CONFIG, properties.getGroupId());
    configurations.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    configurations.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    return new DefaultKafkaConsumerFactory<>(configurations, new StringDeserializer(), valueDeserializer);
  }
}
