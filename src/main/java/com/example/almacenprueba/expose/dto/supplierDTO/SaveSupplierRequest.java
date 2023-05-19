package com.example.almacenprueba.expose.dto.supplierDTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SaveSupplierRequest {
    private String address_1;
    private String address_2;
    private String address_3;
    private String city;
    private String comments;
    private String contact_email;
    private String contact_name;
    private String contact_phone;
    private String country;
    @NotNull
    private String supplier_name;
    private String postcode;
}
