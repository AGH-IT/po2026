package pl.agh.po.vpp.domain;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EstateConfigTest {

    private static final Location KRAKOW = new Location(50.07, 20.04, ZoneId.of("Europe/Warsaw"));

    private static AssetConfig block(String id) {
        return new AssetConfig(id, AssetType.APARTMENT_BLOCK, "Blok " + id, 30, 0, 0, 40, null);
    }

    @Test
    void powtorzoneIdToBlad() {
        List<AssetConfig> assets = List.of(block("b1"), block("b1"));

        assertThatThrownBy(() -> new EstateConfig("Słoneczne", KRAKOW, assets))
                .isInstanceOf(InvalidEstateException.class)
                .hasMessageContaining("b1");
    }

    @Test
    void osiedleBezElementowToBlad() {
        assertThatThrownBy(() -> new EstateConfig("Słoneczne", KRAKOW, List.of()))
                .isInstanceOf(InvalidEstateException.class);
    }

    @Test
    void listaElementowJestKopiaNiemutowalna() {
        List<AssetConfig> source = new ArrayList<>(List.of(block("b1")));
        EstateConfig estate = new EstateConfig("Słoneczne", KRAKOW, source);

        source.add(block("b2"));

        assertThat(estate.assets()).hasSize(1);
        assertThatThrownBy(() -> estate.assets().add(block("b3")))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void wyszukiwanieElementow() {
        AssetConfig wind = new AssetConfig("w1", AssetType.WIND_TURBINE, "Wiatrak", 0, 50, 0, 0, null);
        EstateConfig estate = new EstateConfig("Słoneczne", KRAKOW, List.of(block("b1"), block("b2"), wind));

        assertThat(estate.assetsOfType(AssetType.APARTMENT_BLOCK)).hasSize(2);
        assertThat(estate.findAsset("w1")).contains(wind);
        assertThat(estate.findAsset("brak")).isEmpty();
    }
}
