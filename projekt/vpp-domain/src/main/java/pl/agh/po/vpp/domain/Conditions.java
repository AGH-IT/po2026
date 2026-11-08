package pl.agh.po.vpp.domain;

import java.time.Instant;

/**
 * Wszystko, co silnik wie o świecie w jednym kroku: pogoda i cena energii z sieci.
 *
 * @param pricePlnPerKwh cena energii [PLN/kWh]; może być ujemna (tak bywa na rynku TGE)
 */
public record Conditions(Instant time, Weather weather, double pricePlnPerKwh) {

    public Conditions {
        if (time == null || weather == null) {
            throw new IllegalArgumentException("Conditions wymaga czasu i pogody");
        }
    }
}