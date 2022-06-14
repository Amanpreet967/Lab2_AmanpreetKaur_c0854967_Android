package com.lab2_amanpreetkaur_c0854967_android.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db_product_detail";
    private static final int DATABASE_VERSION = 1;



    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL( ProductDetail.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ProductDetail.TABLE_NAME);
        onCreate(db);
    }
    public void addProductItem(List<com.lab2_amanpreetkaur_c0854967_android.pojo.ProductDetail> listItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        for (int i = 0; i < listItem.size(); i++) {
            values.put(ProductDetail.COLUMN_NAME, listItem.get(i).getProductName());
            values.put(ProductDetail.COLUMN_DESCRIPTION, listItem.get(i).getProductDescription());
            values.put(ProductDetail.COLUMN_PRICE, listItem.get(i).getProductPrice());
            db.insert(ProductDetail.TABLE_NAME, null, values);

        }
        db.close();
    }

    @SuppressLint("Range")
    public List<ProductDetail> getAllProducts() {
        List<ProductDetail> quizList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + ProductDetail.TABLE_NAME ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ProductDetail productDetail = new ProductDetail();
                productDetail.setProductId(cursor.getInt(cursor.getColumnIndex(ProductDetail.COLUMN_ID)));
                productDetail.setProductName( cursor.getString( cursor.getColumnIndex( ProductDetail.COLUMN_NAME ) ) );
                productDetail.setProductDescription( cursor.getString( cursor.getColumnIndex( ProductDetail.COLUMN_DESCRIPTION) ) );
                productDetail.setProductPrice( cursor.getInt( cursor.getColumnIndex( ProductDetail.COLUMN_PRICE ) ) );

                quizList.add(productDetail);
            } while (cursor.moveToNext());
        }
        db.close();

        return quizList;
    }

    public long insertData(String prodcuctName, String productDescription, int productPrice) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ProductDetail.COLUMN_NAME, prodcuctName);
        values.put(ProductDetail.COLUMN_DESCRIPTION, productDescription);
        values.put(ProductDetail.COLUMN_PRICE, productPrice);

        long id = db.insert(ProductDetail.TABLE_NAME, null, values);
        db.close();

        return id;
    }

    public int updateData(com.lab2_amanpreetkaur_c0854967_android.pojo.ProductDetail productDetail) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProductDetail.COLUMN_NAME, productDetail.getProductName());
        values.put( ProductDetail.COLUMN_DESCRIPTION, productDetail.getProductDescription() );
        values.put( ProductDetail.COLUMN_PRICE, productDetail.getProductPrice() );

        return db.update(ProductDetail.TABLE_NAME, values, ProductDetail.COLUMN_ID + " = ?",
                new String[]{String.valueOf(productDetail.getProductId())});
    }



    public void deleteProduct(ProductDetail product) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ProductDetail.TABLE_NAME, ProductDetail.COLUMN_ID + " = ?",
                new String[]{String.valueOf(product.getProductId())});
        db.close();
    }


}
