package com.lab2_amanpreetkaur_c0854967_android.db;

import java.io.Serializable;

public class ProductDetail implements Serializable {
    private String productName;
    private int productId;
    private int productPrice;
    private String productDescription;


    public ProductDetail() {
    }

    public ProductDetail( String productName, String productDescription, int productPrice) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }
    public ProductDetail(String productName, int productId, int productPrice, String productDescription) {
        this.productName = productName;
        this.productId = productId;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
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



    public static final String TABLE_NAME = "product";

    public static final String COLUMN_ID = "productId";
    public static final String COLUMN_NAME = "productName";
    public static final String COLUMN_DESCRIPTION = "productDescription";
    public static final String COLUMN_PRICE = "productPrice";



    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_DESCRIPTION + " TEXT,"
                    + COLUMN_PRICE  + " INTEGER)";
}
