package companyname.com.kpl.admin_files;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import companyname.com.kpl.R;

/**
 * Created by LENOVO on 11/22/2016.
 */
public class ThreeFragment_admin extends Fragment {
Button addplayer,editplayer,addfeaturedplayer,editfeaturedplayer;

    public ThreeFragment_admin() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addplayer= (Button) getView().findViewById(R.id.btn_add_player);
        addplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity().getApplication(),Add_Player.class);
                startActivity(i);
            }
        });
        editplayer=(Button)getView().findViewById(R.id.btn_edit_player_list);
        editplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity().getApplication(),Select_Player_list.class);
                startActivity(i);
            }
        });

        addfeaturedplayer= (Button) getView().findViewById(R.id.btn_feat_add_player);
        addfeaturedplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity().getApplication(),Add_Featured_player.class);
                startActivity(i);
            }
        });
        editfeaturedplayer=(Button)getView().findViewById(R.id.btn_feat_edit_player);
        editfeaturedplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity().getApplication(),Select_Feat_Player_list.class);
                startActivity(i);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_three_admin, container, false);


    }

}
