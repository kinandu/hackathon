package com.hack.easy2invest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hack.easy2invest.model.Company;
import com.hack.easy2invest.model.Items;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class DetailsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private String companyName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            companyName = extras.getString("name");
            // and get whatever type user account id is
        }
        new GetDetailsService1().execute();
         recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class GetDetailsService1 extends
            AsyncTask<String, String, String> {
        private static final String API_KEY = "FHETJoZ5BYm9hH6Dyp4vlLJz0lF1pbye";
        private static final String BASE_URL = "https://financialmodelingprep.com/api/v3/search";
        String apiKey = "apikey=" + API_KEY;
        String query = "query=" + companyName;

        String url1 = String.format("%s?%s&%s", BASE_URL, query, apiKey);

        @Override
        protected String doInBackground(String... uri) {
            try {
                URL url = new URL(url1);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Log the server response code
                int responseCode = connection.getResponseCode();
                Log.i("TAG", "Server responded with: " + responseCode);

                // And if the code was HTTP_OK then parse the contents
                if (responseCode == HttpURLConnection.HTTP_OK) {

                    // Convert request content to string
                    InputStream is = connection.getInputStream();
                    String content = convertInputStream(is, "UTF-8");
                    is.close();

                    return content;
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }

        private String convertInputStream(InputStream is, String encoding) {
            Scanner scanner = new Scanner(is, encoding).useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // Hide progressbar
            // setProgressBarIndeterminateVisibility(false);

            if (result != null) {
                Gson g = new Gson();
               // Company s = g.fromJson(result, Company.class);
                Items[] users = null;

                try {
                     users = g.fromJson(result, Items[].class);
                }
                catch (JsonSyntaxException e) {
                    if (e.getMessage().equals(
                            "Expected BEGIN_OBJECT but was BEGIN_ARRAY")) {
                        JsonArray jsonArray = new JsonParser()
                                .parse(result)
                                .getAsJsonArray();
                        for (int i = 0; i < jsonArray.size(); i++) {
                            Items user = g.fromJson(jsonArray.get(i),
                                    Items.class);
                            // do something with the user object
                        }
                    }
                    else {
                        // Handle other JSON parsing errors
                    }
                }
                //
                Items itemvalues[] = new Items[0];
                for (Items items: users) {

                    if (items.getExchangeShortName().equals("NASDAQ")){
                        itemvalues = new Items[]{items};
                    }
                }



                MyListAdapter adapter = new MyListAdapter(itemvalues);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(DetailsActivity.this));
                recyclerView.setAdapter(adapter);
                // Create a dialog
          /*  AlertDialog.Builder builder = new AlertDialog.Builder(ge);
            builder.setMessage(result.substring(0, 200) + "...");
            builder.setPositiveButton("OK", null);

            // and show it
            builder.create().show();*/
            }
        }



    }

}
