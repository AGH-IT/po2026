package pl.agh.po.vpp.domain;

import java.time.LocalTime;

/**
 * Zdarzenie operatora: element nie działa w podanym przedziale czasu (awaria wiatraka,
 * wyłączenie paneli na bloku). Przedział [from, to); to == 00:00 oznacza "do końca doby".
 */
public record AssetOutage(String assetId, LocalTime from, LocalTime to) {

    public AssetOutage {
        if (assetId == null || assetId.isBlank()) {
            throw new IllegalArgumentException("Awaria bez id elementu");
        }
        if (from == null || to == null) {
            throw new IllegalArgumentException("Awaria '" + assetId + "' bez przedziału czasu");
        }
        if (!to.equals(LocalTime.MIDNIGHT) && !from.isBefore(to)) {
            throw new IllegalArgumentException("Awaria '" + assetId + "': początek musi być przed końcem");
        }
    }

    /** Awaria przez całą dobę. */
    public static AssetOutage allDay(String assetId) {
        return new AssetOutage(assetId, LocalTime.MIDNIGHT, LocalTime.MIDNIGHT);
    }

    public boolean isActiveAt(LocalTime time) {
        boolean afterStart = !time.isBefore(from);
        boolean beforeEnd = to.equals(LocalTime.MIDNIGHT) || time.isBefore(to);
        return afterStart && beforeEnd;
    }
}