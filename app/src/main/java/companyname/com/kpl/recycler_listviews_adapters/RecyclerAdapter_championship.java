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

/**
 * Created by admin on 12/17/2016.
 */
public class RecyclerAdapter_championship extends RecyclerView.Adapter <RecyclerAdapter_championship.RecyclerViewHolder>{
    private Context ctx;
    ArrayList<Championship> arrayList=new ArrayList<>();
    public RecyclerAdapter_championship(ArrayList<Championship> arrayList, Context ctx) {
        this.arrayList=arrayList;
        this.ctx=ctx;
    }

    @Override
    public RecyclerAdapter_championship.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_championship,parent,false);
        RecyclerAdapter_championship.RecyclerViewHolder recyclerViewHolder=new RecyclerAdapter_championship.RecyclerViewHolder(view,viewType,ctx,arrayList);
        return recyclerViewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerAdapter_championship.RecyclerViewHolder holder, int position) {
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#E8E8E8"));
        }

        Championship championship=arrayList.get(position);
        //holder.News_image.setText(news.getNews_image());
        //holder.imageView.setImageResource(news.getNews_image());
        //Picasso.with(holder.imageView.getContext()).load(news.getNews_image());
        holder.Id.setText(Integer.toString(championship.getTm_id()));
        holder.Name.setText(championship.getTm_name());
        //holder.Title.setText(news.getTitle());
        //holder.Desc.setText(news.getDesc());
        Glide.with(holder.imageView.getContext()).load(championship.getTm_image())
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
        ArrayList<Championship>championship=new ArrayList<Championship>();
        Context ctx;
        public RecyclerViewHolder(View view,int viewType,Context ctx,ArrayList<Championship> championship)
        {

            super(view);
            this.championship=championship;
            this.ctx=ctx;
            view.setOnClickListener(this);


            Name=(TextView)view.findViewById(R.id.second_team_name_championships);
            //Title=(TextView)view.findViewById(R.id.four);
            Id=(TextView)view.findViewById(R.id.third_team_code_championships);
            //Desc=(TextView)view.findViewById(R.id.five);
            //Content=(TextView)view.findViewById(R.id.six);
            imageView= (ImageView) view.findViewById(R.id.first_team_logo_championships);
            imageView_static=(ImageView)view.findViewById(R.id.image_static_championships);
            imageView_static=imageView;
            //    this.viewType=TYPE_LIST;


        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            Championship championship=this.championship.get(position);

            Intent intent=new Intent(ctx,Team_details.class);
            intent.putExtra("image", championship.getTm_image());
            //intent.putExtra("id",championship.getTm_id());
            intent.putExtra("id",Integer.toString(championship.getTm_id()));
            intent.putExtra("name",championship.getTm_name());


            this.ctx.startActivity(intent);
            ((Activity)ctx).finish();



        }
    }
}
