package de.neuefische.cgnjava234springservice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product create(ProductCreate productCreate) {
        Product product = new Product(
                null,
                productCreate.name(),
                productCreate.price(),
                System.currentTimeMillis() / 1000
        );

        return productRepository.save(product);
    }

    public List<ProductListResponse> getAll() {
        List<Product> products = productRepository.findAll();
        List<ProductListResponse> productListResponse = products.stream()
                .map(p -> new ProductListResponse(p.name(), p.price(), p.createdAt()))
                .toList();
        return productListResponse;
    }
}
