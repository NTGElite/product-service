package com.demo.product.intergration.kafka.publishmessage;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExampleDTO implements Serializable {
  private String id;
  private String name;
}
