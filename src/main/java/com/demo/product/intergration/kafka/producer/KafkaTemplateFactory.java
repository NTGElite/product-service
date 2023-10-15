package com.demo.product.intergration.kafka.producer;


import com.demo.product.intergration.kafka.KafkaConfigProperties;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

/**
 * Factory of {@link KafkaTemplate}
 *
 * @param <T> Class of sending values
 */
public class KafkaTemplateFactory<T> {

  private ProducerFactory<String, T> producerFactory(KafkaConfigProperties properties) {
    return new DefaultKafkaProducerFactory<>(getConfigurations(properties));
  }

  /**
   * Create a Map of configuration from system properties file.
   *
   * @param properties Kafka configuration
   * @return a {@link Map} of name and value of Kafka configuration properties
   */
  public static Map<String, Object> getConfigurations(KafkaConfigProperties properties) {
    Map<String, Object> configurations = new HashMap<>();
    configurations.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServer());
    configurations.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, properties.getMaxRequestSize());
    configurations.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, properties.getRequestTimeoutMs());
    configurations.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    configurations.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    return configurations;
  }

  /**
   * Create {@link KafkaTemplate} that sends message with {@link String} as key and a custom type as value to a give topic
   *
   * @param properties Kafka configuration
   * @param topic  Topic where the message is sent to
   * @return a {@link KafkaTemplate} that sends message to a given topic
   */
  public KafkaTemplate<String, T> getKafkaTemplate(KafkaConfigProperties properties, String topic) {
    KafkaTemplate<String, T> kafkaTemplate = new KafkaTemplate<>(producerFactory(properties));
    kafkaTemplate.setDefaultTopic(topic);
    return kafkaTemplate;
  }
}
