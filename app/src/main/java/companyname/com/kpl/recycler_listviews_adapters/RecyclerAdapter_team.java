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

import companyname.com.kpl.ImageDecoder;
import companyname.com.kpl.R;
import companyname.com.kpl.admin_files.Edit_team;

/**
 * Created by admin on 12/29/2016.
 */
public class RecyclerAdapter_team extends RecyclerView.Adapter <RecyclerAdapter_team.RecyclerViewHolder>{
private Context ctx;
        //private static final int TYPE_HEAD=0;
        //private static final int TYPE_LIST=1;


        ArrayList<Team> arrayList=new ArrayList<>();

public RecyclerAdapter_team(ArrayList<Team> arrayList, Context ctx) {

        this.arrayList=arrayList;
        this.ctx=ctx;
        }

@Override
public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_players_in_team,parent,false);
        RecyclerViewHolder recyclerViewHolder=new RecyclerViewHolder(view,viewType,ctx,arrayList);
        return recyclerViewHolder;

        //RecyclerAdapter_PremiereLeague.RecyclerViewHolder recyclerViewHolder=new RecyclerAdapter_PremiereLeague.RecyclerViewHolder(view,viewType,ctx,arrayList);
        //return recyclerViewHolder;
        }

    @Override
public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        /*
        * IF ALTERNATE ROW COLORS
        * if (position % 2 == 0) {
        holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
        holder.itemView.setBackgroundColor(Color.parseColor("#E8E8E8"));
        }
        *
        * */

        Team team=arrayList.get(position);
        //holder.News_image.setText(news.getNews_image());
        //holder.imageView.setImageResource(news.getNews_image());
        //Picasso.with(holder.imageView.getContext()).load(news.getNews_image());
        holder.Name.setText(team.getName());
        //holder.Title.setText(news.getTitle());
        //holder.Desc.setText(news.getDesc());
        //imageView.setImageBitmap(ImageDecoder.decodeSampledBitmapFromResource(ctx.getResources(),items.get(position).getImage(),100,100));
        Glide.with(holder.imageView.getContext())
        .load(team.getImage())
        //.asBitmap(ImageDecoder.decodeSampledBitmapFromResource(ctx.getResources(),R.id.image_static_player1,100,100))
        .error(R.drawable.default_player)
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
    TextView Name;
    ImageView imageView;

    ArrayList<Team>team=new ArrayList<Team>();
    Context ctx;
    public RecyclerViewHolder(View view,int viewType,Context ctx,ArrayList<Team> team)
    {
        super(view);
        this.team=team;
        this.ctx=ctx;
        view.setOnClickListener(this);


        Name=(TextView)view.findViewById(R.id.tv_player_name_fetch1);
        imageView= (ImageView) view.findViewById(R.id.iv_player_image);
        //imageView= (ImageView) view.findViewById(R.id.image_static_player1);
        //imageView.setImageBitmap(ImageDecoder.decodeSampledBitmapFromResource(ctx.getResources(),R.id.iv_player_image,100,100));
        //imageView.setImageBitmap(ImageDecoder.decodeSampledBitmapFromResource(ctx.getResources(),items.get(position).getImage(),100,100));
    }

    @Override
    public void onClick(View view) {
        /*
        *int position=getAdapterPosition();
        Team team=this.team.get(position);
        Intent intent=new Intent(ctx,Edit_team.class);
        intent.putExtra("image", premiereLeague.getTm_image());
        intent.putExtra("id",Integer.toString(premiereLeague.getTm_id()));
        intent.putExtra("name",premiereLeague.getTm_name());
        this.ctx.startActivity(intent);
        ((Activity)ctx).finish();
        * */

    }

}
}

