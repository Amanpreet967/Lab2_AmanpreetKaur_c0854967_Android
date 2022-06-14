package com.lab2_amanpreetkaur_c0854967_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.lab2_amanpreetkaur_c0854967_android.pojo.ProductDetail;


public class AddProductDetail extends AppCompatActivity {

    private com.lab2_amanpreetkaur_c0854967_android.db.ProductDetail product;
    private EditText name, price, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_product_detail );
        Button submit = (Button) findViewById( R.id.submit );
        AppCompatImageView back = (AppCompatImageView) findViewById( R.id.back_icon );
        name = (EditText) findViewById( R.id.pd_name );
        description = (EditText) findViewById( R.id.description );
        price = (EditText) findViewById( R.id.price );
        product = (com.lab2_amanpreetkaur_c0854967_android.db.ProductDetail) getIntent().getSerializableExtra( "productData" );
        if(product != null){
            setUI(product);
        }

        submit.setOnClickListener( view -> validation() );

        back.setOnClickListener( view -> finish() );
    }

    private void validation() {
        String productName, productDescritpion, productPrice;
        productName = name.getText().toString();
        productDescritpion = description.getText().toString();
        productPrice = price.getText().toString();

        if(productName.isEmpty()){
            name.setError( "Please enter Product Name" );
            return;
        }

        if(productDescritpion.isEmpty()){
            description.setError( "Please enter Product Description" );
            return;
        }
        if(productPrice.isEmpty()){
            price.setError( "Please enter Product Price" );
            return;
        }


        if(product!=null && product.getProductId()!=0){
            ProductDetail products = new ProductDetail( product.getProductId(),productName,productDescritpion,Integer.parseInt( productPrice ));
            Intent data = new Intent();
            data.putExtra( "product",products );
            setResult( Activity.RESULT_FIRST_USER, data);
        }else {
            ProductDetail products = new ProductDetail( productName,Integer.parseInt( productPrice ),productDescritpion);
            Intent data = new Intent();
            data.putExtra( "products",products );
            setResult( Activity.RESULT_OK, data);
        }
        finish();
    }

    private void setUI(com.lab2_amanpreetkaur_c0854967_android.db.ProductDetail product) {
        price.setText( ""+product.getProductPrice() );
        name.setText(product.getProductName());
        description.setText( product.getProductDescription() );
    }
}