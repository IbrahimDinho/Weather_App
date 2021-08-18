package com.IbrahimE.weather;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //widgets
    EditText et_dataInput;
    Button btn_getWeather;
    ListView lv_weatherReport;
    EditText view_todaysWeather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign widgets
        btn_getWeather = findViewById(R.id.btn_getWeather);
        et_dataInput = findViewById(R.id.et_dataInput);
        lv_weatherReport = findViewById(R.id.lv_weatherReports);
        view_todaysWeather = findViewById(R.id.view_todaysWeather);


        final WeatherDataService cityWeather = new WeatherDataService(MainActivity.this);

        // Search city with listener
        btn_getWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityWeather.getCityForecastByName(et_dataInput.getText().toString(), new WeatherDataService.GetCityForecastByNameCallBack() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModel) {
                        WeatherReportModel day_one = weatherReportModel.get(0);
                        //remove today weather from list
                        weatherReportModel.remove(0);
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportModel);
                        // will show toString method
                        lv_weatherReport.setAdapter(arrayAdapter);
                        view_todaysWeather.setText(day_one.toString());
                    }
                });

            }
        });


    }
}