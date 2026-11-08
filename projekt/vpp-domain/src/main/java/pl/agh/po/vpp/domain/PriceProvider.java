package pl.agh.po.vpp.domain;

import java.util.List;

/**
 * Źródło cen energii z sieci. Implementacje w vpp-data:
 * SyntheticPriceProvider (profil dobowy z seeda), CsvPriceProvider (ceny z TGE/ENTSO-E).
 */
public interface PriceProvider {

    /**
     * Cena energii dla każdego kroku doby [PLN/kWh].
     *
     * @return lista o długości {@code timeline.size()}; element i odpowiada {@code timeline.steps().get(i)}
     */
    List<Double> pricesFor(Timeline timeline);
}
