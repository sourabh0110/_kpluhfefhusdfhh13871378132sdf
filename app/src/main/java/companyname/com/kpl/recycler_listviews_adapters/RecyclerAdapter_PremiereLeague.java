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
 * Created by admin on 12/16/2016.
 */
public class RecyclerAdapter_premiereLeague extends RecyclerView.Adapter <RecyclerAdapter_premiereLeague.RecyclerViewHolder>{
    private Context ctx;
    //private static final int TYPE_HEAD=0;
    //private static final int TYPE_LIST=1;


    ArrayList<PremiereLeague> arrayList=new ArrayList<>();

    public RecyclerAdapter_premiereLeague(ArrayList<PremiereLeague> arrayList, Context ctx) {

        this.arrayList=arrayList;
        this.ctx=ctx;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_premiereleague,parent,false);
        RecyclerViewHolder recyclerViewHolder=new RecyclerViewHolder(view,viewType,ctx,arrayList);
        return recyclerViewHolder;

        //RecyclerAdapter_PremiereLeague.RecyclerViewHolder recyclerViewHolder=new RecyclerAdapter_PremiereLeague.RecyclerViewHolder(view,viewType,ctx,arrayList);
        //return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#E8E8E8"));
        }

        PremiereLeague premiereLeague=arrayList.get(position);
        //holder.News_image.setText(news.getNews_image());
        //holder.imageView.setImageResource(news.getNews_image());
        //Picasso.with(holder.imageView.getContext()).load(news.getNews_image());
        holder.Id.setText(Integer.toString(premiereLeague.getTm_id()));
        holder.Name.setText(premiereLeague.getTm_name());
        //holder.Title.setText(news.getTitle());
        //holder.Desc.setText(news.getDesc());
        Glide.with(holder.imageView.getContext()).load(premiereLeague.getTm_image())
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
        TextView Name,Id;
        ImageView imageView,imageView_static;
        ArrayList<PremiereLeague>premiereLeagues=new ArrayList<PremiereLeague>();
        Context ctx;
        public RecyclerViewHolder(View view,int viewType,Context ctx,ArrayList<PremiereLeague> premiereLeagues)
        {
            super(view);
            this.premiereLeagues=premiereLeagues;
            this.ctx=ctx;
            view.setOnClickListener(this);


            Name=(TextView)view.findViewById(R.id.second_team_name);
            //Title=(TextView)view.findViewById(R.id.four);
            Id=(TextView)view.findViewById(R.id.third_team_code);
            //Desc=(TextView)view.findViewById(R.id.five);
            //Content=(TextView)view.findViewById(R.id.six);
            imageView= (ImageView) view.findViewById(R.id.first_team_logo);
            imageView_static=(ImageView)view.findViewById(R.id.image_static);
            imageView_static=imageView;
            //    this.viewType=TYPE_LIST;


        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            PremiereLeague premiereLeague=this.premiereLeagues.get(position);

            Intent intent=new Intent(ctx,Edit_team.class);
            intent.putExtra("image", premiereLeague.getTm_image());
            //intent.putExtra("id",premiereLeague.getTm_id());
            intent.putExtra("id",Integer.toString(premiereLeague.getTm_id()));
            intent.putExtra("name",premiereLeague.getTm_name());


            this.ctx.startActivity(intent);



        }

    }
}
