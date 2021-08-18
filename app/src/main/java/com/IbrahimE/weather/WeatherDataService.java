package com.IbrahimE.weather;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataService {

    private static final String API_LOCATION_SEARCH_QUERY = "https://www.metaweather.com/api/location/search/?query=";
    private static final String API_FORECAST_QUERY = "https://www.metaweather.com/api/location/";

    Context context;

    public WeatherDataService(Context context){
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String response);
    }

    private void getCityWeatherID(String cityName, VolleyResponseListener volleyResponseListener){
        String url = API_LOCATION_SEARCH_QUERY + cityName;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String cityID ="";

                try {
                    JSONObject city = response.getJSONObject(0);
                    cityID = city.getString("woeid");
                    volleyResponseListener.onResponse(cityID);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volleyResponseListener.onError("Something went Wrong try ReInputting Location");
            }
        });


        // Add the request to the RequestQueue.
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
    public interface VolleyResponseListenerForecastById {
        void onError(String message);

        void onResponse(List<WeatherReportModel> weatherReportModel);
    }

    private void getCityForecastByID(String id, VolleyResponseListenerForecastById forecastByIdResponse){
        // hold list of data for each weather day
        List<WeatherReportModel> report = new ArrayList<WeatherReportModel>();
        String url = API_FORECAST_QUERY + id;
        // get object
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray consolidated_weather_list = response.getJSONArray("consolidated_weather");

                    for (int i=0; i<consolidated_weather_list.length();i++) {
                        WeatherReportModel one_day = new WeatherReportModel();
                        JSONObject first_day_from_api = (JSONObject) consolidated_weather_list.get(i);
                        // may need to use LONG ------
                        one_day.setId(first_day_from_api.getInt("id"));
                        one_day.setWeather_state_name(first_day_from_api.getString("weather_state_name"));
                        one_day.setWeather_state_abbr(first_day_from_api.getString("weather_state_abbr"));
                        one_day.setThe_temp(first_day_from_api.getInt("the_temp"));
                        one_day.setDate(first_day_from_api.getString("applicable_date"));

                        report.add(one_day);
                    }
                        //send back list of weathers to main activity
                        forecastByIdResponse.onResponse(report);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        MySingleton.getInstance(context).addToRequestQueue(request);

    }
    public interface GetCityForecastByNameCallBack{
        void onError(String message);

        void onResponse(List<WeatherReportModel> weatherReportModel);
    }
    public void getCityForecastByName(String cityName, GetCityForecastByNameCallBack getCityForecastByNameCallBack ){
        // get city id using name
        getCityWeatherID(cityName, new VolleyResponseListener() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(String cityID) {
                // now have cityID returned in response parameter
                getCityForecastByID(cityID, new VolleyResponseListenerForecastById() {
                    @Override
                    public void onError(String message) {

                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModel) {
                        // have weather report
                        getCityForecastByNameCallBack.onResponse(weatherReportModel);
                    }
                });
            }
        });

    }
    }
