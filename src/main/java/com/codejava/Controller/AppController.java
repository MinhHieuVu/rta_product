package com.codejava.Controller;

import com.codejava.Product.Product;
import com.codejava.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private ProductService service;

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Product> listProducts = service.listAll();
        model.addAttribute("listProducts" , listProducts);

        return "index";
    }

    @RequestMapping("/new")
    public String newProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product" , product);

        return "new_product";
    }

    @RequestMapping(value = "/save" ,method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product product) {
        service.save(product);

        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editProduct(@PathVariable(name = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("edit_product");
        Product product = service.get(id);
        modelAndView.addObject("product", product);

        return modelAndView;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";
    }

}
