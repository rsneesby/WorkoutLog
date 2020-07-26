package com.example.ryan.workoutlog.application.persistance;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DBWorker {
    URL url;
 HttpURLConnection urlConnection;

    public DBWorker() throws IOException {

    }
    public void connect() throws IOException {
        try {
             url = new URL("ryansneesby.com");
             urlConnection = (HttpURLConnection) url.openConnection();
        }catch(Exception e){

        }finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
        }
    }
}
