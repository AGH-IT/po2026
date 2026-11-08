package pl.agh.po.vpp.domain;

/**
 * Stan magazynu energii: ile jest w nim energii i ile się zmieści.
 *
 * @param socKwh      stan naładowania [kWh] (SoC, state of charge)
 * @param capacityKwh pojemność [kWh]; 0 = osiedle bez magazynu
 */
public record BatteryState(double socKwh, double capacityKwh) {

    private static final double EPSILON_KWH = 1e-9;

    public BatteryState {
        if (capacityKwh < 0) {
            throw new IllegalArgumentException("Pojemność nie może być ujemna: " + capacityKwh);
        }
        if (socKwh < -EPSILON_KWH || socKwh > capacityKwh + EPSILON_KWH) {
            throw new IllegalArgumentException("SoC " + socKwh + " poza zakresem [0, " + capacityKwh + "]");
        }
    }

    /** Naładowanie jako ułamek 0–1 (do paska na scenie i wykresu w panelu). */
    public double socFraction() {
        return capacityKwh == 0 ? 0 : socKwh / capacityKwh;
    }

    public boolean isFull() {
        return capacityKwh - socKwh <= EPSILON_KWH;
    }

    public boolean isEmpty() {
        return socKwh <= EPSILON_KWH;
    }
}
