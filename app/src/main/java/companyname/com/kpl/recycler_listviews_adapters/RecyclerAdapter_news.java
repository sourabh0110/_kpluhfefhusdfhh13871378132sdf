package companyname.com.kpl.recycler_listviews_adapters;

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
 * Created by admin on 12/13/2016.
 */
public class RecyclerAdapter_news extends RecyclerView.Adapter <RecyclerAdapter_news.RecyclerViewHolder>{
    private Context ctx;
    //private static final int TYPE_HEAD=0;
    //private static final int TYPE_LIST=1;



    ArrayList<News> arrayList=new ArrayList<>();
    public RecyclerAdapter_news(ArrayList<News> arrayList,Context ctx)
    {

        this.arrayList=arrayList;
        this.ctx=ctx;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_news,parent,false);
            RecyclerViewHolder recyclerViewHolder=new RecyclerViewHolder(view,viewType,ctx,arrayList);
            return recyclerViewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {


        News news=arrayList.get(position);
            //holder.News_image.setText(news.getNews_image());
            //holder.imageView.setImageResource(news.getNews_image());
            //Picasso.with(holder.imageView.getContext()).load(news.getNews_image());
            holder.Id.setText(Integer.toString(news.getId()));
            holder.Name.setText(news.getName());
            holder.Title.setText(news.getTitle());
            holder.Desc.setText(news.getDesc());
            holder.News_date.setText(news.getNews_date());
            Glide.with(holder.imageView.getContext()).load(news.getNews_image())
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
        TextView Name,Title,Id,News_image,Desc,Content,News_date;
        ImageView imageView,imageView_static;
        ArrayList<News>news=new ArrayList<News>();
        Context ctx;
        public RecyclerViewHolder(View view,int viewType,Context ctx,ArrayList<News> news)
        {
            super(view);
            this.news=news;
            this.ctx=ctx;
            view.setOnClickListener(this);

                News_date=(TextView)view.findViewById(R.id.seven);
                Name=(TextView)view.findViewById(R.id.third);
                Title=(TextView)view.findViewById(R.id.four);
                Id=(TextView)view.findViewById(R.id.second);
                Desc=(TextView)view.findViewById(R.id.five);
                Content=(TextView)view.findViewById(R.id.six);
                imageView= (ImageView) view.findViewById(R.id.first);
                imageView_static=(ImageView)view.findViewById(R.id.image_static);
                imageView_static=imageView;
            //    this.viewType=TYPE_LIST;


        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            News news=this.news.get(position);

            Intent intent=new Intent(ctx,News_Details.class);
            intent.putExtra("image", news.getNews_image());
            intent.putExtra("news_id",news.getId());
            intent.putExtra("name",news.getName());
            intent.putExtra("title",news.getTitle());
            intent.putExtra("description",news.getDesc());
            intent.putExtra("content",news.getContent());
            intent.putExtra("date",news.getNews_date());



            this.ctx.startActivity(intent);



        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}

