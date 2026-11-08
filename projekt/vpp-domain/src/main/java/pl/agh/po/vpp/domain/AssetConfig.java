package pl.agh.po.vpp.domain;

/**
 * Opis jednego elementu osiedla z estate.json.
 * Pola, które nie dotyczą danego typu, mają wartość 0 (np. capacityKwh dla domu).
 *
 * @param id           unikalny identyfikator, np. "block-1"
 * @param type         rodzaj elementu
 * @param name         nazwa do wyświetlenia w panelu i na scenie
 * @param pvPeakKw     moc szczytowa paneli na dachu [kW] (budynki)
 * @param ratedPowerKw moc znamionowa [kW]: wiatrak, moc ładowania/rozładowania baterii, limit przyłącza
 * @param capacityKwh  pojemność [kWh] (bateria)
 * @param dwellings    liczba mieszkań (budynki), skaluje profil zużycia
 * @param position     położenie na makiecie 3D
 */
public record AssetConfig(
        String id,
        AssetType type,
        String name,
        double pvPeakKw,
        double ratedPowerKw,
        double capacityKwh,
        int dwellings,
        Position position) {

    public AssetConfig {
        if (id == null || id.isBlank()) {
            throw new InvalidEstateException("Element osiedla bez id");
        }
        if (type == null) {
            throw new InvalidEstateException("Element '" + id + "' nie ma typu");
        }
        if (name == null || name.isBlank()) {
            name = id;
        }
        if (pvPeakKw < 0 || ratedPowerKw < 0 || capacityKwh < 0 || dwellings < 0) {
            throw new InvalidEstateException("Element '" + id + "' ma ujemny parametr");
        }
        if (type == AssetType.WIND_TURBINE && ratedPowerKw <= 0) {
            throw new InvalidEstateException("Wiatrak '" + id + "' musi mieć ratedPowerKw > 0");
        }
        if (type == AssetType.BATTERY && (capacityKwh <= 0 || ratedPowerKw <= 0)) {
            throw new InvalidEstateException("Bateria '" + id + "' musi mieć capacityKwh > 0 i ratedPowerKw > 0");
        }
        if (position == null) {
            position = Position.ORIGIN;
        }
    }
}