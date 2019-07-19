/*
package com.jeftech.kbfc.Adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jeftech.kbfc.Models.Customer;
import com.jeftech.kbfc.R;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

*/
/**
 * Created by ajith on 29/7/16.
 *//*


public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {

    private final String TAG = "CUSTOMER ADAPTER";
    private final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private Context context;
    private ArrayList<Customer> customers = new ArrayList<>();
    private Boolean isListing;


    public CustomerAdapter(Context context, ArrayList<Customer> customers, Boolean isListing) {
        this.context = context;
        this.customers = customers;
        this.isListing = isListing;
    }

    public void update(ArrayList<Customer> customers) {
        this.customers.clear();
        this.customers = customers;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

*/
/*        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_customer, parent, false);*//*

        return new MyViewHolder(itemView);

    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final Customer customer = customers.get(position);
        holder.txtCustomerName.setText(customer.getCustomerName() + "(" + customer.getCustomerCode() + ")");
        holder.txtAddress.setText(customer.getAddress());
        holder.txtPhone.setText(customer.getPhone());
        holder.txtCity.setText(customer.getCity());
        if (customer.getAddress().isEmpty() || customer.getAddress().equals(null) || customer.getAddress().equals("null")) {
            holder.txtAddress.setVisibility(View.GONE);
        }
        if (customer.getPhone().isEmpty() || customer.getPhone().equals(null) || customer.getPhone().equals("null")) {
            holder.txtPhone.setVisibility(View.GONE);
        }
        holder.txtCustomerCode.setVisibility(View.GONE);

        if (!isListing) {
            holder.imgAction.setVisibility(View.VISIBLE);

            holder.customer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   */
/* ShoppingCart.setCustomer(customer);
                    Fragment fragment = new OrderCreationFragment();
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                   FragmentManager fragmentManager = activity.getSupportFragmentManager();
                   FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.replace(R.id.content_main, fragment);
                    String backStateName = fragment.getClass().getName();
                    ft.addToBackStack(backStateName);
                    ft.commit();*//*

                }
            });
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtCustomerName, txtCustomerCode, txtAddress, txtPhone, txtCity;
        public ImageView imgAction;
        public RelativeLayout customer;

        public MyViewHolder(View view) {
            super(view);
         */
/*   txtCustomerName = (TextView) view.findViewById(R.id.txtCustomerName);
            txtCustomerCode = (TextView) view.findViewById(R.id.txtCustomerCode);
            txtAddress = (TextView) view.findViewById(R.id.txtAddress);
            txtPhone = (TextView) view.findViewById(R.id.txtPhone);
            txtCity = (TextView) view.findViewById(R.id.txtCity);
            imgAction = (ImageView) view.findViewById(R.id.imgAction);
            customer = (RelativeLayout) view.findViewById(R.id.customer);*//*

        }
    }


}
*/
