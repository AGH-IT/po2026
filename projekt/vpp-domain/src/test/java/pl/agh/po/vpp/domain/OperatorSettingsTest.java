package pl.agh.po.vpp.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OperatorSettingsTest {

    @Test
    void brakujacePolaDostajaWartosciDomyslne() {
        OperatorSettings settings = new OperatorSettings(null, null, null, null, 7);

        assertThat(settings.strategy()).isEqualTo(StrategyType.RULE_BASED);
        assertThat(settings.batteryMode()).isEqualTo(BatteryMode.AUTO);
        assertThat(settings.outages()).isEmpty();
        assertThat(settings.hasExportLimit()).isFalse();
    }

    @Test
    void ujemnyLimitEksportuToBlad() {
        assertThatThrownBy(() -> new OperatorSettings(null, null, List.of(), -1.0, 7))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void withStrategyZmieniaTylkoStrategie() {
        OperatorSettings base = new OperatorSettings(
                StrategyType.RULE_BASED, BatteryMode.HOLD_FOR_EVENING, List.of(), 20.0, 7);

        OperatorSettings other = base.withStrategy(StrategyType.PRICE_BASED);

        assertThat(other.strategy()).isEqualTo(StrategyType.PRICE_BASED);
        assertThat(other.batteryMode()).isEqualTo(BatteryMode.HOLD_FOR_EVENING);
        assertThat(other.exportLimitKw()).isEqualTo(20.0);
        assertThat(base.strategy()).isEqualTo(StrategyType.RULE_BASED);
    }

    @Test
    void awariaJestAktywnaWswoimPrzedziale() {
        AssetOutage outage = new AssetOutage("w1", LocalTime.of(14, 0), LocalTime.of(18, 0));

        assertThat(outage.isActiveAt(LocalTime.of(13, 59))).isFalse();
        assertThat(outage.isActiveAt(LocalTime.of(14, 0))).isTrue();
        assertThat(outage.isActiveAt(LocalTime.of(17, 45))).isTrue();
        assertThat(outage.isActiveAt(LocalTime.of(18, 0))).isFalse();
    }

    @Test
    void awariaCalodobowa() {
        AssetOutage outage = AssetOutage.allDay("w1");

        assertThat(outage.isActiveAt(LocalTime.MIDNIGHT)).isTrue();
        assertThat(outage.isActiveAt(LocalTime.of(23, 59))).isTrue();
    }
}
