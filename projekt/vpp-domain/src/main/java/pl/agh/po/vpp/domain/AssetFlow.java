package pl.agh.po.vpp.domain;

/**
 * Moc jednego elementu osiedla w jednym kroku, z punktu widzenia osiedlowej sieci.
 * Obie wartości są nieujemne:
 * <ul>
 *   <li>budynek: production = panele na dachu, consumption = zużycie mieszkań</li>
 *   <li>wiatrak: production = moc wiatraka</li>
 *   <li>bateria: production = rozładowanie, consumption = ładowanie</li>
 *   <li>sieć: production = import, consumption = eksport</li>
 * </ul>
 * W każdym kroku suma production wszystkich elementów = suma consumption.
 */
public record AssetFlow(String assetId, AssetType type, double productionKw, double consumptionKw) {

    public AssetFlow {
        if (assetId == null || type == null) {
            throw new IllegalArgumentException("AssetFlow wymaga id i typu elementu");
        }
        if (productionKw < 0 || consumptionKw < 0) {
            throw new IllegalArgumentException(
                    "Moce w AssetFlow muszą być nieujemne: " + assetId + " " + productionKw + "/" + consumptionKw);
        }
    }

    /** Ile element netto oddaje do osiedla [kW]; ujemne = netto pobiera. */
    public double netKw() {
        return productionKw - consumptionKw;
    }
}
