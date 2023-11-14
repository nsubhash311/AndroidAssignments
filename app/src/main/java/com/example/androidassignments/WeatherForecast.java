package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class WeatherForecast extends AppCompatActivity {
    ProgressBar progressBar;
    ImageView imageView;
    TextView current_temp;
    TextView min_temp;
    TextView max_temp;
    List<String> cityList;
    TextView cityName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        setTitle("Weather Network Information");
        current_temp = findViewById(R.id.txtCurr);
        min_temp = findViewById(R.id.txtMin);
        max_temp = findViewById(R.id.txtMax);
        imageView = findViewById(R.id.weatherimg);
        cityName = findViewById(R.id.cityname);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        get_a_city();
    }

    public void get_a_city() {

        cityList = Arrays.asList(getResources().getStringArray(R.array.Cities));

        final Spinner citySpinner = findViewById(R.id.citySpinner);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource( this, R.array.Cities, android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(adapter);
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView <?> adapterView, View view, int i, long l)
            {
                new ForecastQuery(cityList.get(i)).execute();
                cityName.setText(cityList.get(i) + " Weather"); }
            @Override
            public void onNothingSelected(AdapterView <?> adapterView) {
            }
        });
    }
    public class ForecastQuery extends AsyncTask<String, Integer, String> {
        private final String cityName;
        public String minTemp;
        public String maxTemp;
        public String currTemp;
        public Bitmap WeatherImg;

        public ForecastQuery(String cityName) {
            this.cityName = cityName;
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(
                        "https://api.openweathermap.org/" + "data/2.5/weather?q=" + cityName + "," + "ca&APPID=79cecf493cb6e52d25bb7b7050ff723c&" + "mode=xml&units=metric");
                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                InputStream in = conn.getInputStream(); try {
                    XmlPullParser parser = Xml.newPullParser();
                    parser.setFeature( XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    parser.setInput(in, null);
                    int type;
                    while ((type = parser.getEventType()) != XmlPullParser.END_DOCUMENT) {
                        if (parser.getEventType() == XmlPullParser.START_TAG) { if (parser.getName().equals("temperature")) {
                            currTemp = parser.getAttributeValue(null, "value");
                            publishProgress(25);

                            minTemp = parser.getAttributeValue(null, "min");
                            publishProgress(50);
                            maxTemp = parser.getAttributeValue(null, "max");
                            publishProgress(75);
                        } else if (parser.getName().equals("weather")) {
                            String iconName = parser.getAttributeValue(null, "icon");
                            String fileName = iconName + ".png";
                            Log.i("WeatherForecast", "Looking for file: " + fileName);
                            if (fileExistance(fileName)) {
                                FileInputStream fis = null;
                                try {
                                    fis = openFileInput(fileName);
                                }
                                catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                Log.i("WeatherForecast", "Found the file locally");
                                WeatherImg = BitmapFactory.decodeStream(fis);
                            }
                            else {
                                String iconUrl = "https://openweathermap.org/img/w/" + fileName;
                                WeatherImg = getImage(new URL(iconUrl));
                                FileOutputStream outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                                WeatherImg.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                                Log.i("WeatherForecast", "Downloaded the file from the Internet");
                                outputStream.flush();
                                outputStream.close();
                            }
                            publishProgress(100);
                        } }
                        parser.next();
                    }
                } finally { in.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            current_temp.setText("Current Temperature" + ": " + currTemp+"°C");
            min_temp.setText("Minimum Temperature" + ": " + minTemp+"°C");
            max_temp.setText("Maximum Temperature" + ": " + maxTemp+"°C");
            imageView.setImageBitmap(WeatherImg);
            progressBar.setVisibility(View.INVISIBLE);
        }

        public boolean fileExistance(String fname) {
            File file = getBaseContext().getFileStreamPath(fname);
            return file.exists();
        }
        public Bitmap getImage(URL url) {
            HttpsURLConnection connection = null;
            try {
                connection = (HttpsURLConnection) url.openConnection(); connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    return BitmapFactory.decodeStream(connection.getInputStream());
                } else
                    return null;
            } catch (Exception e) {
                return null;
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }
    }
}