package companyname.com.kpl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class OneFragment extends Fragment {
    //Button b;
    public OneFragment() {
        Log.e("ASD","asdasd");
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("ASD","asdasd");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e("ASD","asdasd");

        return inflater.inflate(R.layout.fragment_one, container, false);

    }

}
