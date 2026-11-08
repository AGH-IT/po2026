package pl.agh.po.vpp.domain;

import java.time.Instant;

/**
 * Obserwator przebiegu symulacji (wzorzec Observer).
 * Silnik (vpp-engine) powiadamia słuchaczy; słuchacze to m.in. dziennik zdarzeń,
 * strumień SSE w vpp-app i pasek postępu w trybie konsolowym.
 * Wszystkie metody są domyślnie puste, więc słuchacz nadpisuje tylko to, co go interesuje.
 */
public interface SimulationListener {

    /** Po każdym policzonym kroku. */
    default void onStep(StepResult step) {
    }

    /** Magazyn właśnie się zapełnił. */
    default void onBatteryFull(Instant time) {
    }

    /** Magazyn właśnie się rozładował. */
    default void onBatteryEmpty(Instant time) {
    }

    /** Eksport do sieci został obcięty do limitu ustawionego przez operatora. */
    default void onExportLimitReached(Instant time) {
    }

    /** Koniec symulacji całej doby. */
    default void onFinished(SimulationResult result) {
    }
}
