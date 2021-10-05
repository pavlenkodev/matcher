package com.example.model;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Cat extends BaseEntity {

    private String name;
    private String imageUrl;
    private long selected;
}
