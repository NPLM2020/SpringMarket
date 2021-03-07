package ru.market.market;

import ru.market.domain.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getProductList();
    Product getProductById(int id);
}
