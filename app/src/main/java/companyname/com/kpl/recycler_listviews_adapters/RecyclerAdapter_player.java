package companyname.com.kpl.recycler_listviews_adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import companyname.com.kpl.R;
import companyname.com.kpl.admin_files.Edit_Player;
import companyname.com.kpl.admin_files.Edit_team;
import companyname.com.kpl.admin_files.Select_Player_list;

/**
 * Created by admin on 12/19/2016.
 */

public class RecyclerAdapter_player extends RecyclerView.Adapter <RecyclerAdapter_player.RecyclerViewHolder>{
    private Context ctx;
    //private static final int TYPE_HEAD=0;
    //private static final int TYPE_LIST=1;


    ArrayList<Player> arrayList=new ArrayList<>();

    public RecyclerAdapter_player(ArrayList<Player> arrayList, Context ctx) {

        this.arrayList=arrayList;
        this.ctx=ctx;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_players,parent,false);
        RecyclerAdapter_player.RecyclerViewHolder recyclerViewHolder=new RecyclerAdapter_player.RecyclerViewHolder(view,viewType,ctx,arrayList);
        return recyclerViewHolder;    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        Player player=arrayList.get(position);
        holder.Id.setText(Integer.toString(player.getPlayer_id()));
        holder.player_name.setText(player.getPlayer_name());
        holder.mobno.setText(player.getMobno());
        holder.dob.setText(player.getPlayer_dob());
        holder.team_name.setText(player.getTm_name());
        holder.team_code.setText(Integer.toString(player.getTm_code()));
        Glide.with(holder.imageView.getContext()).load(player.getPlayer_image())
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        int viewType;
        TextView player_name,Id,mobno,dob,team_code,team_name;
        ImageView imageView,imageView_static;
        ArrayList<Player>player=new ArrayList<Player>();
        Context ctx;
        public RecyclerViewHolder(View view,int viewType,Context ctx,ArrayList<Player> player)
        {
            super(view);
            this.player=player;
            this.ctx=ctx;
            view.setOnClickListener(this);
            player_name=(TextView)view.findViewById(R.id.tv_player_name_fetch);
            mobno=(TextView)view.findViewById(R.id.tv_player_mobile_no);
            Id=(TextView)view.findViewById(R.id.tv_player_id);
            dob=(TextView)view.findViewById(R.id.tv_player_team_bdate);
            team_code=(TextView)view.findViewById(R.id.tv_player_team_code);
            team_name=(TextView)view.findViewById(R.id.tv_player_team_name_fetch);
            imageView= (ImageView) view.findViewById(R.id.iv_player_image);
            //    this.viewType=TYPE_LIST;


        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            Player player=this.player.get(position);

            Intent intent=new Intent(ctx,Player_Details.class);
            intent.putExtra("imagepath", player.getPlayer_image());
            intent.putExtra("teamname",player.getTm_name());
            intent.putExtra("name",player.getPlayer_name());
            intent.putExtra("dob",player.getPlayer_dob());
            intent.putExtra("mobno",player.getMobno());
            intent.putExtra("id",Integer.toString(player.getPlayer_id()));

            this.ctx.startActivity(intent);
            ((Activity)ctx).finish();


        }

    }
}
