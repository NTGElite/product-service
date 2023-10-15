package com.demo.product.intergration.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "kafka")
@Getter
@Setter
@PropertySource(value = "classpath:/kafka-config.yaml")
public class KafkaConfigProperties {
    private String bootstrapServer;
    private String groupId;
    private String replyMessageMaximumSize;
    private String autoOffsetResetConfig;
    private String maxRequestSize;
    private String maxPartitionFetchBytes;
    private String requestTimeoutMs;
}
