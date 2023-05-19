package com.example.almacenprueba.controllersHtml.ProductHtml;

import com.example.almacenprueba.exceptions.NotFoundException;
import com.example.almacenprueba.expose.dto.productDTO.ProductResponse;
import com.example.almacenprueba.expose.dto.productDTO.SaveProductRequest;
import com.example.almacenprueba.expose.dto.productDTO.UpdateProductRequest;
import com.example.almacenprueba.persistence.entities.Product;
import com.example.almacenprueba.services.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductHtmlController {

    private final ProductService productService;

    public ProductHtmlController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product-page")
    public String productPage() {
        return "Product";
    }

    @GetMapping("/product-list")
    public String productList(Model model, @RequestParam(required = false) Long id) {
        if (id != null) {
            try {
                ProductResponse product = productService.findById(id);
                List<ProductResponse> productList = new ArrayList<>();
                productList.add(product);
                model.addAttribute("productList", productList);
            } catch (NotFoundException e) {
                model.addAttribute("errorMessage", "Product not found");
            }
        } else {
            List<ProductResponse> productList = productService.getAll();
            model.addAttribute("productList", productList);
        }
        return "Product-list";
    }


    @GetMapping("/products-update")
    public String showProductUpdatePage() {
        return "Product-Update";
    }

    @PostMapping("/product-update") // Cambia la ruta a /products-update
    public String updateProduct(@RequestParam("id_product") Long id,
                                @RequestParam("name_product") String name_product,
                                @RequestParam("comments") String comments,
                                @RequestParam("long_description") String long_description,
                                @RequestParam("short_description") String short_description,
                                @RequestParam("unit_price") Float unit_price) {
        UpdateProductRequest product = new UpdateProductRequest();
        product.setName_product(name_product);
        product.setComments(comments);
        product.setLong_description(long_description);
        product.setShort_description(short_description);
        product.setUnit_price(unit_price);

        try {
            productService.update(id, product);
        } catch (NotFoundException e) {

        }

        return "redirect:/product-list";
    }

    @GetMapping("/product-delete")
    public String ProductDelete() {
        return "Product-Delete";
    }

    @GetMapping("/products-create")
    public String productCreatePage() {
        return "Product-Create";
    }

    @PostMapping("/product-create")
    public String createProduct(@RequestParam("name_product") String name_product,
                                @RequestParam("comments") String comments,
                                @RequestParam("long_description") String long_description,
                                @RequestParam("short_description") String short_description,
                                @RequestParam("unit_price") Float unit_price) {
        SaveProductRequest product = new SaveProductRequest();
        product.setName_product(name_product);
        product.setComments(comments);
        product.setLong_description(long_description);
        product.setShort_description(short_description);
        product.setUnit_price(unit_price);

        productService.create(product);

        return "Product-Create";
    }

}
