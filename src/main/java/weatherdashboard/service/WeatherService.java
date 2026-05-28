package weatherdashboard.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import weatherdashboard.model.Weather;
import weatherdashboard.exception.WeatherException;

/**
 * Handles retrieving weather data from API.
 */
public class WeatherService {

    private WeatherParser parser = new WeatherParser();


    /**
     * Retrives weather data for a city
     * 
     * @param city the city name
     * @return a weather object containing weather data
     * @throws WeatherException if weather data cannot be retrived
     */
    public Weather getWeather(String city) throws WeatherException {
        try {
            String apiKey = "3c618382ce7281aef1c2e3ed2bfc8b7f";

  
            String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);

            String urlString =
                "https://api.openweathermap.org/data/2.5/weather?q="
                + encodedCity
                + "&appid=" + apiKey
                + "&units=imperial";

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int statusCode = conn.getResponseCode();

            if (statusCode != 200) {
                BufferedReader errorReader = new BufferedReader(
                        new InputStreamReader(conn.getErrorStream()));

                StringBuilder errorResponse = new StringBuilder();
                String line;

                while ((line = errorReader.readLine()) != null) {
                    errorResponse.append(line);
                }

                errorReader.close();

                throw new WeatherException("API Error: " + errorResponse.toString());
            }

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            return parser.parseWeather(response.toString());

        } catch (Exception e) {
            throw new WeatherException("Error fetching weather: " + e.getMessage());
        }
    }
}