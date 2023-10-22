package com.demo.product.intergration.kafka.topic;

import com.demo.product.intergration.kafka.constants.KafkaConstants;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestTopicConfig {
  @Bean(name = "example_topic")
  public RequestTopic getExampleRequestTopic() {
    return () -> KafkaConstants.EXAMPLE_TOPIC;
  }

  @Bean(name = "example_topic2")
  public RequestTopic getExampleRequestTopic2() {
    return () -> KafkaConstants.EXAMPLE_TOPIC2;
  }

  @Bean
  public NewTopic getExampleTopic(@Qualifier("example_topic") RequestTopic topic) {
    Map<String, String> configurations = new HashMap<>();
    configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
    return new NewTopic(topic.getTopicName(), KafkaConstants.NUM_PARTITION, KafkaConstants.REPLICATION_FACTOR)
        .configs(configurations);
  }

  @Bean
  public NewTopic getExampleTopic2(@Qualifier("example_topic2") RequestTopic topic) {
    Map<String, String> configurations = new HashMap<>();
    configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
    return new NewTopic(topic.getTopicName(), KafkaConstants.NUM_PARTITION, KafkaConstants.REPLICATION_FACTOR)
        .configs(configurations);
  }
}
