package com.example.mobileapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobileapp.model.Product;
import com.example.mobileapp.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    ArrayList<Product> listProduct;
    Context context;


    public ProductAdapter(ArrayList<Product> listProduct, Context context) {
        this.listProduct = listProduct;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.row_sp, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Product product = listProduct.get(position);
//        byte[] decode = Base64.decode(product.getAnh(), Base64.DEFAULT);
//        Bitmap imgBitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
//        holder.img.setImageBitmap(imgBitmap);
        Glide.with(context).load(product.getAnh()).into(holder.img);
        Locale locale = new Locale("vi", "VN"); // Thiết lập địa phương Việt Nam
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        holder.txtName.setText(product.getTen());
        holder.txtGia_km.setText(currencyFormatter.format(product.getGia_km()));
        holder.txtGia_sp.setText(currencyFormatter.format(product.getGia_sp()));
        holder.txtQuatang.setText(product.getQuatang());
        SpannableString spannableString = new SpannableString(holder.txtGia_sp.getText().toString());
        spannableString.setSpan(new StrikethroughSpan(), 0, holder.txtGia_sp.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.txtGia_sp.setText(spannableString);


    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img, imgSale;
        TextView txtName, txtGia_km, txtGia_sp, txtQuatang, txtSale;
        CardView btn_add_Cart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_sp);
            imgSale = itemView.findViewById(R.id.img_sale);
            txtName = itemView.findViewById(R.id.txtName_sp);
            txtGia_km = itemView.findViewById(R.id.txtGia_km);
            txtGia_sp = itemView.findViewById(R.id.txtGia_sp);
            txtQuatang = itemView.findViewById(R.id.txtQuatang);
            txtSale = itemView.findViewById(R.id.textView_sale);
            btn_add_Cart = itemView.findViewById(R.id.btn_addCart_row);
        }
    }

}

