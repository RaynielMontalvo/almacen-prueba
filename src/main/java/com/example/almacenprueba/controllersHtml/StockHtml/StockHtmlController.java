package com.example.almacenprueba.controllersHtml.StockHtml;

import com.example.almacenprueba.exceptions.NotFoundException;
import com.example.almacenprueba.expose.dto.stockDTO.SaveStockRequest;
import com.example.almacenprueba.expose.dto.stockDTO.StockResponse;
import com.example.almacenprueba.expose.dto.stockDTO.UpdateStockRequest;
import com.example.almacenprueba.services.impl.StockService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
public class StockHtmlController {

    private final StockService stockService;

    public StockHtmlController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/stock-page")
    public String Stock(){
        return "Stock";
    }

    @GetMapping("/stock-search") //url
    public String stockList(Model model) {
        List<StockResponse> stockList = stockService.getAll();
        model.addAttribute("stockList", stockList);
        return "Stock-Search"; //plantilla html stock-list
    }
    @GetMapping("/stock-delete")
    public String StockDelete(){
        return "Stock-Delete";
    }

    @PostMapping("/stocks/{id}") // Mapea la ruta "/stocks/{id}" a esta función
    public String deleteStock(@PathVariable("id") Long stockId, Model model) {
        try {
            stockService.delete(stockId);
            model.addAttribute("message", "Stock deleted successfully.");
        } catch (Exception e) {
            model.addAttribute("message", "Unable to delete stock.");
        }
        return "Stock-Delete";
    }

    @GetMapping("/stock-create")
    public String stockCreateForm(Model model) {
        model.addAttribute("saveStockRequest", new SaveStockRequest());
        return "Stock-Create";
    }

    @PostMapping("/stocks-create")
    public String createStock(@RequestParam("productId") Long id_product,
                              RedirectAttributes redirectAttributes) throws NotFoundException {
        SaveStockRequest stock = new SaveStockRequest();
        stock.setId_product(id_product);
        redirectAttributes.addFlashAttribute("successMessage", "Stock created successfully.");
        stockService.create(stock);
        return "redirect:/stock-create";
    }


    @GetMapping("/stock-update")
    public String stockUpdateForm() {
        return "Stock-Update";
    }

    @PostMapping("/stocks/{id}/update")
    public String updateStock(@PathVariable("id") Long stockId,
                              @RequestParam("productId") Long productId,
                              RedirectAttributes redirectAttributes) throws NotFoundException {
        UpdateStockRequest stock = new UpdateStockRequest();
        stock.setId_product(productId);
        redirectAttributes.addFlashAttribute("successMessage", "Stock updated successfully.");
        stockService.update(stockId, stock);
        return "redirect:/stock-update";
    }
}

