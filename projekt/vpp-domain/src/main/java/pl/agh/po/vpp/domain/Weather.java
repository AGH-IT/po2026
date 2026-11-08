package pl.agh.po.vpp.domain;

import java.time.Instant;

/**
 * Pogoda w jednym kroku czasu.
 *
 * @param time                 początek kroku
 * @param tiltedIrradianceWm2  promieniowanie na nachylony panel [W/m²] (Open-Meteo: global_tilted_irradiance)
 * @param windSpeed80mMs       prędkość wiatru na 80 m [m/s] (Open-Meteo: wind_speed_80m)
 * @param temperatureC         temperatura powietrza [°C]
 * @param cloudCoverPct        zachmurzenie [%], 0–100
 */
public record Weather(
        Instant time,
        double tiltedIrradianceWm2,
        double windSpeed80mMs,
        double temperatureC,
        double cloudCoverPct) {

    public Weather {
        if (time == null) {
            throw new IllegalArgumentException("Pogoda bez czasu");
        }
        if (tiltedIrradianceWm2 < 0 || windSpeed80mMs < 0) {
            throw new IllegalArgumentException("Promieniowanie i wiatr nie mogą być ujemne (" + time + ")");
        }
        if (cloudCoverPct < 0 || cloudCoverPct > 100) {
            throw new IllegalArgumentException("Zachmurzenie poza 0–100%: " + cloudCoverPct);
        }
    }
}