package pl.agh.po.vpp.domain;

/**
 * Wspólny typ wszystkich elementów osiedla w silniku.
 * Propozycja dla osoby A – szczegóły (np. sealed w vpp-engine) do jej decyzji.
 */
public interface EnergyAsset {

    /** Id z estate.json, to samo co w {@link AssetConfig#id()} i {@link AssetFlow#assetId()}. */
    String id();

    AssetType type();
}
