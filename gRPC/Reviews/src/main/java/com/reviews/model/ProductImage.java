package com.reviews.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class ProductImage {
    @Id
    @GeneratedValue
    private String sku;

    @ManyToOne
    private Product product;

    // in this case we are storing the image in the database, but it would be
    // "better" to store it in a server file system
    @Lob
    private byte[] image;

    private String contentType;
}
