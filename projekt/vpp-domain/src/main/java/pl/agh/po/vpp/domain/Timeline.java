package pl.agh.po.vpp.domain;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Oś czasu jednej symulowanej doby: od północy do północy w strefie osiedla, co {@code step}.
 * Zwykle 96 kroków po 15 min; w dni zmiany czasu 92 (marzec) albo 100 (październik).
 */
public record Timeline(LocalDate date, Duration step, ZoneId zone) {

    public static final Duration DEFAULT_STEP = Duration.ofMinutes(15);

    public Timeline {
        if (date == null || step == null || zone == null) {
            throw new IllegalArgumentException("Timeline wymaga daty, kroku i strefy czasowej");
        }
        if (step.isNegative() || step.isZero() || Duration.ofHours(1).toSeconds() % step.toSeconds() != 0) {
            throw new IllegalArgumentException("Krok musi dzielić godzinę bez reszty: " + step);
        }
    }

    public static Timeline of(LocalDate date, ZoneId zone) {
        return new Timeline(date, DEFAULT_STEP, zone);
    }

    /** Początek doby (lokalna północ). */
    public Instant start() {
        return date.atStartOfDay(zone).toInstant();
    }

    /** Koniec doby (następna lokalna północ), wyłącznie. */
    public Instant end() {
        return date.plusDays(1).atStartOfDay(zone).toInstant();
    }

    /** Początki kolejnych kroków w przedziale [start, end). */
    public List<Instant> steps() {
        List<Instant> result = new ArrayList<>();
        for (Instant t = start(); t.isBefore(end()); t = t.plus(step)) {
            result.add(t);
        }
        return List.copyOf(result);
    }

    public int size() {
        return (int) (Duration.between(start(), end()).toSeconds() / step.toSeconds());
    }

    /** Długość kroku w godzinach, do przeliczania kW na kWh: energia = moc × stepHours(). */
    public double stepHours() {
        return step.toSeconds() / 3600.0;
    }
}