package companyname.com.kpl.admin_files;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import companyname.com.kpl.Demo;
import companyname.com.kpl.R;
import companyname.com.kpl.recycler_listviews_adapters.DisplayList_news;

/**
 * Created by LENOVO on 11/22/2016.
 */
public class FourFragment_admin extends Fragment {

    private Button addnews,searchnews,showallnews;
    public FourFragment_admin() {
        // Required empty public constructor
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchnews=(Button)getView().findViewById(R.id.btn_search);
        addnews=(Button)getView().findViewById(R.id.btn_add);

        showallnews= (Button) getView().findViewById(R.id.btn_showall_news);
        showallnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity().getApplication(),DisplayList_news.class);
                startActivity(i);

            }
        });

        addnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity().getApplication(),Add_news.class);
                startActivity(i);
            }
        });
        searchnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity().getApplication(),Search_news.class);
                startActivity(i);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_four_admin, container, false);


    }



}
