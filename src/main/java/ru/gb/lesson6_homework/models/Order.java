package ru.gb.lesson6_homework.models;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name = "Order.getAll", query = "SELECT o FROM Order o ORDER BY o.id"),
        @NamedQuery(name = "Order.getById", query = "SELECT o FROM Order o WHERE o.id = :id"),
        @NamedQuery(name = "Order.deleteById", query = "DELETE FROM Order o WHERE o.id = :id"),
})

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "costOnOrderDate")
    private int costOnOrderDate;

    @ManyToOne()
    @JoinColumn(name = "consumer_id")
    private Consumer consumer;

    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;

    public Order(){}

    public Order(Product product, Consumer consumer, int costOnOrderDate){
        this.product = product;
        this.consumer = consumer;
        this.costOnOrderDate = costOnOrderDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long orderId) {
        this.id = orderId;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCostOnOrderDate() {
        return costOnOrderDate;
    }

    public void setCostOnOrderDate(int costOnOrderDate) {
        this.costOnOrderDate = costOnOrderDate;
    }
}
