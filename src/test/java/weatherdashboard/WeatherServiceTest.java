package weatherdashboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import weatherdashboard.model.Weather;
import weatherdashboard.service.WeatherParser;
import weatherdashboard.service.WeatherService;

/**
 * Unit tests for WeatherParser
 */
public class WeatherServiceTest {
    /**
   * Tests parsing valid weather JSON.
   */
  @Test
  public void testParseWeather() {

    String json = """
        {
          "name": "Omaha",
          "main": {
            "temp": 72.5,
            "humidity": 40
          },
          "weather": [
            {
              "description": "clear sky"
            }
          ],
          "wind": {
            "speed": 5.0
          }
        }
        """;

    WeatherParser parser = new WeatherParser();
    Weather weather = parser.parseWeather(json);

    assertEquals("Omaha", weather.getCityName());
    assertEquals(72.5, weather.getTemperature());
    assertEquals("clear sky", weather.getCondition());
    assertEquals(40, weather.getHumidity());
    assertEquals(5.0, weather.getWindSpeed());
  }

  /**
   * Tests parsing invalid JSON.
   */
  @Test
  public void testInvalidJson() {

    String badJson = "invalid json";

    WeatherParser parser = new WeatherParser();

    assertThrows(Exception.class, () -> {
      parser.parseWeather(badJson);
    });
  }

  /**
   * Tests parsing JSON with missing fields.
   */
  @Test
  public void testMissingField() {

    String incompleteJson = """
        {
          "name": "Omaha"
        }
        """;

    WeatherParser parser = new WeatherParser();

    assertThrows(Exception.class, () -> {
      parser.parseWeather(incompleteJson);
    });
  }
}