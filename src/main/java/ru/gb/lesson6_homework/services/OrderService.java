package ru.gb.lesson6_homework.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.lesson6_homework.models.Consumer;
import ru.gb.lesson6_homework.models.Order;
import ru.gb.lesson6_homework.models.Product;
import ru.gb.lesson6_homework.repositories.OrderDao;

import java.util.List;

@Service
public class OrderService {
    final private String listFormat = "%-25s %-25s %-25s" + System.lineSeparator();
    final private String headerFormat = "%-25s %-25s %-25s" + System.lineSeparator();
    private OrderDao orderDao;
    private ProductService productService;
    private ConsumerService consumerService;

    @Autowired
    public OrderService(OrderDao orderDao, ProductService productService, ConsumerService consumerService) {
        this.orderDao = orderDao;
        this.productService = productService;
        this.consumerService = consumerService;
    }

    public void printAllOrders() {
        System.out.println("Product list: ");
        System.out.printf(headerFormat, "Name", "Product", "Cost on order date");
        orderDao.getAllOrders().stream().forEach(s -> System.out.printf(listFormat, s.getConsumer().getName(), s.getProduct().toString(), s.getCostOnOrderDate()));
        System.out.println();
    }

    public void printProductsListByConsumerId(Long id) {
        System.out.println("Product list: ");
        System.out.printf(headerFormat, "Name", "Product", "Cost on order date");
        orderDao.getAllOrders().stream().filter(s -> s.getConsumer().getId().equals(id)).forEach(s -> System.out.printf(listFormat, s.getConsumer().getName(), s.getProduct().toString(), s.getCostOnOrderDate()));
        System.out.println();
    }

    public void printConsumersListByProductId(Long id) {
        System.out.println("Product list: ");
        System.out.printf(headerFormat, "Name", "Product", "Cost on order date");
        orderDao.getAllOrders().stream().filter(s -> s.getProduct().getId().equals(id)).forEach(s -> System.out.printf(listFormat,  s.getConsumer().getName(), s.getProduct().toString(), s.getCostOnOrderDate()));
        System.out.println();
    }

    public void addOrder(Long product_id, Long consumer_id) {
        Product product = productService.getProductById(product_id);
        Consumer consumer = consumerService.getConsumerById(consumer_id);
        Order order = new Order(product, consumer, product.getCost());
        orderDao.addOrUpdateOrder(order);
    }

}
