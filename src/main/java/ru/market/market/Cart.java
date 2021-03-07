package ru.market.market;


import ru.market.domain.Product;

import java.util.List;

public interface Cart {
    void addProduct(int id);
    void deleteProduct(int id);
    void clear();
    List<Product> getProducts();
}
