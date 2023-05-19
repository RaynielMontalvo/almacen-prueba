package com.example.almacenprueba.expose.dto.productDTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class SaveProductRequest implements Serializable {
    @NotNull
    private String name_product;
    private String comments;
    private String long_description;
    private String short_description;
    private Float unit_price;

}
