package pl.agh.po.vpp.data;

import org.junit.jupiter.api.Test;
import pl.agh.po.vpp.domain.AssetConfig;
import pl.agh.po.vpp.domain.AssetType;
import pl.agh.po.vpp.domain.EstateConfig;
import pl.agh.po.vpp.domain.InvalidEstateException;
import pl.agh.po.vpp.domain.Position;

import java.io.StringReader;
import java.nio.file.Path;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EstateLoaderTest {

    /** Testy uruchamiają się w katalogu modułu vpp-data, plik osiedla leży w projekt/data. */
    private static final Path ESTATE_FILE = Path.of("..", "data", "estate.json");

    private static final String LOCATION = """
            "location": { "latitude": 50.07, "longitude": 20.04, "zone": "Europe/Warsaw" }""";
    private static final String GRID = """
            { "id": "grid", "type": "GRID", "ratedPowerKw": 250 }""";

    private final EstateLoader loader = new EstateLoader();

    private EstateConfig parse(String json) {
        return loader.parse(new StringReader(json), "test.json");
    }

    private static String estateWith(String assets) {
        return "{ \"name\": \"Testowe\", " + LOCATION + ", \"assets\": [ " + assets + " ] }";
    }

    @Test
    void plikOsiedlaZRepoJestPoprawny() {
        EstateConfig estate = loader.load(ESTATE_FILE);

        assertThat(estate.name()).isEqualTo("Osiedle Słoneczne");
        assertThat(estate.location().zone()).isEqualTo(ZoneId.of("Europe/Warsaw"));
        assertThat(estate.assetsOfType(AssetType.APARTMENT_BLOCK)).hasSize(4);
        assertThat(estate.assetsOfType(AssetType.HOUSE)).hasSize(8);
        assertThat(estate.assetsOfType(AssetType.SCHOOL)).hasSize(1);
        assertThat(estate.assetsOfType(AssetType.WIND_TURBINE)).hasSize(1);
        assertThat(estate.assetsOfType(AssetType.BATTERY)).hasSize(1);
        assertThat(estate.assetsOfType(AssetType.GRID)).hasSize(1);
    }

    @Test
    void polaElementuSaWczytywane() {
        EstateConfig estate = loader.load(ESTATE_FILE);

        AssetConfig battery = estate.findAsset("battery").orElseThrow();
        assertThat(battery.capacityKwh()).isEqualTo(200);
        assertThat(battery.ratedPowerKw()).isEqualTo(100);

        AssetConfig block = estate.findAsset("block-1").orElseThrow();
        assertThat(block.pvPeakKw()).isEqualTo(30);
        assertThat(block.dwellings()).isEqualTo(40);
        assertThat(block.position()).isEqualTo(new Position(-60, -40));
    }

    @Test
    void pominietePolaDostajaZeroINazweZId() {
        EstateConfig estate = parse(estateWith(GRID));

        AssetConfig grid = estate.findAsset("grid").orElseThrow();
        assertThat(grid.pvPeakKw()).isZero();
        assertThat(grid.capacityKwh()).isZero();
        assertThat(grid.name()).isEqualTo("grid");
        assertThat(grid.position()).isEqualTo(Position.ORIGIN);
    }

    @Test
    void brakPlikuToBladZeSciezka() {
        assertThatThrownBy(() -> loader.load(Path.of("nie-ma-takiego.json")))
                .isInstanceOf(InvalidEstateException.class)
                .hasMessageContaining("nie-ma-takiego.json");
    }

    @Test
    void zepsutyJsonToBladZNumeremLinii() {
        assertThatThrownBy(() -> parse("{ \"name\": \"Testowe\",\n \"assets\": [ "))
                .isInstanceOf(InvalidEstateException.class)
                .hasMessageContaining("test.json")
                .hasMessageContaining("linia");
    }

    @Test
    void nieznanyTypElementuToBlad() {
        String json = estateWith(GRID + ", { \"id\": \"x\", \"type\": \"REAKTOR\" }");

        assertThatThrownBy(() -> parse(json))
                .isInstanceOf(InvalidEstateException.class)
                .hasMessageContaining("REAKTOR");
    }

    @Test
    void literowkaWNazwiePolaToBlad() {
        String json = estateWith("{ \"id\": \"grid\", \"type\": \"GRID\", \"ratedPowerKW\": 250 }");

        assertThatThrownBy(() -> parse(json))
                .isInstanceOf(InvalidEstateException.class)
                .hasMessageContaining("ratedPowerKW");
    }

    @Test
    void bladWalidacjiRekorduMaCzytelnyKomunikat() {
        String json = estateWith(GRID + ", { \"id\": \"h1\", \"type\": \"HOUSE\", \"pvPeakKw\": -5 }");

        assertThatThrownBy(() -> parse(json))
                .isInstanceOf(InvalidEstateException.class)
                .hasMessageContaining("test.json")
                .hasMessageContaining("'h1' ma ujemny parametr");
    }

    @Test
    void powtorzoneIdToBlad() {
        String json = estateWith(GRID + ", " + GRID);

        assertThatThrownBy(() -> parse(json))
                .isInstanceOf(InvalidEstateException.class)
                .hasMessageContaining("Powtórzone id");
    }

    @Test
    void osiedleBezPrzylaczaToBlad() {
        String json = estateWith("{ \"id\": \"h1\", \"type\": \"HOUSE\", \"pvPeakKw\": 5 }");

        assertThatThrownBy(() -> parse(json))
                .isInstanceOf(InvalidEstateException.class)
                .hasMessageContaining("GRID");
    }

    @Test
    void dwaMagazynyToBlad() {
        String battery = "{ \"id\": \"%s\", \"type\": \"BATTERY\", \"ratedPowerKw\": 100, \"capacityKwh\": 200 }";
        String json = estateWith(GRID + ", " + battery.formatted("b1") + ", " + battery.formatted("b2"));

        assertThatThrownBy(() -> parse(json))
                .isInstanceOf(InvalidEstateException.class)
                .hasMessageContaining("BATTERY");
    }

    @Test
    void pustyPlikToBlad() {
        assertThatThrownBy(() -> parse(""))
                .isInstanceOf(InvalidEstateException.class);
    }
}
