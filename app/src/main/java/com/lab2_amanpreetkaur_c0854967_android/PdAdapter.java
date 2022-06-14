package com.lab2_amanpreetkaur_c0854967_android;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.lab2_amanpreetkaur_c0854967_android.db.ProductDetail;

import java.util.List;


public class PdAdapter extends RecyclerView.Adapter<PdAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private Context context;
    private List<ProductDetail> mData;


    public PdAdapter(Context context, List<ProductDetail> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context =  context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate( R.layout.item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProductDetail productDetail = mData.get(position);
        holder.productDescription.setText( productDetail.getProductDescription() );
        holder.productPrice.setText( "$"+productDetail.getProductPrice()+"/-" );
        holder.productId.setText( "Id: "+productDetail.getProductId() );
        holder.productName.setText( productDetail.getProductName() );
        holder.delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new AlertDialog.Builder(context)
                        .setMessage("Are you sure want to Delete this product?")
                        .setCancelable(false)
                        .setPositiveButton( Html.fromHtml("<font color='#0096ff'>Yes</font>"), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ((MainActivity)context).deleteProductDetail(productDetail);
                            }
                        })
                        .setNegativeButton(Html.fromHtml("<font color='#0096ff'>NO</font>"), null)
                        .show();
            }
        } );
        holder.edit.setOnClickListener( view -> ((MainActivity)context).addProductDetail(productDetail) );
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setList(List<ProductDetail> mList) {
        this.mData = mList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView productId, productName, productDescription,productPrice;
        AppCompatImageView edit, delete;

        ViewHolder(View itemView) {
            super(itemView);
            productId = itemView.findViewById(R.id.id);
            productName= itemView.findViewById( R.id.name );
            productDescription= itemView.findViewById( R.id.description );
            productPrice= itemView.findViewById( R.id.price );
            edit = itemView.findViewById( R.id.edit );
            delete = itemView.findViewById( R.id.delete );
        }
    }
}