package com.demo.product.intergration.kafka;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "kafka")
@PropertySource(value = "classpath:/kafka-config.yaml")
@Value
public class KafkaConfigProperties {
  String bootstrapServer;
  String groupId;
  String replyMessageMaximumSize;
  String autoOffsetResetConfig;
  String maxRequestSize;
  String maxPartitionFetchBytes;
  String requestTimeoutMs;
}
