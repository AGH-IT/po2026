package pl.agh.po.vpp.domain;

/** Ręczne sterowanie magazynem z panelu operatora. */
public enum BatteryMode {
    /** Decyduje wybrana strategia. */
    AUTO,
    /** Wymuszone ładowanie z pełną mocą (także z sieci). */
    CHARGE_NOW,
    /** Nie rozładowuj przed wieczornym szczytem. */
    HOLD_FOR_EVENING
}