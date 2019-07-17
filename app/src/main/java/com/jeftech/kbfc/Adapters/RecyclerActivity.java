package com.jeftech.kbfc.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jeftech.kbfc.Adapters.RvAdapter;
import com.jeftech.kbfc.Models.DataModel;
import com.jeftech.kbfc.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity
//        implements RvAdapter.ClickListener
 {

    private String URLstring = "https://jeftik.000webhostapp.com/kbfc/players.json";
    private static ProgressDialog mProgressDialog;
    ArrayList<DataModel> dataModelArrayList;
    private RvAdapter rvAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        recyclerView = findViewById(R.id.recycler);
        fetchingJSON();
    }


    private void fetchingJSON() {

        showSimpleProgressDialog(this, "Loading...","Fetching players",false);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("Log", "onResponse()");
                        Log.d("strrrrr", ">>" + response);

                        try {
                            Log.e("Log", "try block");
                            removeSimpleProgressDialog();

//                            JSONObject obj = new JSONObject(response);
//                            if(obj.optString("status").equals("true")){

                            dataModelArrayList = new ArrayList<>();
//                                JSONArray dataArray  = obj.getJSONArray("data");

                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                Log.e("Log", "for loop");

//                                    DataModel playerModel = new DataModel();
                                JSONObject dataobj = array.getJSONObject(i);

                                Log.e("Log", String.valueOf(dataobj));

//                                    playerModel.setCode(dataobj.getString("itemcode"));
//                                    playerModel.setName(dataobj.getString("itemname"));
//                                    playerModel.setBrand(dataobj.getString("brandname"));
//                                    playerModel.setCategory(dataobj.getString("itemcategory"));
//                                    playerModel.setPrice(dataobj.getString("itemprice"));
//                                    playerModel.setImgURL(dataobj.getString("itemimage"));

                                dataModelArrayList.add(new DataModel(
                                        dataobj.getString("name"),
                                        dataobj.getString("rooms"),
                                        dataobj.getString("phone"),
                                        dataobj.getString("place"),
                                        dataobj.getString("image")
                                ));

                                Log.e("Log", "data fetched");

//                                    dataModelArrayList.add(playerModel);

                                Log.e("Log", "datamodelarraylist setted");

                            }
//                            Toast.makeText(RecyclerActivity.this, (CharSequence) dataModelArrayList, Toast.LENGTH_SHORT).show();
                            setupRecycler();

//                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Log", "inside onErrorResponse");
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    private void setupRecycler(){

        Log.e("Log", "inside setupRecycler");

        rvAdapter = new RvAdapter(this,dataModelArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        Log.e("Log", "setup recyclerview");
    }

    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            Log.e("Log", "inside catch IllegalArgumentException");
            ie.printStackTrace();

        } catch (RuntimeException re) {
            Log.e("Log", "inside catch RuntimeException");
            re.printStackTrace();
        } catch (Exception e) {
            Log.e("Log", "Inside catch Exception");
            e.printStackTrace();
        }

    }

    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void onClick(int position)
//    {
//        DataModel p = dataModelArrayList.get(position);
//        String name = p.getName();
//        String rooms = p.getRooms();
//        String phone = p.getPhone();
//        String place = p.getPlace();
//        String imgURL = p.getImgURL();
//
//        Intent i=new Intent(getApplicationContext(), HostelViewActivity.class);
//        i.putExtra("name", name);
//        i.putExtra("rooms", rooms);
//        i.putExtra("phone", phone);
//        i.putExtra("place", place);
//        i.putExtra("url", imgURL);
//        startActivity(i);
//    }

}
