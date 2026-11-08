package pl.agh.po.vpp.domain;

import java.util.List;

/**
 * Źródło pogody dla symulacji. Implementacje w vpp-data:
 * SyntheticWeatherProvider (dane z seeda), OpenMeteoWeatherProvider (prawdziwe API),
 * CachingWeatherProvider (dekorator zapisujący dni do plików).
 */
public interface WeatherProvider {

    /**
     * Pogoda dla każdego kroku doby.
     *
     * @return lista o długości {@code timeline.size()}; element i odpowiada {@code timeline.steps().get(i)}
     * @throws WeatherUnavailableException gdy nie da się uzyskać danych dla tego dnia
     */
    List<Weather> weatherFor(Timeline timeline, Location location);
}
