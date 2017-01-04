package companyname.com.kpl.recycler_listviews_adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

/**
 * Created by admin on 12/27/2016.
 */
public class RecyclerAdapter_featplayer extends RecyclerView.Adapter <RecyclerAdapter_featplayer.RecyclerViewHolder>{
    private Context ctx;
    //private static final int TYPE_HEAD=0;
    //private static final int TYPE_LIST=1;


    ArrayList<FeatPlayer> arrayList=new ArrayList<>();

    public RecyclerAdapter_featplayer(ArrayList<FeatPlayer> arrayList, Context ctx) {

        this.arrayList=arrayList;
        this.ctx=ctx;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_featuredplayer,parent,false);
        RecyclerAdapter_featplayer.RecyclerViewHolder recyclerViewHolder=new RecyclerAdapter_featplayer.RecyclerViewHolder(view,viewType,ctx,arrayList);
        return recyclerViewHolder;
        //View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_players,parent,false);
        //RecyclerAdapter_featplayer.RecyclerViewHolder recyclerViewHolder=new RecyclerAdapter_featplayer.RecyclerViewHolder(view,viewType,ctx,arrayList);
        //return recyclerViewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        FeatPlayer player=arrayList.get(position);
        /*
        * HOLDER VALUES
        */
       holder.id.setText(Integer.toString(player.getId()));
        holder.matches_played.setText(Integer.toString(player.getMatches_played()));
        holder.goals_scored.setText(Integer.toString(player.getGoals_scored()));
        holder.yellow_cards.setText(Integer.toString(player.getYellow_cards()));
        holder.red_cards.setText(Integer.toString(player.getRed_cards()));
        holder.assists.setText(Integer.toString(player.getAssists()));
        holder.player_name.setText(player.getPlayer_name());
        holder.player_pos.setText(player.getPlayer_pos());
        holder.player_team.setText(player.getPlayer_team());

        Glide.with(holder.feat_image.getContext()).load(player.getImage())
                .error(R.drawable.default_player)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.feat_image);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            //int id,matches_played,goals_scored,yellow_cards,red_cards,assists;
            TextView id,matches_played,goals_scored,yellow_cards,red_cards,assists,player_name,player_team,player_pos;
            ImageView feat_image;
            ArrayList<FeatPlayer>player=new ArrayList<>();
            Context ctx;
            public RecyclerViewHolder(View view,int viewType,Context ctx,ArrayList<FeatPlayer> player)
            {
                super(view);
                this.player=player;
                this.ctx=ctx;
                view.setOnClickListener(this);
                id= (TextView) view.findViewById(R.id.feat_id);
                matches_played= (TextView) view.findViewById(R.id.feat_matches_played);
                goals_scored= (TextView) view.findViewById(R.id.feat_goals);
                yellow_cards= (TextView) view.findViewById(R.id.feat_yellow);
                red_cards= (TextView) view.findViewById(R.id.feat_red);
                assists= (TextView) view.findViewById(R.id.feat_assists);
                player_name= (TextView) view.findViewById(R.id.feat_name);
                player_team= (TextView) view.findViewById(R.id.feat_team);
                player_pos= (TextView) view.findViewById(R.id.feat_pos);
                feat_image= (ImageView) view.findViewById(R.id.feat_image);
                //    this.viewType=TYPE_LIST;


        }

        @Override
        public void onClick(View v) {

            int position=getAdapterPosition();
            FeatPlayer player=this.player.get(position);

            Intent intent=new Intent(ctx,FeaturedPlayer_Details.class);
            intent.putExtra("image", player.getImage());
            intent.putExtra("id",Integer.toString(player.getId()));
            intent.putExtra("name",player.getPlayer_name());
            intent.putExtra("pos",player.getPlayer_pos());
            intent.putExtra("teamname",player.getPlayer_team());
            intent.putExtra("matches",Integer.toString(player.getMatches_played()));
            intent.putExtra("goals",Integer.toString(player.getGoals_scored()));
            intent.putExtra("yellow",Integer.toString(player.getYellow_cards()));
            intent.putExtra("red",Integer.toString(player.getRed_cards()));
            intent.putExtra("assists",Integer.toString(player.getAssists()));
            this.ctx.startActivity(intent);

            ((Activity)ctx).finish();

        }
    }
}
