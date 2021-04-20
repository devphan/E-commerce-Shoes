package com.ecommerce.shoes.controller.admin;

import com.ecommerce.shoes.entity.Product;
import com.ecommerce.shoes.model.dto.ProductDto;
import com.ecommerce.shoes.model.request.ProductReq;
import com.ecommerce.shoes.service.BrandService;
import com.ecommerce.shoes.service.CategoryService;
import com.ecommerce.shoes.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    //Get all ProductInfor
    @GetMapping("")
    public String getListProduct(Model model) {
        List<ProductDto> result = productService.findAllProductInfo();
        model.addAttribute("products", result);
        return "/admin/product/list";
    }


    //Get from create
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new ProductReq());

        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("categories", categoryService.findAll());

        return "/admin/product/create";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("product") ProductReq req, Model model) {
        productService.saveProduct(req);
        model.addAttribute("product", new ProductReq());
        return "redirect:/admin/products";
    }

    @GetMapping("/{id}")
    public String getDetailsOrUpdate(@PathVariable int id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("brands", brandService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "/admin/product/update";
    }


}
