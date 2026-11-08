package pl.agh.po.vpp.domain;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Wynik jednego kroku symulacji dla całego osiedla.
 * Znaki: batteryKw + ładowanie / − rozładowanie; gridKw + import / − eksport.
 * Bilans: productionKw + gridKw = consumptionKw + batteryKw.
 *
 * @param time          początek kroku
 * @param step          długość kroku
 * @param conditions    pogoda i cena w tym kroku
 * @param flows         moc każdego elementu osobno (dla sceny 3D i dymków)
 * @param productionKw  produkcja paneli i wiatraka razem [kW], ≥ 0
 * @param consumptionKw zużycie budynków razem [kW], ≥ 0
 * @param batteryKw     moc baterii [kW]
 * @param gridKw        moc wymiany z siecią [kW]
 * @param batterySocKwh stan naładowania baterii na końcu kroku [kWh]
 */
public record StepResult(
        Instant time,
        Duration step,
        Conditions conditions,
        List<AssetFlow> flows,
        double productionKw,
        double consumptionKw,
        double batteryKw,
        double gridKw,
        double batterySocKwh) {

    public StepResult {
        if (time == null || step == null || conditions == null) {
            throw new IllegalArgumentException("StepResult wymaga czasu, kroku i warunków");
        }
        flows = flows == null ? List.of() : List.copyOf(flows);
        if (productionKw < 0 || consumptionKw < 0 || batterySocKwh < 0) {
            throw new IllegalArgumentException("Produkcja, zużycie i SoC nie mogą być ujemne (" + time + ")");
        }
    }

    public double stepHours() {
        return step.toSeconds() / 3600.0;
    }

    /** Zamienia moc [kW] na energię w tym kroku [kWh]. */
    public double energyKwh(double powerKw) {
        return powerKw * stepHours();
    }

    /** Import z sieci [kW], ≥ 0. */
    public double importKw() {
        return Math.max(gridKw, 0);
    }

    /** Eksport do sieci [kW], ≥ 0. */
    public double exportKw() {
        return Math.max(-gridKw, 0);
    }

    /** Ile się nie zgadza w bilansie [kW]; poprawny krok daje ~0. */
    public double balanceErrorKw() {
        return productionKw + gridKw - consumptionKw - batteryKw;
    }

    public Optional<AssetFlow> flowOf(String assetId) {
        return flows.stream().filter(f -> f.assetId().equals(assetId)).findFirst();
    }
}
