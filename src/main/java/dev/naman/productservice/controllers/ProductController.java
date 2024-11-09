package dev.naman.productservice.controllers;

import dev.naman.productservice.dtos.ExceptionDto;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.security.JwtObject;
import dev.naman.productservice.security.TokenValidator;
import dev.naman.productservice.services.ProductService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
//    @Autowired
    // field injection
    private ProductService productService;
    private TokenValidator tokenValidator;
    // ....;
    // ...;



    // constructor injection
//    @Autowired
    public ProductController(ProductService productService, TokenValidator tokenValidator) {
        this.productService = productService;
        this.tokenValidator = tokenValidator;
    }
//

    // setter injection
//    @Autowired
//    public void setProductService(ProductService productService) {
//        this.productService = productService;
//    }

    // GET /products {}
    @GetMapping
    public List<GenericProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    // localhost:8080/products/{id}
    // localhost:8080/products/123
    @GetMapping("{id}")
    public GenericProductDto getProductById(@Nullable @RequestHeader(HttpHeaders.AUTHORIZATION)String authToken, @PathVariable("id") Long id) throws NotFoundException {

        System.out.println(authToken+" <-authtoken");
        Optional<JwtObject> jwtObjectOptional;
        String userId= "351e99b7-3150-49db-ad27-74c793d0aea5";
        JwtObject jwtObject = null;
        if(authToken!=null){
            jwtObjectOptional = tokenValidator.validateToken(authToken,userId);
           if (jwtObjectOptional.isEmpty()){
               System.out.println("failinggggg");
           }
           jwtObject = jwtObjectOptional.get();

        }
        return productService.getProductById(id,jwtObject.getUserId());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                productService.deleteProduct(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product) {
//        System.out.println(product.name);
        return productService.createProduct(product);
    }

    @PutMapping("{id}")
    public void updateProductById() {

    }
}
