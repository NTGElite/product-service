package com.demo.product.intergration.kafka.deserializer;


import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.support.JacksonUtils;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.lang.Nullable;

/**
 * This class to handle exception when receive and parse data from topic
 *
 * @param <T>
 */
@Slf4j
@Deprecated(forRemoval = true)
public class CustomJsonDeserializer<T> extends JsonDeserializer<T> {

  public CustomJsonDeserializer(@Nullable Class<? super T> targetType, boolean useHeadersIfPresent) {
    super(targetType, JacksonUtils.enhancedObjectMapper(), useHeadersIfPresent);
  }

  @Override
  public T deserialize(String topic, Headers headers, byte[] data) {
    if (data == null) {
      return null;
    } else {
      try {
        return objectMapper.readValue(data, new TypeReference<T>() {}); // magic
      } catch (Exception exception) {
        log.error("An exception {} thrown when polling the topic {}", exception.getClass().getSimpleName(), topic);
        return null;
      }
    }
  }

  @Override
  public T deserialize(String topic, @Nullable byte[] data) {
    if (data == null) {
      return null;
    } else {
      try {
        return objectMapper.readValue(data, new TypeReference<T>() {});
      } catch (Exception exception) {
        log.error("An exception {} thrown when polling the topic {}", exception.getClass().getSimpleName(), topic);
        return null;
      }
    }
  }
}
