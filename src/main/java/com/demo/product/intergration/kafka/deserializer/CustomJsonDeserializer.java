package com.demo.product.intergration.kafka.deserializer;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.support.JacksonUtils;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.lang.Nullable;

import java.nio.charset.StandardCharsets;

/**
 * This class to handle exception when receive and parse data from topic
 *
 * @param <T>
 */
@Slf4j
public class CustomJsonDeserializer<T> extends JsonDeserializer<T> {

    public CustomJsonDeserializer(@Nullable Class<? super T> targetType, boolean useHeadersIfPresent) {
        super(targetType, JacksonUtils.enhancedObjectMapper(), useHeadersIfPresent);
    }

    @Override
    public T deserialize(String topic, Headers headers, byte[] data) {
        try {
            if (data == null) return (T) new Object();
            byte[] jsoned = objectMapper.readValue(objectMapper.writeValueAsString(data), byte[].class);
            String s = new String(jsoned, StandardCharsets.UTF_8);
            if (s == null || "".equals(s.trim())) return (T) new Object();
            return super.deserialize(topic, headers, data);
        } catch (Exception exception) {
            log.error("An exception {} thrown when polling the topic {}", exception.getClass().getSimpleName(), topic);
            return (T) new Object();
        }
    }

    @Override
    public T deserialize(String topic, @Nullable byte[] data) {
        try {
            if (data == null) return (T) new Object();
            byte[] jsonedByte = objectMapper.readValue(objectMapper.writeValueAsString(data), byte[].class);
            String body = new String(jsonedByte, StandardCharsets.UTF_8);
            if (body == null || "".equals(body.trim())) return (T) new Object();
            return super.deserialize(topic, data);
        } catch (Exception exception) {
            log.error("An exception {} thrown when polling the topic {}", exception.getClass().getSimpleName(), topic);
            return (T) new Object();
        }
    }
}