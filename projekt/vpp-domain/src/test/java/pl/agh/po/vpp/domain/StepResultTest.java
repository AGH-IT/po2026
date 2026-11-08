package pl.agh.po.vpp.domain;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

class StepResultTest {

    private static final Instant T = Instant.parse("2026-11-05T11:00:00Z");
    private static final Conditions CONDITIONS = new Conditions(T, new Weather(T, 400, 6, 8, 30), 0.6);

    /** Przykład konwencji znaków: panele 50 kW, budynki 30 kW, bateria ładuje 15 kW, eksport 5 kW. */
    private static StepResult sunnyStep() {
        List<AssetFlow> flows = List.of(
                new AssetFlow("b1", AssetType.APARTMENT_BLOCK, 50, 30),
                new AssetFlow("bat", AssetType.BATTERY, 0, 15),
                new AssetFlow("grid", AssetType.GRID, 0, 5));
        return new StepResult(T, Duration.ofMinutes(15), CONDITIONS, flows, 50, 30, 15, -5, 100);
    }

    @Test
    void bilansSieZgadza() {
        assertThat(sunnyStep().balanceErrorKw()).isCloseTo(0, within(1e-9));
    }

    @Test
    void sumaProdukcjiElementowRownaSumieZuzycia() {
        StepResult step = sunnyStep();

        double produced = step.flows().stream().mapToDouble(AssetFlow::productionKw).sum();
        double consumed = step.flows().stream().mapToDouble(AssetFlow::consumptionKw).sum();

        assertThat(produced).isCloseTo(consumed, within(1e-9));
    }

    @Test
    void ujemnyGridToEksport() {
        StepResult step = sunnyStep();

        assertThat(step.exportKw()).isEqualTo(5);
        assertThat(step.importKw()).isZero();
    }

    @Test
    void energiaWKrokuTo_mocRazyCzas() {
        assertThat(sunnyStep().energyKwh(20)).isEqualTo(5.0);
    }

    @Test
    void mocElementuPoId() {
        assertThat(sunnyStep().flowOf("bat")).hasValueSatisfying(f -> assertThat(f.netKw()).isEqualTo(-15));
        assertThat(sunnyStep().flowOf("brak")).isEmpty();
    }

    @Test
    void ujemnaMocWAssetFlowToBlad() {
        assertThatThrownBy(() -> new AssetFlow("b1", AssetType.HOUSE, -1, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void stanBaterii() {
        BatteryState half = new BatteryState(100, 200);

        assertThat(half.socFraction()).isEqualTo(0.5);
        assertThat(half.isFull()).isFalse();
        assertThat(new BatteryState(200, 200).isFull()).isTrue();
        assertThat(new BatteryState(0, 200).isEmpty()).isTrue();
        assertThatThrownBy(() -> new BatteryState(201, 200)).isInstanceOf(IllegalArgumentException.class);
    }
}
