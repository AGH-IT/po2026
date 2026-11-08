package pl.agh.po.vpp.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/** Całe osiedle: nazwa, lokalizacja i lista elementów. Wynik wczytania estate.json. */
public record EstateConfig(String name, Location location, List<AssetConfig> assets) {

    public EstateConfig {
        if (name == null || name.isBlank()) {
            throw new InvalidEstateException("Osiedle bez nazwy");
        }
        if (location == null) {
            throw new InvalidEstateException("Osiedle '" + name + "' nie ma lokalizacji");
        }
        if (assets == null || assets.isEmpty()) {
            throw new InvalidEstateException("Osiedle '" + name + "' nie ma żadnych elementów");
        }
        assets = List.copyOf(assets);

        Set<String> ids = new HashSet<>();
        for (AssetConfig asset : assets) {
            if (!ids.add(asset.id())) {
                throw new InvalidEstateException("Powtórzone id elementu: " + asset.id());
            }
        }
    }

    public List<AssetConfig> assetsOfType(AssetType type) {
        return assets.stream().filter(a -> a.type() == type).toList();
    }

    public Optional<AssetConfig> findAsset(String id) {
        return assets.stream().filter(a -> a.id().equals(id)).findFirst();
    }
}