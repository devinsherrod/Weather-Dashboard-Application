package weatherdashboard.model;

/**
 * Represents weather data for a city.
 */
public class Weather {

  private String cityName;
  private double temperature;
  private String condition;
  private int humidity;
  private double windSpeed;

  /**
   * Constructs a Weather object.
   *
   * @param cityName the city name
   * @param temperature the temperature
   * @param condition the weather condition
   * @param humidity the humidity percentage
   * @param windSpeed the wind speed
   */
  public Weather(
      String cityName,
      double temperature,
      String condition,
      int humidity,
      double windSpeed) {

    this.cityName = cityName;
    this.temperature = temperature;
    this.condition = condition;
    this.humidity = humidity;
    this.windSpeed = windSpeed;
  }

  /**
   * Returns the city name.
   *
   * @return the city name
   */
  public String getCityName() {
    return cityName;
  }

  /**
   * Returns the temperature.
   *
   * @return the temperature
   */
  public double getTemperature() {
    return temperature;
  }

  /**
   * Returns the weather condition.
   *
   * @return the weather condition
   */
  public String getCondition() {
    return condition;
  }

  /**
   * Returns the humidity.
   *
   * @return the humidity percentage
   */
  public int getHumidity() {
    return humidity;
  }

  /**
   * Returns the wind speed.
   *
   * @return the wind speed
   */
  public double getWindSpeed() {
    return windSpeed;
  }
}