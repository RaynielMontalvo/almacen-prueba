package com.example.almacenprueba.controllersHtml.SupplierHtml;

import com.example.almacenprueba.exceptions.NotFoundException;
import com.example.almacenprueba.expose.dto.productDTO.ProductResponse;
import com.example.almacenprueba.expose.dto.productDTO.UpdateProductRequest;
import com.example.almacenprueba.expose.dto.supplierDTO.SaveSupplierRequest;
import com.example.almacenprueba.expose.dto.supplierDTO.SupplierResponse;
import com.example.almacenprueba.expose.dto.supplierDTO.UpdateSupplierRequest;
import com.example.almacenprueba.persistence.entities.Supplier;
import com.example.almacenprueba.services.impl.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SupplierHtmlController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierHtmlController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/supplier-page")
    public String supplierPage() {
        return "Suppliers";
    }

    @GetMapping("/supplier-list")
    public String supplierList(Model model, @RequestParam(required = false) Long id) {
        if (id != null) {
            try {
                SupplierResponse supplier = supplierService.findById(id);
                List<SupplierResponse> supplierList = new ArrayList<>();
                supplierList.add(supplier);
                model.addAttribute("supplierList", supplierList);
            } catch (NotFoundException e) {
                model.addAttribute("errorMessage", "Product not found");
            }
        } else {
            List<SupplierResponse> supplierList = supplierService.getAll();
            model.addAttribute("supplierList", supplierList);

        }
        return "Suppliers-List";
    }


    @GetMapping("/suppliers-create")
    public String supplierCreatePage() {
        return "Supplier-Create";
    }

    @PostMapping("/supplier-create")
    public String createSupplier(@RequestParam("supplierName") String supplier_name,
                                 @RequestParam("address1") String address_1,
                                 @RequestParam("address2") String address_2,
                                 @RequestParam("address3") String address_3,
                                 @RequestParam("city") String city,
                                 @RequestParam("comments") String comments,
                                 @RequestParam("contactEmail") String contact_email,
                                 @RequestParam("contactName") String contact_name,
                                 @RequestParam("contactPhone") String contact_phone,
                                 @RequestParam("country") String country,
                                 @RequestParam("postcode") String postcode) {
        SaveSupplierRequest supplier = new SaveSupplierRequest();
        supplier.setSupplier_name(supplier_name);
        supplier.setAddress_1(address_1);
        supplier.setAddress_2(address_2);
        supplier.setAddress_3(address_3);
        supplier.setCity(city);
        supplier.setComments(comments);
        supplier.setContact_email(contact_email);
        supplier.setContact_name(contact_name);
        supplier.setContact_phone(contact_phone);
        supplier.setCountry(country);
        supplier.setPostcode(postcode);

        supplierService.create(supplier);

        return "Supplier-Create";
    }

    @GetMapping("/suppliers-update")
    public String supplierUpdatePage() {
        return "Supplier-Update";
    }

    @PostMapping("/supplier-update")
    public String updateSupplier(@RequestParam("id_supplier") Long id,
                                 @RequestParam("supplierName") String supplier_name,
                                 @RequestParam("address1") String address_1,
                                 @RequestParam("address2") String address_2,
                                 @RequestParam("address3") String address_3,
                                 @RequestParam("city") String city,
                                 @RequestParam("comments") String comments,
                                 @RequestParam("contactEmail") String contact_email,
                                 @RequestParam("contactName") String contact_name,
                                 @RequestParam("contactPhone") String contact_phone,
                                 @RequestParam("country") String country,
                                 @RequestParam("postcode") String postcode) {
        UpdateSupplierRequest supplier = new UpdateSupplierRequest();
        supplier.setSupplier_name(supplier_name);
        supplier.setAddress_1(address_1);
        supplier.setAddress_2(address_2);
        supplier.setAddress_3(address_3);
        supplier.setCity(city);
        supplier.setComments(comments);
        supplier.setContact_email(contact_email);
        supplier.setContact_name(contact_name);
        supplier.setContact_phone(contact_phone);
        supplier.setCountry(country);
        supplier.setPostcode(postcode);

        try {
            supplierService.update(id,supplier);
        }catch(NotFoundException e) {

        }
        return "redirect:/supplier-list";
    }

    @GetMapping("/supplier-delete")
    public String supplierDeletePage() {
        return "Supplier-Delete";
    }
}
