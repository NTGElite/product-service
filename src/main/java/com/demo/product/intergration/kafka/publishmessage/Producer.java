package com.demo.product.intergration.kafka.publishmessage;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Producer {
  private final KafkaTemplate<String, ExampleDTO> exampleDTOKafkaTemplate;
  private final KafkaTemplate<String, String> stringKafkaTemplate;

  @EventListener(ApplicationReadyEvent.class)
  public void sendExampleDtoToKafka() {
    //produce ExampleDTO
    exampleDTOKafkaTemplate.sendDefault("1", ExampleDTO.builder().id("1").name("Tuấn Đá").build());
    //produce String
    stringKafkaTemplate.sendDefault("Heleo");
  }
}
