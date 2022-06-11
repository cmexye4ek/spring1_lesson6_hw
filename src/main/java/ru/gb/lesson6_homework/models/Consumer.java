package ru.gb.lesson6_homework.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "consumers")
@NamedQueries({
        @NamedQuery(name = "Consumer.getAll", query = "SELECT c FROM Consumer c ORDER BY c.id"),
        @NamedQuery(name = "Consumer.getById", query = "SELECT c FROM Consumer c WHERE c.id = :id"),
        @NamedQuery(name = "Consumer.deleteById", query = "DELETE FROM Consumer c WHERE c.id = :id"),
})

public class Consumer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "consumer")
    private List<Order> orderList;

    public Consumer(){}

    public Consumer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
