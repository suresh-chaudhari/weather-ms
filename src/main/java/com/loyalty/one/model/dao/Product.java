package com.loyalty.one.model.dao;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Suresh Chaudhari
 *
 * Note: use the same object for response to show product
 */
@Entity
@Table(name = "product")
@Data
public class Product implements Serializable {

    @Id
    @Column(columnDefinition = "INT(10) UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, name = "pname")
    @NotNull
    private String productName;

    @Column(columnDefinition = "INT(10) UNSIGNED")
    @NotNull
    private int quantity;
}
