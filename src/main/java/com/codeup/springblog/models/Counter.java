package com.codeup.springblog.models;

import javax.persistence.*;


@Entity
@Table(name = "counter")
public class Counter {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private int quantity;

    public Counter(long id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public Counter(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public Counter() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increment(){
        this.quantity = quantity + 1;
    }

    public void decrement(){
        this.quantity = quantity - 1;
    }

}
