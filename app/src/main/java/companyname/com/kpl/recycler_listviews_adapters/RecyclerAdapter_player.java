package companyname.com.kpl.recycler_listviews_adapters;

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
import companyname.com.kpl.admin_files.Edit_team;

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


        //holder.Content.setText(news.getContent());
            /*
            * Glide.with(holder.imageView.getContext()).load("http://devkpl.com/news/uploads/" + news.getNews_image())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(holder.imageView);
            *
            * */

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
            imageView_static=(ImageView)view.findViewById(R.id.image_static);
            imageView_static=imageView;
            //    this.viewType=TYPE_LIST;


        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            Player player=this.player.get(position);

            Intent intent=new Intent(ctx,Edit_team.class);
            intent.putExtra("image", player.getPlayer_image());
            intent.putExtra("id",player.getPlayer_id());
            intent.putExtra("name",player.getTm_name());


            this.ctx.startActivity(intent);



        }

    }
}
