package com.example.almacenprueba.expose.dto.supplierDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierResponse {
    private Long id_supplier;
    private String address_1;
    private String address_2;
    private String address_3;
    private String city;
    private String comments;
    private String contact_email;
    private String contact_name;
    private String contact_phone;
    private String country;
    private String supplier_name;
    private String postcode;
}

