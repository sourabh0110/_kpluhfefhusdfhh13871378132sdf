package companyname.com.kpl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by LENOVO on 11/22/2016.
 */
public class FourFragment_admin extends Fragment {

    private Button addnews,delnews,editnews;
    public FourFragment_admin() {
        // Required empty public constructor
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editnews=(Button)getView().findViewById(R.id.edit_news);
        addnews=(Button)getView().findViewById(R.id.add_news);
        addnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity().getApplication(),Add_news.class);
                startActivity(i);
            }
        });

        delnews= (Button) getView().findViewById(R.id.del_news);
        delnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent i=new Intent(getActivity().getApplication(),Delete_news.class);
                startActivity(i);
                */
                enterNextFragment();
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
        editnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity().getApplication(),Edit_news.class);
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
