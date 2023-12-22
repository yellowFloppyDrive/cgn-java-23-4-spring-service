package de.neuefische.cgnjava234springservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

class ProductServiceTest {
    @Test
    public void whenCreate_thenCreateWithCreatedAt() {
        // Given
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        String name = "Brot";
        double price = 2;
        Product product = new Product("1", name, price, 12345);
        Mockito.when(productRepository.save(Mockito.any())).thenReturn(product);

        ProductService productService = new ProductService(productRepository);

        // When
        Product productResult = productService.create(new ProductCreate(name, price));

        // Then
        Assertions.assertEquals(new Product("1", "Brot", 2, 12345), productResult);

        Mockito.verify(productRepository).save(Mockito.any());
    }

    @Test
    public void whenGetAll_thenGetAllAsProductResponse() {
        // Given
        ProductRepository productRepository = Mockito.mock(ProductRepository.class);
        Mockito.when(productRepository.findAll()).thenReturn(List.of(
                new Product("1", "Brot", 2, 12345),
                new Product("2", "Schokolade", 1.5, 54321)
        ));

        ProductService productService = new ProductService(productRepository);

        // When
        List<ProductListResponse> productResult = productService.getAll();

        // Then
        Assertions.assertEquals(
                List.of(
                        new ProductListResponse("Brot", 2, 12345),
                        new ProductListResponse("Schokolade", 1.5, 54321)
                ),
                productResult
        );

        Mockito.verify(productRepository).findAll();
    }
}