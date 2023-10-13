package com.hack.easy2invest;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class GetDetailsService extends
     AsyncTask<String, String, String> {
    private static final String API_KEY = "FHETJoZ5BYm9hH6Dyp4vlLJz0lF1pbye";
    private static final String BASE_URL = "https://financialmodelingprep.com/api/v3/search";
    String apiKey = "apikey=" + API_KEY;
    private String companyName = "Wells Fargo";
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

            // Create a dialog
          /*  AlertDialog.Builder builder = new AlertDialog.Builder(ge);
            builder.setMessage(result.substring(0, 200) + "...");
            builder.setPositiveButton("OK", null);

            // and show it
            builder.create().show();*/
        }
    }



}
