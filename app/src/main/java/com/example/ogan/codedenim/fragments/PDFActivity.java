package com.example.ogan.codedenim.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ogan.codedenim.MyUtilClass;
import com.example.ogan.codedenim.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class PDFActivity extends AppCompatActivity {

    private  PDFView pdfView;
    private  ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_pdf);
        pdfView= findViewById(R.id.pdfView);
        progressBar = findViewById(R.id.pb_pdf);
        String fileLocation = getIntent().getStringExtra("fileLocation");
        setTitle(fileLocation);
        new RetrievePdfStream(this).execute("https://codedenim.azurewebsites.net/MaterialUpload/" + fileLocation);

    }

    private static class RetrievePdfStream extends AsyncTask<String, Void, Void> {
        private WeakReference<PDFActivity> weakReference;

        // only retain a weak reference to the activity
        RetrievePdfStream(PDFActivity context) {
            weakReference = new WeakReference<>(context);
        }

        @Override
        protected Void doInBackground(String... strings) {
            InputStream inputStream;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if(urlConnection.getResponseCode() == 200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());

                    // get a reference to the activity if it is still there
                    PDFActivity activity = weakReference.get();
                    if (activity == null) {
                        return null;
                    }
                    activity.pdfView.fromStream(inputStream).load();
                }
            } catch (IOException e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try{
                // get a reference to the activity if it is still there
                PDFActivity activity = weakReference.get();
                if (activity == null) {
                    return;
                }
                activity.progressBar.setVisibility(View.GONE);
            } catch (Exception e){
                e.printStackTrace();
            }

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            try{
                // get a reference to the activity if it is still there
                PDFActivity activity = weakReference.get();
                if (activity == null) {
                    return;
                }
                activity.progressBar.setVisibility(View.GONE);
                MyUtilClass.INSTANCE.showAlert(activity.pdfView.getContext(), "Sorry, an error occurred loading pdf");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                PDFActivity.super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
