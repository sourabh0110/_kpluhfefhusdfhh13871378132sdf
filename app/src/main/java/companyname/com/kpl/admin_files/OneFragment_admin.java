package companyname.com.kpl.admin_files;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import companyname.com.kpl.R;

/**
 * Created by LENOVO on 11/22/2016.
 */
public class OneFragment_admin extends Fragment {
    public OneFragment_admin() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one_admin, container, false);


    }
}
