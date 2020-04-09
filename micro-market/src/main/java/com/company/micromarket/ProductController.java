package com.company.micromarket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class ProductController {
    private final ProductService productService;
    @Value("${user-service.location}")
    private String userServiceLocation;

    private final RestTemplate restTemplate = new RestTemplate();

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public void postNewProduct(Product product) {
        productService.saveProduct(product);
    }

    @PostMapping("/buy/{id}")
    public Product buyProduct(@PathVariable String id, @RequestHeader String userId) {
        Product product = productService.getProductById(id);
        validateUserPermissionToBuyProduct(product.getType(), userId);
        return product;
    }

    private void validateUserPermissionToBuyProduct(String productType, String userId) {
        Map<String, String> params = new HashMap<>();

        params.put("userId", userId);
        params.put("type", productType);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(userServiceLocation);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }

        Boolean isAllowed = restTemplate.getForObject(builder.toUriString(), Boolean.class);

        if (isAllowed == null || !isAllowed) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
