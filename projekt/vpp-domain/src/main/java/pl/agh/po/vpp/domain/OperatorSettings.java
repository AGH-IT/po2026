package pl.agh.po.vpp.domain;

import java.util.List;

/**
 * Wszystko, co operator ustawia przed symulacją.
 *
 * @param strategy      wybrana strategia
 * @param batteryMode   ręczne sterowanie magazynem
 * @param outages       awarie i wyłączenia elementów
 * @param exportLimitKw limit eksportu do sieci [kW]; null = brak limitu
 * @param seed          ziarno losowości (dane syntetyczne), ten sam seed = ten sam wynik
 */
public record OperatorSettings(
        StrategyType strategy,
        BatteryMode batteryMode,
        List<AssetOutage> outages,
        Double exportLimitKw,
        long seed) {

    public static final long DEFAULT_SEED = 42L;

    public OperatorSettings {
        if (strategy == null) {
            strategy = StrategyType.RULE_BASED;
        }
        if (batteryMode == null) {
            batteryMode = BatteryMode.AUTO;
        }
        outages = outages == null ? List.of() : List.copyOf(outages);
        if (exportLimitKw != null && exportLimitKw < 0) {
            throw new IllegalArgumentException("Limit eksportu nie może być ujemny: " + exportLimitKw);
        }
    }

    public static OperatorSettings defaults() {
        return new OperatorSettings(StrategyType.RULE_BASED, BatteryMode.AUTO, List.of(), null, DEFAULT_SEED);
    }

    public boolean hasExportLimit() {
        return exportLimitKw != null;
    }

    /** Te same ustawienia z inną strategią: do porównania dwóch strategii dla jednego dnia. */
    public OperatorSettings withStrategy(StrategyType newStrategy) {
        return new OperatorSettings(newStrategy, batteryMode, outages, exportLimitKw, seed);
    }
}