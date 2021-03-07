package ru.market.cli;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.market.config.AppConfig;
import ru.market.market.Cart;
import ru.market.market.ProductRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class Runner implements CommandLineRunner {

    private final ProductRepository productRepository;

    public Runner(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Cart cart = (Cart) context.getBean("cart");

        String inputString;
        while ((inputString = reader.readLine()) != null) {
            int whitespaceIndex = inputString.indexOf(" ");
            if (whitespaceIndex != -1) {
                String command = inputString.substring(0, whitespaceIndex);
                int id = Integer.parseInt(inputString.substring(whitespaceIndex + 1));
                if (command.equals("add")) {
                    cart.addProduct(id);
                } else if (command.equals("delete")) {
                    cart.deleteProduct(id);
                } else {
                    throw new IllegalArgumentException("Unknown command.");
                }
            } else {
                if (inputString.equals("showProducts")) {
                    System.out.println(productRepository.getProductList());
                } else if (inputString.equals("showCart")) {
                    System.out.println(cart.getProducts());
                } else if (inputString.equals("clearCart")) {
                    cart.clear();
                } else if (inputString.equals("newCart")) {
                    cart = (Cart) context.getBean("cart");
                } else {
                    throw new IllegalArgumentException("Unknown command.");
                }
            }
        }
    }

}
