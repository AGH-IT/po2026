package pl.agh.po.vpp.domain;

/**
 * Strategia sterowania (wzorzec Strategy): decyduje, ile energii idzie do baterii, a ile do sieci.
 * Implementacje w vpp-engine: RuleBasedStrategy, PriceBasedStrategy; dekoratory ManualOverride, ExportLimit.
 * Propozycja dla osoby A – sygnatura do jej decyzji.
 */
public interface DispatchStrategy {

    /**
     * @param netKw      produkcja minus zużycie w tym kroku [kW]; + nadwyżka, − niedobór
     * @param battery    aktualny stan magazynu
     * @param conditions pogoda i cena w tym kroku
     * @return życzenie strategii; musi spełniać netKw + gridKw = batteryKw
     */
    Dispatch decide(double netKw, BatteryState battery, Conditions conditions);
}
