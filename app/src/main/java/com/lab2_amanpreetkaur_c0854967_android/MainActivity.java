package com.lab2_amanpreetkaur_c0854967_android;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lab2_amanpreetkaur_c0854967_android.db.DbHelper;
import com.lab2_amanpreetkaur_c0854967_android.pojo.ProductDetail;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public SharedPreference mSharedPreference;
    private List<ProductDetail> mList;
    private DbHelper dbHelper;
    private RecyclerView productRecyclerView;
    private FloatingActionButton addButton;
    private PdAdapter adapter;
    private List<com.lab2_amanpreetkaur_c0854967_android.db.ProductDetail> dblist = new ArrayList<>();
    private AppCompatEditText searchUser;
    private static final int REQ_CODE = 100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        init();


    }
private void init(){
    mSharedPreference = new SharedPreference(this);
    addProducts();
    productRecyclerView = findViewById(R.id.rc_view);
    productRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    addButton = (FloatingActionButton)findViewById( R.id.add_btn );
    searchUser = (AppCompatEditText) findViewById( R.id.search_user );
    dbHelper = new DbHelper(this);
    if(!mSharedPreference.getIsUpdated()){
        dbHelper.addProductItem(mList);
        mSharedPreference.setIsUpdated(true);
    }
    dblist.addAll(dbHelper.getAllProducts());

    adapter = new PdAdapter(this, dblist );
    productRecyclerView.setAdapter(adapter);

    addButton.setOnClickListener( new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            addProductDetail(null);
        }
    } );

    searchUser.setText( "" );

    searchUser.addTextChangedListener( new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            filterProduct(editable.toString());
        }
    } );


}

    private void filterProduct(String s) {
        List<com.lab2_amanpreetkaur_c0854967_android.db.ProductDetail> tempList = new ArrayList();
        for(com.lab2_amanpreetkaur_c0854967_android.db.ProductDetail d: dblist){
            if(d.getProductName().contains(s)){
                tempList.add(d);
            }
        }
        adapter.setList( tempList );
    }

    private void addProducts() {
        mList = new ArrayList<>();
        mList.add( new ProductDetail( "Lakme Color",45, "Crush Nailart 6 ml  (G9)" ) );
        mList.add( new ProductDetail( "Lakmé",23, "Enrich Matte Lipstick  (Shade RM14, 4.7 g)" ) );
        mList.add( new ProductDetail( "MAYBELLINE NEW YORK ",25, "Colossal Kajal Promo  (Black, 0.7 g)" ) );
        mList.add( new ProductDetail( "Lakme",14, "Eyeconic Kajal Twin Pack  (Deep Black, 0.7 g)" ) );
        mList.add( new ProductDetail( "Lakme",55, " Eyeconic Kajal Pencil  (Deep Black, 0.35 g)" ) );
        mList.add( new ProductDetail( "NIVEA Cherry Fruity Shine Lip Balm Cherry",34, "(Pack of: 2, 9.6 g)" ) );
        mList.add( new ProductDetail( "MAYBELLINE NEW YORK Sara's Favorite Sensational",124, "Liquid Matte Pack of 3" ) );
        mList.add( new ProductDetail( "MAYBELLINE NEW YORK",64, "Colossal Eyeliner 3 ml  (Bold Black)" ) );
        mList.add( new ProductDetail( "Lakmé Perfect Radiance Compact ",19, " (Ivory Fair 01, 8 g)" ) );
        mList.add( new ProductDetail( "MAYBELLINE NEW YORK",35, "Shade 230 Compact Powder, 8g" ) );

    }

    public void addProductDetail(com.lab2_amanpreetkaur_c0854967_android.db.ProductDetail productDetail) {
        Intent intent = new Intent(this, AddProductDetail.class);
        intent.putExtra( "productData", productDetail );
        startActivityForResult(intent, REQ_CODE );

    }

    public void deleteProductDetail(com.lab2_amanpreetkaur_c0854967_android.db.ProductDetail productDetail) {

        dbHelper.deleteProduct( productDetail );
        updateDBList();
        Toast.makeText( this, "Productc Deleted", Toast.LENGTH_SHORT ).show();
    }

    private void updateDBList() {
        dblist.clear();
        dblist = dbHelper.getAllProducts();
        adapter.setList( dblist );
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if(requestCode == REQ_CODE)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                ProductDetail product = (ProductDetail) data.getExtras().getSerializable("products");
                dbHelper.insertData(product.getProductName(),product.getProductDescription(),product.getProductPrice());
               updateDBList();
            }else if (resultCode  == Activity.RESULT_FIRST_USER){
                ProductDetail products = (ProductDetail) data.getExtras().getSerializable("product");
                dbHelper.updateData(products);
                updateDBList();
                Toast.makeText( this, "Updated", Toast.LENGTH_SHORT ).show();
            }
        }
    }
}