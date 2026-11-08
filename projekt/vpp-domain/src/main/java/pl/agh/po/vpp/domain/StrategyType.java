package pl.agh.po.vpp.domain;

/** Strategia sterowania magazynem i siecią wybierana przez operatora. */
public enum StrategyType {
    /** Nadwyżka do baterii, niedobór z baterii, reszta z/do sieci. */
    RULE_BASED,
    /** Arbitraż cenowy: ładuj, gdy tanio, rozładowuj, gdy drogo. */
    PRICE_BASED
}