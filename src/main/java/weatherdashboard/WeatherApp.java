package weatherdashboard;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import weatherdashboard.model.Weather;
import weatherdashboard.service.WeatherService;

/**
 * Main JavaFX application class for the Weather Dashboard.
 *
 * This application allows the user to enter a city name and retrieve
 * Real time weather data from the OpenWeatherMap API.
 */
public class WeatherApp extends Application {

  /**
   * Starts the JavaFX application.
   *
   * @param stage the primary stage for the application
   */
  @Override
  public void start(Stage stage) {

    Label title = new Label("Weather Dashboard");

    title.setStyle(
        "-fx-font-size: 24px;"
        + "-fx-font-weight: bold;"
        + "-fx-text-fill: darkblue;");

    TextField cityField = new TextField();
    cityField.setPromptText("Enter a city");
    cityField.setMaxWidth(200);

    Button button = new Button("Get Weather");

    button.setStyle(
        "-fx-font-size: 14px;"
        + "-fx-background-color: darkblue;"
        + "-fx-text-fill: white;");

    Label temp = new Label("Temperature:");
    Label condition = new Label("Condition:");
    Label humidity = new Label("Humidity:");
    Label wind = new Label("Wind Speed:");

    Label status = new Label("Status: Waiting for input...");

    button.setOnAction(e -> {
      String city = cityField.getText();

      if (city.isBlank()) {
        status.setText("Status: Please enter a city.");
        return;
      }

      try {
        WeatherService service = new WeatherService();
        Weather weather = service.getWeather(city);

        String weatherCondition = weather.getCondition().toLowerCase();

        String icon = "🌤️";

        if (weatherCondition.contains("clear")) {
          icon = "☀️";
        } else if (weatherCondition.contains("cloud")) {
          icon = "☁️";
        } else if (weatherCondition.contains("rain")) {
          icon = "🌧️";
        } else if (weatherCondition.contains("snow")) {
          icon = "❄️";
        } else if (weatherCondition.contains("storm")) {
          icon = "⛈️";
        }

        String formattedCondition = weather.getCondition();

        formattedCondition = formattedCondition.substring(0, 1).toUpperCase()+ formattedCondition.substring(1);

        temp.setText(
            "🌡 Temperature: "
            + Math.round(weather.getTemperature())
            + " °F");

        condition.setText(icon + " Condition: " + formattedCondition);

        humidity.setText("💧 Humidity: " + weather.getHumidity() + "%");

        wind.setText(
            "💨 Wind Speed: "
                + weather.getWindSpeed()
                + weather.getWindSpeed()
                + " mph");

        status.setText("Status: Weather loaded for " + weather.getCityName());

      } catch (Exception ex) {
        status.setText("Error: Could not retrieve weather data.");
      }
    });

    VBox root = new VBox(
        15,
        title,
        cityField,
        button,
        temp,
        condition,
        humidity,
        wind,
        status);

    root.setAlignment(Pos.CENTER);

    root.setStyle(
        "-fx-padding: 25;"
            + "-fx-background-color: linear-gradient(lightblue, white);");

    Scene scene = new Scene(root, 400, 400);

    stage.setTitle("Weather Dashboard");
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Launches the JavaFX application.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    launch();
  }
}