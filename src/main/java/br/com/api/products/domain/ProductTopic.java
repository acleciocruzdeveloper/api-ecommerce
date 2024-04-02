package br.com.api.products.domain;

import br.com.api.products.enumerates.EProductTopic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductTopic {

    private EProductTopic eventType;
    private String data;

}
