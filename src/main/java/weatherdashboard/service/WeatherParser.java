package weatherdashboard.service;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import weatherdashboard.model.Weather;

/**
 * Parses JSON weather data into weather objects.
 */
public class WeatherParser {
    
    /**
     * Parses a JSOn weather response
     * 
     * @param json the JSON weather data
     * @return a weather object containing parsed data
     */
    public Weather parseWeather(String json) {
        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

        String city = obj.get("name").getAsString();

        double temp = obj.getAsJsonObject("main")
                         .get("temp").getAsDouble();

        int humidity = obj.getAsJsonObject("main")
                          .get("humidity").getAsInt();

        String condition = obj.getAsJsonArray("weather")
                              .get(0).getAsJsonObject()
                              .get("description").getAsString();

        double windSpeed = obj.getAsJsonObject("wind")
                              .get("speed").getAsDouble();


        return new Weather(city, temp, condition, humidity, windSpeed);
    }
}