package com.lab2_amanpreetkaur_c0854967_android.pojo;

import java.io.Serializable;

public class ProductDetail implements Serializable {


    private String productName;
    private int productId;
    private int productPrice;
    private String productDescription;

    public ProductDetail(String productName, int productPrice, String productDescription) {
        this.productName = productName;
//        this.productId = productId;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
    }

    public ProductDetail(int productId, String productName, String productDescritpion, int price) {

        this.productName = productName;
        this.productId = productId;
        this.productPrice = price;
        this.productDescription = productDescritpion;

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}
