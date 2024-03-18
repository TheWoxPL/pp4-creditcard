package pl.wbubula.ecommerce.catalog;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductCatalogTest {

    @Test
    void itListAvailableProducts(){
        ProductCatalog catalog = new ProductCatalog();

        List<Product> products = catalog.allProducts();

        assert products.isEmpty();
    }

    @Test
    void itAllowsToAddProduct(){
        ProductCatalog catalog = new ProductCatalog();

       catalog.addProduct("Lego set 8083", "Nice one", BigDecimal.valueOf(100), "image.png");
       List<Product> products = catalog.allProducts();

        assertThat(products)
                .hasSize(1);
    }

    @Test
    void itLoadsSingleProductById(){
        ProductCatalog catalog = new ProductCatalog();
        String id = catalog.addProduct("Lego set 8083", "Nice one", BigDecimal.valueOf(100), "image.png");

        Product loaded = catalog.getProductById(id);
        assertThat(id).isEqualTo(loaded.getId());
    }

    @Test
    void itAllowsChangePrice(){
        ProductCatalog catalog = new ProductCatalog();
        String id = catalog.addProduct("Lego set 8083", "Nice one", BigDecimal.valueOf(100), "image.png");

        catalog.changePrice(id, BigDecimal.valueOf(10.10));
        Product loaded = catalog.getProductById(id);

        assertThat(BigDecimal.valueOf(10.10)).isEqualTo(loaded.getPrice());
    }

    @Test
    void itDenysAddWhenNoDescription(){
        ProductCatalog catalog = new ProductCatalog();

        assertThrows(LackOfDescription.class, () -> {
            catalog.addProduct("Lego set 8083", null, BigDecimal.valueOf(100), "image.png");
        });
    }

    @Test
    void itDenysAddWhenNoImageSource(){
        ProductCatalog catalog = new ProductCatalog();

        assertThrows(LackOfImageSource.class, () -> {
            catalog.addProduct("Lego set 8083", "Nice one", BigDecimal.valueOf(100), null);
        });

    }

}
