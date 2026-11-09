package pl.agh.po.vpp.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import pl.agh.po.vpp.domain.AssetType;
import pl.agh.po.vpp.domain.EstateConfig;
import pl.agh.po.vpp.domain.InvalidEstateException;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Wczytuje opis osiedla z pliku JSON (estate.json) do rekordu {@link EstateConfig}.
 * Każdy problem - brak pliku, zły JSON, błędne wartości - kończy się
 * {@link InvalidEstateException} z komunikatem, który mówi, co i gdzie poprawić.
 */
public class EstateLoader {

    private final ObjectMapper mapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    /** Wczytuje osiedle z pliku. */
    public EstateConfig load(Path file) {
        try (Reader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            return parse(reader, file.toString());
        } catch (IOException e) {
            throw new InvalidEstateException("Nie można odczytać pliku osiedla: " + file, e);
        }
    }

    /**
     * Wczytuje osiedle z dowolnego źródła tekstu.
     *
     * @param source nazwa źródła do komunikatów o błędach (np. ścieżka pliku)
     */
    public EstateConfig parse(Reader json, String source) {
        EstateConfig estate;
        try {
            estate = mapper.readValue(json, EstateConfig.class);
        } catch (JsonProcessingException e) {
            throw describe(e, source);
        } catch (IOException e) {
            throw new InvalidEstateException("Nie można odczytać opisu osiedla: " + source, e);
        }
        if (estate == null) {
            throw new InvalidEstateException(source + ": plik jest pusty");
        }
        validate(estate, source);
        return estate;
    }

    /** Reguły dotyczące całego osiedla, których pojedynczy rekord nie sprawdzi. */
    private void validate(EstateConfig estate, String source) {
        int grids = estate.assetsOfType(AssetType.GRID).size();
        if (grids != 1) {
            throw new InvalidEstateException(
                    source + ": osiedle musi mieć dokładnie jedno przyłącze do sieci (GRID), a ma " + grids);
        }
        int batteries = estate.assetsOfType(AssetType.BATTERY).size();
        if (batteries > 1) {
            throw new InvalidEstateException(
                    source + ": osiedle może mieć najwyżej jeden magazyn energii (BATTERY), a ma " + batteries);
        }
    }

    /**
     * Zamienia wyjątek Jacksona na nasz. Jeśli błąd zgłosił konstruktor rekordu
     * (np. ujemna moc), wyciągamy jego komunikat zamiast technicznego opisu Jacksona.
     */
    private InvalidEstateException describe(JsonProcessingException e, String source) {
        for (Throwable cause = e.getCause(); cause != null; cause = cause.getCause()) {
            if (cause instanceof InvalidEstateException invalid) {
                return new InvalidEstateException(source + ": " + invalid.getMessage(), e);
            }
        }
        String where = e.getLocation() == null ? "" : " (linia " + e.getLocation().getLineNr() + ")";
        return new InvalidEstateException(source + where + ": błędny format - " + e.getOriginalMessage(), e);
    }
}
