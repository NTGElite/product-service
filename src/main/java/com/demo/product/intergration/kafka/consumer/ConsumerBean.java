package com.demo.product.intergration.kafka.consumer;

import com.demo.product.intergration.kafka.KafkaConfigProperties;
import com.demo.product.intergration.kafka.publishmessage.ExampleDTO;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

@Configuration
@Slf4j
public class ConsumerBean {
  @Bean
  ConcurrentKafkaListenerContainerFactory<String, ExampleDTO> exampleDTOListener(
      KafkaConfigProperties configProperties) {
    KafkaConsumerListenerFactory<ExampleDTO> factory = new KafkaConsumerListenerFactory<>();
    ConcurrentKafkaListenerContainerFactory<String, ExampleDTO> listenerContainerFactory =
        factory.createListenerContainer(ExampleDTO.class, configProperties);
    Map<String, Object> configUpdates = new HashMap<>();
    configUpdates.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, configProperties.getAutoOffsetResetConfig());
    configUpdates.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ExampleDTO.class);
    configUpdates.put(ConsumerConfig.GROUP_ID_CONFIG, "1");
    listenerContainerFactory.getConsumerFactory().updateConfigs(configUpdates);
    return listenerContainerFactory;
  }
}
