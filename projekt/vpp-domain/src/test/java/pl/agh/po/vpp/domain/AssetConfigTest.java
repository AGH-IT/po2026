package pl.agh.po.vpp.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AssetConfigTest {

    @Test
    void ujemnaMocToBlad() {
        assertThatThrownBy(() -> new AssetConfig("h1", AssetType.HOUSE, "Dom", -5, 0, 0, 1, null))
                .isInstanceOf(InvalidEstateException.class)
                .hasMessageContaining("h1");
    }

    @Test
    void bateriaMusiMiecPojemnoscIMoc() {
        assertThatThrownBy(() -> new AssetConfig("bat", AssetType.BATTERY, "Magazyn", 0, 100, 0, 0, null))
                .isInstanceOf(InvalidEstateException.class);
    }

    @Test
    void wiatrakMusiMiecMocZnamionowa() {
        assertThatThrownBy(() -> new AssetConfig("w1", AssetType.WIND_TURBINE, "Wiatrak", 0, 0, 0, 0, null))
                .isInstanceOf(InvalidEstateException.class);
    }

    @Test
    void brakNazwyIPozycjiDostajeWartosciDomyslne() {
        AssetConfig asset = new AssetConfig("h1", AssetType.HOUSE, null, 5, 0, 0, 1, null);

        assertThat(asset.name()).isEqualTo("h1");
        assertThat(asset.position()).isEqualTo(Position.ORIGIN);
    }
}
