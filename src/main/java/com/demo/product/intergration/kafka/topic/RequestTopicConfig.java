package com.demo.product.intergration.kafka.topic;

import com.demo.product.intergration.kafka.constants.Constants;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RequestTopicConfig {
    @Bean(name = "example_topic")
    public RequestTopic getExampleRequestTopic() {
        return () -> Constants.EXAMPLE_TOPIC;
    }

    @Bean
    public NewTopic getExampleTopic(@Qualifier("example_topic") RequestTopic requestTopic) {
        Map<String, String> topicConfig = new HashMap<>();
        topicConfig.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
        return new NewTopic(requestTopic.getTopicName(), Constants.NUM_PARTITION, Constants.REPLICATION_FACTOR).configs(topicConfig);
    }
}
