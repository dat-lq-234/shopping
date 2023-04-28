package com.example.shopping.models;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"price", "productYear"})
@Entity
@Table(name="tblProduct")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true, length = 300)
    private String productName;
    private double price;
    private String url;
    private int productYear;

    @Transient
    private int age;
    public int getAge(){
        return Calendar.getInstance().get(Calendar.YEAR) - productYear;
    }
    public Product(String productName, double price, String url, int year) {
        this.productName = productName;
        this.price = price;
        this.url = url;
        this.productYear = year;
    }
}
