package pl.agh.po.vpp.domain;

/**
 * Magazyn energii.
 * Propozycja dla osoby A (vpp-engine) – sygnatury do jej decyzji.
 */
public interface Storage extends EnergyAsset {

    BatteryState state();

    /** Maksymalna moc ładowania i rozładowania [kW]. */
    double ratedPowerKw();

    /**
     * Wykonuje polecenie przez jeden krok i zmienia stan naładowania.
     *
     * @param requestedKw żądana moc: + ładowanie, − rozładowanie
     * @param stepHours   długość kroku w godzinach
     * @return moc faktycznie wykonana, przycięta do limitów mocy i pojemności
     */
    double apply(double requestedKw, double stepHours);
}
