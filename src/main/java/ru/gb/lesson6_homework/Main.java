package ru.gb.lesson6_homework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.gb.lesson6_homework.configs.AppConfig;
import ru.gb.lesson6_homework.services.OrderService;
import ru.gb.lesson6_homework.services.ProductService;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(OrderService.class).printAllOrders();
        context.getBean(OrderService.class).printProductsListByConsumerId(1L);
        context.getBean(OrderService.class).printConsumersListByProductId(1L);
        context.getBean(OrderService.class).addOrder(5L, 2L);
        context.getBean(OrderService.class).printProductsListByConsumerId(2L);
        context.getBean(ProductService.class).increaseCostByOne(context.getBean(ProductService.class).getProductById(5L));
        context.getBean(OrderService.class).printProductsListByConsumerId(2L);
    }

}
