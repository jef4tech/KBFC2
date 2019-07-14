package com.jeftech.kbfc;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class frag1 extends Fragment {
Button players;
Unbinder unbinder;


    @BindView(R.id.btnList)
    Button btnEnter;
    public frag1() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview= inflater.inflate(R.layout.fragment_frag1, container, false);


       players =(Button)rootview.findViewById(R.id.btnList);
        // Inflate the layout for this fragment

        players.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplication(),RecyclerActivity.class);
                startActivity(intent);
                Toast.makeText(getContext(), "clicked button", Toast.LENGTH_SHORT).show();
            }
        });

        // bind view using butter knife
        unbinder = ButterKnife.bind(this, rootview);

return rootview;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // unbind the view to free some memory
        unbinder.unbind();
    }
}
