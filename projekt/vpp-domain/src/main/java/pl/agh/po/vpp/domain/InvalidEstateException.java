package pl.agh.po.vpp.domain;

/**
 * Błędna konfiguracja osiedla: brak wymaganych pól, ujemne moce, powtórzone id itp.
 * Rzucany przy wczytywaniu estate.json (vpp-data) i przy budowie silnika (vpp-engine).
 */
public class InvalidEstateException extends RuntimeException {

    public InvalidEstateException(String message) {
        super(message);
    }

    public InvalidEstateException(String message, Throwable cause) {
        super(message, cause);
    }
}