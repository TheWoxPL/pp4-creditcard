package pl.wbubula.ecommerce.catalog.sales;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wbubula.ecommerce.catalog.ProductCatalog;
import pl.wbubula.ecommerce.sales.cart.Cart;
import pl.wbubula.ecommerce.sales.cart.CartLine;
import pl.wbubula.ecommerce.sales.offer.OfferCalculator;
import static org.assertj.core.api.Assertions.*;
import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class OfferingCalculatorTest {


    @Autowired
    private OfferCalculator offerCalculator;
    @Autowired
    ProductCatalog catalog;

    @Test
    void itCalculatesOffer(){
        Cart cart = Cart.empty();
        catalog.setUpDatabase();
        catalog.addProduct("Lego set 8083" , "Nice one", BigDecimal.valueOf(10));
        catalog.addProduct("Cobi Blocks" , "Nice one" , BigDecimal.valueOf(5));
        String productId1 = catalog.allProducts().get(0).getId();
        String productId2 = catalog.allProducts().get(1).getId();

        for (int i = 0; i < 10; i++) {
            cart.addProduct(productId1);
        }

        for (int i = 0; i < 5; i++) {
            cart.addProduct(productId2);
        }

        List<CartLine> lines = cart.getLines();
        var result = offerCalculator.calculate(lines);

        assertThat(result.getItemsCount())
                .isEqualTo(15);
        assertThat(result.getTotal())
                .isEqualTo(BigDecimal.valueOf(100.0));
    }

    private String thereIsProduct(String id) {
        return id;
    }
}
