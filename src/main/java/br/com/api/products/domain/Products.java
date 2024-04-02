package br.com.api.products.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Products implements Serializable {
    @Serial
    private static final long serialVersionUID = -7478641728610023677L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 32, nullable = false)
    private String productName;

    @Column(length = 50, nullable = false)
    private String productModel;

    @Column(length = 30, unique = true)
    private String productCode;

    @NotNull
    @Column(name = "price")
    @DecimalMin(value = "0.00", inclusive = false)
    private BigDecimal productPrice;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;
}
