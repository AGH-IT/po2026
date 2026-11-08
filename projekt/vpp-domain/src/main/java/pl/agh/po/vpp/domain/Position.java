package pl.agh.po.vpp.domain;

/** Położenie elementu na makiecie 3D, w metrach. Oś x na wschód, z na południe. */
public record Position(double x, double z) {

    public static final Position ORIGIN = new Position(0, 0);
}