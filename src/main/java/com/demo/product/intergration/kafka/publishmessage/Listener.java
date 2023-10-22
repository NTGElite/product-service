package com.demo.product.intergration.kafka.publishmessage;

import com.demo.product.intergration.kafka.constants.KafkaConstants;
import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {
  @KafkaListener(topics = KafkaConstants.EXAMPLE_TOPIC, containerFactory = "exampleDTOListener")
  void listenerExampleDTO2(ConsumerRecord<String, ExampleDTO> record) {
    System.out.println("ExampleDTO Message Received :" + record.key());
    System.out.println("ExampleDTO Message Received :" + record.value());
  }

  @KafkaListener(topics = KafkaConstants.EXAMPLE_TOPIC, groupId = "1")
  void listenerExampleDTO(String message) {
    Gson gson = new Gson();
    ExampleDTO tuanda = gson.fromJson(message, ExampleDTO.class);
    System.out.println("ExampleDTO Message Receive :" + tuanda);
  }


  @KafkaListener(topics = KafkaConstants.EXAMPLE_TOPIC2, groupId = "1")
  void listenerString(String message) {
    System.out.println("Message Received :" + message);
  }

}
