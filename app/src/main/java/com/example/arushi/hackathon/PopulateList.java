package com.example.arushi.hackathon;

import android.os.AsyncTask;
import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
        import android.widget.SimpleAdapter;

        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;


public class PopulateList extends edit {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_populate_list);

        mListView = (ListView) findViewById(R.id.lv_charities);
        mListView.setClickable(true);



       String strUrl = "http://graphapi.firstgiving.com/v1/list/organization?q=organization_name:"
                + "al*";

        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute(strUrl);



    }

    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;

        try {

            URL url = new URL(strUrl);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Accept-Encoding","gzip,deflate");
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("User-Agent","JakartaCommons-HttpClient/3.1");
            urlConnection.setRequestProperty("Host","graphapi.firstgiving.com");

            urlConnection.connect();

            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
            StringBuffer sb = new StringBuffer();

            String line = "";
            while( (line = br.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
            br.close();
        } catch (Exception e) {
            Log.d("Error downloading url", e.toString());
        }  finally {
            iStream.close();
        }

        return data;

    }

    private class DownloadTask extends AsyncTask<String, Void, String> {
        String data = null;

        @Override
        protected String doInBackground(String... url) {
            try {
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            ListViewLoaderTask listViewLoaderTask = new ListViewLoaderTask();
            listViewLoaderTask.execute(result);
        }
    }

    private class ListViewLoaderTask extends AsyncTask<String, Void, SimpleAdapter> {

        JSONObject jObject;
        @Override
        protected SimpleAdapter doInBackground(String... strJson) {
            try{
                jObject = new JSONObject(strJson[0]);
                CharityJSONParser charityJSONParser = new CharityJSONParser();
                charityJSONParser.parse(jObject);
            } catch (Exception e) {
                Log.d("JSON Exception1",e.toString());
            }

            CharityJSONParser charityJSONParser = new CharityJSONParser();

            List<HashMap<String,Object>> charities= null;

            try {
                charities = charityJSONParser.parse(jObject);
            } catch (Exception e) {
                Log.d("JSON Exception2",e.toString());
            }

            String[] from = {"Charity","Address"};
            int[] to = {R.id.tv_charity, R.id.tv_address};
            SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), charities,
                    R.layout.lv_layout,from,to);

            return simpleAdapter;
        }

        @Override
        protected void onPostExecute(SimpleAdapter simpleAdapter) {
            mListView.setAdapter(simpleAdapter);

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                        long l) {
                    HashMap<String,Object> click = (HashMap<String,Object>)
                            mListView.getItemAtPosition(position);
                   // clicked = (String)click.get("Charity");
                }
            });
        }

    }
}