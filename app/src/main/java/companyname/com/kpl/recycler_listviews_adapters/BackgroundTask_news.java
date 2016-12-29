package companyname.com.kpl.recycler_listviews_adapters;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import companyname.com.kpl.R;

/**
 * Created by admin on 12/13/2016.
 */

public class BackgroundTask_news extends AsyncTask<Void,News,Void> {
    private ProgressDialog loading;
    RecyclerAdapter_news recyclerAdapter_news;
    Context ctx;
    Activity activity;
    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    public RecyclerView.LayoutManager layoutManager;
  //  public SearchView searchView;
    public ArrayList<News> arrayList = new ArrayList<>();

    public BackgroundTask_news(Context ctx) {
        this.ctx = ctx;
        activity = (Activity) ctx;
    }


    String json_string="http://devkpl.com/KPL-Admin/wsGetNews";

    //String json_string = "http://devkpl.com/getList_news.php";

    @Override
    protected void onPreExecute() {

        recyclerView = (RecyclerView) activity.findViewById(R.id.recylerView_news);


        layoutManager = new LinearLayoutManager(ctx);
        //RecyclerView.LayoutManager layoutManager=new GridLayoutManager(ctx,2);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,dpToPx(10),true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter_news(arrayList,ctx);
        recyclerView.setAdapter(adapter);
        loading=new ProgressDialog(ctx);
        loading.setTitle("Please Wait..");
        loading.setMessage("List is now Loading!");
        loading.setIndeterminate(true);
        loading.setCancelable(false);
        loading.show();
    }

    @Override
    protected void onProgressUpdate(News... values) {
        arrayList.add(values[0]);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        loading.dismiss();
       /*
       * searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
              //  adapter.getFilter().filter(newText);
                Toast.makeText(activity.getApplicationContext(),"CLICKED",Toast.LENGTH_LONG).show();
                //adapter.getFilter.filter(newText);
                recyclerAdapter_news.getFilter(newText);
                return false;
            }
        });
       * */

    }



    @Override
    protected Void doInBackground(Void... voids) {


        try {

            URL url=new URL(json_string);
            HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder=new StringBuilder();
            String line;


            while((line=bufferedReader.readLine())!=null)
            {
                stringBuilder.append(line+"\n");
            }

            httpURLConnection.disconnect();
            String json_string=stringBuilder.toString().trim();
            JSONObject jsonObject=new JSONObject(json_string);
            JSONArray jsonArray=jsonObject.getJSONArray("server_response");
            int count=0;
            while(count<jsonArray.length())
            {
                JSONObject JO=jsonArray.getJSONObject(count);
                count++;
                //ForWB
                //News news=new News(JO.getString("author_name"),JO.getString("title"),JO.getInt("news_id"),JO.getString("description"),JO.getString("content"),JO.getString("image"),JO.getString("date"));
                News news=new News(JO.getString("image"),JO.getInt("news_id"),JO.getString("author_name"),JO.getString("title"),JO.getString("description"),JO.getString("content"),JO.getString("date"));
                publishProgress(news);
                Thread.sleep(100);
            }
            Log.e("JSON_STRING",json_string);





        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }



    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        //Resources r = getResources();
        Resources r=Resources.getSystem();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));

    }


}

