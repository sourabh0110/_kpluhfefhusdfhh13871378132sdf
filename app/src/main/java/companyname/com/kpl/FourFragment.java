package companyname.com.kpl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FourFragment extends Fragment implements View.OnClickListener {

   Button admin_login;


    @Override
    public void onClick(View v) {

/*
        Intent i=new Intent(getActivity().getApplication(),Admin_Activity.class);
        Fragment fragment = null;
        switch (v.getId()) {
            case R.id.btn_admin:
                changeFragment();
                break;

            case R.id.phbookButton:
                fragment = new PhoneBookFragment();
                replaceFragment(fragment);
                break;
                }
                }
*/

    }




    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_four, container, false);


    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        admin_login=(Button)getView().findViewById(R.id.btn_admin);
        admin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity().getApplication(),LoginActivity.class);
                startActivity(i);
            }
        });
        Log.e("ASD","asdasd");


    }

}
