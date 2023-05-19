package com.example.almacenprueba.expose.dto.supplierDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class SuppliersResponse {
    private List<SupplierResponse> suppliers;
}
