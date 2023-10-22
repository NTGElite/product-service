package com.demo.product.intergration.kafka.producer;

import com.demo.product.intergration.kafka.KafkaConfigProperties;
import com.demo.product.intergration.kafka.constants.KafkaConstants;
import com.demo.product.intergration.kafka.publishmessage.ExampleDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class ProducerBean {
  @Bean
  KafkaTemplate<String, ExampleDTO> exampleDTOKafkaTemplate(KafkaConfigProperties properties) {
    KafkaTemplateFactory<ExampleDTO> kafkaTemplateFactory = new KafkaTemplateFactory<>();
    return kafkaTemplateFactory.getKafkaTemplate(properties, KafkaConstants.EXAMPLE_TOPIC);
  }

  @Bean
  KafkaTemplate<String, String> stringKafkaTemplate(KafkaConfigProperties properties) {
    KafkaTemplateFactory<String> kafkaTemplateFactory = new KafkaTemplateFactory<>();
    return kafkaTemplateFactory.getKafkaTemplate(properties, KafkaConstants.EXAMPLE_TOPIC2);
  }
}
