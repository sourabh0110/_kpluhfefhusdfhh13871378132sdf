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

/**
 * Created by LENOVO on 11/22/2016.
 */
public class FourFragment_admin extends Fragment {

    private Button addnews,delnews,editnews,searchnews,showallnews;
    public FourFragment_admin() {
        // Required empty public constructor
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editnews= (Button) getView().findViewById(R.id.btn_edit);
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
        editnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity().getApplication(),Edit_news.class);
                startActivity(i);
            }
        });

        delnews= (Button) getView().findViewById(R.id.btn_del);
        delnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent i=new Intent(getActivity().getApplication(),Delete_news.class);
                startActivity(i);
                */
                //enterNextFragment();
            }


            private void enterNextFragment() {

                Fragment newFragment = new Demo();
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack
                transaction.replace(R.id.main, newFragment);
                transaction.addToBackStack(null);

// Commit the transaction
                transaction.commit();

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
