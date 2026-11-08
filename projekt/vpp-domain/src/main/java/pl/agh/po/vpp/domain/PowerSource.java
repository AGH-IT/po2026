package pl.agh.po.vpp.domain;

/**
 * Element produkujący energię: panele, wiatrak.
 * Propozycja dla osoby A (vpp-engine) – sygnatury do jej decyzji.
 */
public interface PowerSource extends EnergyAsset {

    /** Produkowana moc w danych warunkach [kW], ≥ 0. */
    double powerKw(Conditions conditions);
}
