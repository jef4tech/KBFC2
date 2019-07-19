/*
package com.jeftech.kbfc.Fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.jeftech.kbfc.Db.CustomersDB;
import com.jeftech.kbfc.Models.Customer;
import com.jeftech.kbfc.R;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CustomerFragment extends Fragment {

    private static final String TAG = "CustomerFragment";
    private static final String IS_LISTNG = "is_listing";
    public static boolean isListing = true;
    private final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Context context;
    private CustomersDB customersDB;
    private CustomerFragment.OnFragmentInteractionListener mListener;
    private EditText txtCustomerHint;
    private CustomerAdapter customerAdapter;
    private RecyclerView lstCustomer;
    private ArrayList<Customer> customers;
    private TextView txtCustomerEmpty;

    public CustomerFragment() {
        // Required empty public constructor
    }

    public static CustomerFragment newInstance(boolean isListing) {
        CustomerFragment fragment = new CustomerFragment();
        Bundle args = new Bundle();
        args.putBoolean(IS_LISTNG, isListing);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isListing = getArguments().getBoolean(IS_LISTNG);
        }
        context = getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_customer, container, false);

        lstCustomer = (RecyclerView) view.findViewById(R.id.lstCustomer);
        txtCustomerEmpty = (TextView) view.findViewById(R.id.txtCustomerEmpty);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        lstCustomer.setLayoutManager(mLayoutManager);
        lstCustomer.setItemAnimator(new DefaultItemAnimator());
        lstCustomer.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        lstCustomer.setHasFixedSize(false);
        lstCustomer.setItemViewCacheSize(20);
        lstCustomer.setDrawingCacheEnabled(true);
        lstCustomer.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        lstCustomer.invalidate();

        txtCustomerHint = (EditText) view.findViewById(R.id.txtCustomerHint);
        txtCustomerHint.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterItemList();
            }
        });

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        final View mProgressView = getActivity().findViewById(R.id.login_progress);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        customersDB = new CustomersDB(context);
        if (isListing) {
            getActivity().setTitle(getActivity().getResources().getString(R.string.customers));
            customers = customersDB.getCustomers("", 50);
        } else {

            getActivity().setTitle(getActivity().getResources().getString(R.string.select_customer));
            customers = customersDB.getCustomersForOrder("", 50);
        }


        customerAdapter = new CustomerAdapter(context, customers, isListing);
        lstCustomer.setAdapter(customerAdapter);
        if (customers.size() == 0) {
            txtCustomerEmpty.setVisibility(View.VISIBLE);
        } else {
            txtCustomerEmpty.setVisibility(View.GONE);
        }
    }

    public void filterItemList() {
        String hint = txtCustomerHint.getText().toString();
        ArrayList<Customer> customers = customersDB.getCustomers(hint, 50);
        customerAdapter = new CustomerAdapter(context, customers, isListing);
        lstCustomer.setAdapter(customerAdapter);
        if (customers.size() == 0) {
            txtCustomerEmpty.setVisibility(View.VISIBLE);
        } else {
            txtCustomerEmpty.setVisibility(View.GONE);
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
*/
