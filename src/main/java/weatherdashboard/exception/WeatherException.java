package weatherdashboard.exception;

/**
 * Custom exception for weather errors
 */
public class WeatherException extends Exception {
    /**
     * 
     * Constructs a WeatherException object
     * 
     * @param message the exception message
     */
    public WeatherException(String message) {
        super(message);
    }
}