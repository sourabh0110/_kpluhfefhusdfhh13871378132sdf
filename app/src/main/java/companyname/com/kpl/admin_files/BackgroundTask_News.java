package companyname.com.kpl.admin_files;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
    Context ctx;
    Activity activity;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<News> arrayList = new ArrayList<>();

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
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerAdapter_news(arrayList);
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
                News news=new News(JO.getString("image"),JO.getInt("news_id"),JO.getString("author_name"),JO.getString("title"));
                publishProgress(news);
                Thread.sleep(1000);
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


}

