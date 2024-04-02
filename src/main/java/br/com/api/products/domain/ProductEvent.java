package br.com.api.products.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class ProductEvent implements Serializable {
    @Serial
    private static final long serialVersionUID = -356433779705182478L;

    private long productId;
    private String code;
    private String username;
}
