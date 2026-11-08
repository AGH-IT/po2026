package pl.agh.po.vpp.domain;

/** Rodzaj elementu osiedla. Wartość pola "type" w estate.json. */
public enum AssetType {
    APARTMENT_BLOCK,
    HOUSE,
    SCHOOL,
    WIND_TURBINE,
    BATTERY,
    GRID;

    /** Budynek: zużywa prąd i może mieć panele na dachu. */
    public boolean isBuilding() {
        return this == APARTMENT_BLOCK || this == HOUSE || this == SCHOOL;
    }
}