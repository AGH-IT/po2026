package pl.agh.po.vpp.domain;

/**
 * Odbiorca energii: blok, dom, szkoła (profil zużycia zależny od pory dnia i dnia tygodnia).
 * Propozycja dla osoby A (vpp-engine) – sygnatury do jej decyzji.
 */
public interface Load extends EnergyAsset {

    /** Pobierana moc w danych warunkach [kW], ≥ 0. */
    double demandKw(Conditions conditions);
}
