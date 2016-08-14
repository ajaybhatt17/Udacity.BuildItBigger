package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ajaybhatt.myapplication.backend.myApi.MyApi;
import com.example.jokedisplay.JokeDisplayActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by task on 14/08/16.
 */
public class ViewUtils {

    public static void showJokePage(final Context context, final ProgressBar progressBar) {
        new AsyncTask<String, String, String>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected String doInBackground(String... params) {
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl(String.format(Locale.ENGLISH, "http://%1$s:8080/_ah/api/", ViewUtils.geBackendIP(context)))
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                MyApi myApiService = builder.build();

                try {
                    return myApiService.joke().execute().getData();
                } catch (IOException e) {
                    return e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String result) {
                progressBar.setVisibility(View.GONE);
                Intent intent = new Intent(context, JokeDisplayActivity.class);
                intent.putExtra(JokeDisplayActivity.JOKE_TEXT, result);
                context.startActivity(intent);
            }
        }.execute("");
    }

    public static void showJokePage(final Context context) {
        new AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... params) {
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl(String.format(Locale.ENGLISH, "http://%1$s:8080/_ah/api/", ViewUtils.geBackendIP(context)))
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                MyApi myApiService = builder.build();

                try {
                    return myApiService.joke().execute().getData();
                } catch (IOException e) {
                    return e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String result) {
                Intent intent = new Intent(context, JokeDisplayActivity.class);
                intent.putExtra(JokeDisplayActivity.JOKE_TEXT, result);
                context.startActivity(intent);
            }
        }.execute("");
    }

    public static String geBackendIP(Context context) {

        Properties properties = new Properties();
        String key = "127.0.0.1";
        try {
            properties.load(context.getAssets().open("key.properties"));
            key = properties.getProperty("backend.ip");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return key;
    }

}
