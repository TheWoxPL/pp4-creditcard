package pl.wbubula.ecommerce.catalog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductCatalog {


    private ArrayList<Product> products;

    public ProductCatalog() {
        this.products = new ArrayList<>();
    }

    public List<Product> allProducts() {
        return products;
    }

    public String addProduct(String name, String description, BigDecimal price, String imageSource) {
        if(description==null)
            throw new LackOfDescription();
        else if(imageSource==null)
            throw new LackOfImageSource();

        UUID id = UUID.randomUUID();
        Product newProduct = new Product(id, name, description, price);

        products.add(newProduct);

        return newProduct.getId();
    }


    public Product getProductById(String id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .get();
    }

    public void changePrice(String id, BigDecimal newPrice) {
        Product loaded = this.getProductById(id);
        loaded.changePrice(newPrice);
    }
}
