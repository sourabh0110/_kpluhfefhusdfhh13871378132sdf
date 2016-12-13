package companyname.com.kpl.admin_files;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import companyname.com.kpl.R;

/**
 * Created by admin on 12/13/2016.
 */
public class RecyclerAdapter_news extends RecyclerView.Adapter <RecyclerAdapter_news.RecyclerViewHolder>{
    private Context context;
    private static final int TYPE_HEAD=0;
    private static final int TYPE_LIST=1;



    ArrayList<News> arrayList=new ArrayList<>();
    public RecyclerAdapter_news(ArrayList<News> arrayList)
    {
        this.arrayList=arrayList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==TYPE_HEAD)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.header_layout_news,parent,false);
            RecyclerViewHolder recyclerViewHolder=new RecyclerViewHolder(view,viewType);
            return recyclerViewHolder;
        }
        else if(viewType==TYPE_LIST)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_news,parent,false);
            RecyclerViewHolder recyclerViewHolder=new RecyclerViewHolder(view,viewType);
            return recyclerViewHolder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        if(holder.viewType==TYPE_LIST)
        {
            News news=arrayList.get(position-1);
            //holder.News_image.setText(news.getNews_image());
            //holder.imageView.setImageResource(news.getNews_image());
            //Picasso.with(holder.imageView.getContext()).load(news.getNews_image());
            holder.Id.setText(Integer.toString(news.getId()));
            holder.Name.setText(news.getName());
            holder.Title.setText(news.getTitle());
            Glide.with(holder.imageView.getContext()).load(news.getNews_image())
                    .error(R.mipmap.ic_launcher)
                    .into(holder.imageView);

            /*
            * Glide.with(holder.imageView.getContext()).load("http://devkpl.com/news/uploads/" + news.getNews_image())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(holder.imageView);
            *
            * */


        }


    }

    @Override
    public int getItemCount() {
        return arrayList.size()+1;
    }
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {

        int viewType;
        TextView Name,Title,Id,News_image,Desc,Content,News_date;
        ImageView imageView;
        public RecyclerViewHolder(View view,int viewType)
        {
            super(view);
            if(viewType==TYPE_LIST)
            {
                Name=(TextView)view.findViewById(R.id.third);
                Title=(TextView)view.findViewById(R.id.four);
                Id=(TextView)view.findViewById(R.id.second);
                //News_image=(TextView)view.findViewById(R.id.first);
                imageView= (ImageView) view.findViewById(R.id.first);
                this.viewType=TYPE_LIST;
            }
            else if(viewType==TYPE_HEAD)
            {
                this.viewType=TYPE_HEAD;
            }

        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0)
            return TYPE_HEAD;
        return TYPE_LIST;
    }
}

