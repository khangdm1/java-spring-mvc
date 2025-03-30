package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.ProductRepository;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    private final ProductService productService;
    private final UploadService uploadService;

    public ProductController(ProductService productService,
            UploadService uploadService, ProductRepository productRepository) {
        this.productService = productService;
        this.uploadService = uploadService;
        this.productRepository = productRepository;
    }

    @GetMapping("/admin/product")
    public String getProductPage(Model model) {
        List<Product> products = this.productService.getAllProduct();
        model.addAttribute("products", products);
        return "admin/product/show";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String createProductPage(Model model,
            @ModelAttribute("newProduct") @Valid Product product,
            BindingResult newProductBindingResult,
            @RequestParam("hoidanitFile") MultipartFile file) {
        String image = this.uploadService.handleSaveUploadFile(file, "image");
        product.setImage(image);

        // validate
        if (newProductBindingResult.hasErrors())
            return "admin/product/create";

        // product.setRole(this.userService.getRoleByName(hoidanit.getRole().getName()));

        this.productService.handleSaveProduct(product);
        return "redirect:/admin/product";
    }

    // detail product
    @GetMapping("/admin/product/{id}")
    public String getProductDetailPage(Model model, @PathVariable long id) {
        Product pr = this.productService.getProductById(id);
        model.addAttribute("product", pr);
        model.addAttribute("id", id);
        return "admin/product/detail";
    }

    // update product
    @RequestMapping("/admin/product/update/{id}") // GET
    public String getUpdateProductPage(Model model, @PathVariable long id) {
        Product currentProduct = this.productService.getProductById(id);
        model.addAttribute("newProduct", currentProduct);
        return "admin/product/update";
    }

    @PostMapping("/admin/product/update")
    public String postUpdateProduct(Model model,
            @ModelAttribute("newProduct") Product pr,
            @RequestParam("hoidanitFile") MultipartFile file) {
        Product currentPr = this.productService.getProductById(pr.getId());
        if (currentPr != null) {
            currentPr.setName(pr.getName());
            currentPr.setDetailDesc(pr.getDetailDesc());
            currentPr.setShortDesc(pr.getShortDesc());
            String image = this.uploadService.handleSaveUploadFile(file, "image");
            pr.setImage(image);
            currentPr.setPrice(pr.getPrice());
            this.productService.handleSaveProduct(pr);
        }
        return "redirect:/admin/product";
    }

}