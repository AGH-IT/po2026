package pl.agh.po.vpp.domain;

/**
 * Nie da się uzyskać pogody dla żądanego dnia: brak internetu i brak pliku w cache,
 * data spoza zakresu Open-Meteo, błędna odpowiedź API.
 */
public class WeatherUnavailableException extends RuntimeException {

    public WeatherUnavailableException(String message) {
        super(message);
    }

    public WeatherUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}