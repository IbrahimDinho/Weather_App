package com.IbrahimE.weather;

public class WeatherReportModel {
//    "id":5849599278317568,
//            "weather_state_name":"Heavy Cloud",
//            "weather_state_abbr":"hc",
//            "wind_direction_compass":"SW",
//            "created":"2021-08-12T12:56:01.869146Z","applicable_date":"2021-08-12","min_temp":15.605,"max_temp":23.27,
//            "the_temp":21.725,"wind_speed":6.865090561030249,
//            "wind_direction":214.85483332263584,"air_pressure":1018.5,
//            "humidity":75,"visibility":8.955201622524458,
//            "predictability":71}
    private int id;
    private String weather_state_name;
    private String weather_state_abbr;
    private String wind_direction_compass;
    private String created_data; // much more specific then date
    private String date; //"2021-08-12
    private int min_temp;
    private int max_temp;
    private int the_temp;
    private double wind_speed;
    private double wind_direction;
    private double air_pressure;
    private int humidity;
    private int visibility;
    private double predictability; // i think this is how accurate 1-100.

    public WeatherReportModel(int id, String weather_state_name, String weather_state_abbr, String wind_direction_compass, String created_data, String date, int min_temp, int max_temp, int the_temp, double wind_speed, double wind_direction, double air_pressure, int humidity, int visibility, int predictability) {
        this.id = id;
        this.weather_state_name = weather_state_name;
        this.weather_state_abbr = weather_state_abbr;
        this.wind_direction_compass = wind_direction_compass;
        this.created_data = created_data;
        this.date = date;
        this.min_temp = min_temp;
        this.max_temp = max_temp;
        this.the_temp = the_temp;
        this.wind_speed = wind_speed;
        this.wind_direction = wind_direction;
        this.air_pressure = air_pressure;
        this.humidity = humidity;
        this.visibility = visibility;
        this.predictability = predictability;
    }
    public WeatherReportModel(){

    }
    @Override
    public String toString() {
        return
                 "weather_state_name='" + weather_state_name + '\'' +
                ", date='" + date + '\'' +
                ", the_temp=" + the_temp
                ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeather_state_name() {
        return weather_state_name;
    }

    public void setWeather_state_name(String weather_state_name) {
        this.weather_state_name = weather_state_name;
    }

    public String getWeather_state_abbr() {
        return weather_state_abbr;
    }

    public void setWeather_state_abbr(String weather_state_abbr) {
        this.weather_state_abbr = weather_state_abbr;
    }

    public String getWind_direction_compass() {
        return wind_direction_compass;
    }

    public void setWind_direction_compass(String wind_direction_compass) {
        this.wind_direction_compass = wind_direction_compass;
    }

    public String getCreated_data() {
        return created_data;
    }

    public void setCreated_data(String created_data) {
        this.created_data = created_data;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(int min_temp) {
        this.min_temp = min_temp;
    }

    public int getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(int max_temp) {
        this.max_temp = max_temp;
    }

    public int getThe_temp() {
        return the_temp;
    }

    public void setThe_temp(int the_temp) {
        this.the_temp = the_temp;
    }

    public double getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(double wind_speed) {
        this.wind_speed = wind_speed;
    }

    public double getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(double wind_direction) {
        this.wind_direction = wind_direction;
    }

    public double getAir_pressure() {
        return air_pressure;
    }

    public void setAir_pressure(int air_pressure) {
        this.air_pressure = air_pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public double getPredictability() {
        return predictability;
    }

    public void setPredictability(int predictability) {
        this.predictability = predictability;
    }
}
