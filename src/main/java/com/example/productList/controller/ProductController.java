package com.example.productList.controller;
import com.example.productList.helper.Helper;
import com.example.productList.model.Product;
import com.example.productList.model.User;
import com.example.productList.service.ProductService;
import com.example.productList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {


    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @GetMapping("/adminScreen")
    public String adminScreen(Model model){
        boolean isAdmin = true;
        return findPaginatedAdmin(1,model);
    }


    @GetMapping("/addBalance")
    public String addBalance(Principal principal, Model model){
        String username = principal.getName();
        User user = userService.loadUserByEmail(username);
        model.addAttribute("user", user);
        return "addBalance";
    }

    @PostMapping("/addBalance")
    public String addBalance(Principal principal, @ModelAttribute User user){
        String username = principal.getName();
        User currentUser = userService.loadUserByEmail(username);
        int currentWalletBalance = currentUser.getWalletBalance();
        currentWalletBalance += user.getWalletBalance();
        currentUser.setWalletBalance(currentWalletBalance);
        userService.save(currentUser);
        return "redirect:/addBalance?success";
    }




    @GetMapping("/productslist")
    public String productslist(Model model){

        return findPaginated(1,model);
    }

    @GetMapping("/showNewProductForm")
    public String showNewProductForm(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "new_product";

    }

    @PostMapping("/product/upload")
    public String upload(@ModelAttribute("file") MultipartFile file) {
        if (Helper.checkExcelFormat(file)) {
            //true

            this.productService.save(file);

            return "redirect:/adminScreen";


        }
        return "redirect:/new_product?error";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("product") Product product) {

        productService.saveProduct(product);
        return "redirect:/adminScreen";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "update_product";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable (value = "id") long id) {
        this.productService.deleteProductById(id);
        return "redirect:/adminScreen";
    }
    @GetMapping("/buyProduct/{id}")
    public String buyProduct(@PathVariable (value = "id") long id, Principal principal) {
        Product product = productService.getProductById(id);
        int stocks = product.getStocks();
        double price = product.getPrice();
        if (stocks < 1){

            return "redirect:/productslist?insufficientStocks";
        }
        stocks -= 1;
        product.setStocks(stocks);
        productService.saveProduct(product);
        String username = principal.getName();
        User currentUser = userService.loadUserByEmail(username);
        int currentWalletBalance = currentUser.getWalletBalance();
        if(currentWalletBalance < price){
            return "redirect:/productslist?insufficientBalance";
        }
        currentWalletBalance -=price;
        currentUser.setWalletBalance(currentWalletBalance);
        userService.save(currentUser);
        return "redirect:/productslist";

    }
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo, Model model){
        int pageSize = 10;

        Page<Product> page = productService.findPaginated(pageNo, pageSize);
        List<Product> listProducts = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listProducts", listProducts);
        return "productList";
    }
    @GetMapping("/pageAdmin/{pageNo}")
    public String findPaginatedAdmin(@PathVariable (value = "pageNo") int pageNo, Model model){
        int pageSize = 10;

        Page<Product> page = productService.findPaginated(pageNo, pageSize);
        List<Product> listProducts = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listProducts", listProducts);
        return "adminScreen";
    }

}
