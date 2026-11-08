package pl.agh.po.vpp.domain;

import java.time.ZoneId;

/** Punkt osiedla na mapie, potrzebny do zapytań o pogodę i pozycji słońca. */
public record Location(double latitude, double longitude, ZoneId zone) {

    public Location {
        if (latitude < -90 || latitude > 90) {
            throw new InvalidEstateException("Szerokość geograficzna poza zakresem: " + latitude);
        }
        if (longitude < -180 || longitude > 180) {
            throw new InvalidEstateException("Długość geograficzna poza zakresem: " + longitude);
        }
        if (zone == null) {
            throw new InvalidEstateException("Brak strefy czasowej lokalizacji");
        }
    }
}