package com.demo.product.intergration.kafka;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "kafka")
public class KafkaConfigProperties {
  private String bootstrapServer;
  private String groupId;
  private String replyMessageMaximumSize;
  private String autoOffsetResetConfig;
  private String maxRequestSize;
  private String maxPartitionFetchBytes;
  private String requestTimeoutMs;
}
