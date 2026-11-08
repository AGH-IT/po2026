package pl.agh.po.vpp.domain;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TimelineTest {

    private static final ZoneId WARSAW = ZoneId.of("Europe/Warsaw");

    @Test
    void zwyklaDobaMa96KrokowPo15Minut() {
        Timeline timeline = Timeline.of(LocalDate.of(2026, 11, 5), WARSAW);

        assertThat(timeline.size()).isEqualTo(96);
        assertThat(timeline.steps()).hasSize(96);
        assertThat(timeline.stepHours()).isEqualTo(0.25);
        assertThat(timeline.steps().getFirst()).isEqualTo(timeline.start());
        assertThat(timeline.steps().getLast().plus(timeline.step())).isEqualTo(timeline.end());
    }

    @Test
    void zmianaCzasuNaLetniDaje92Kroki() {
        Timeline timeline = Timeline.of(LocalDate.of(2026, 3, 29), WARSAW);

        assertThat(timeline.size()).isEqualTo(92);
        assertThat(timeline.steps()).hasSize(92);
    }

    @Test
    void zmianaCzasuNaZimowyDaje100Krokow() {
        Timeline timeline = Timeline.of(LocalDate.of(2026, 10, 25), WARSAW);

        assertThat(timeline.size()).isEqualTo(100);
        assertThat(timeline.steps()).hasSize(100);
    }

    @Test
    void krokMusiDzielicGodzine() {
        assertThatThrownBy(() -> new Timeline(LocalDate.of(2026, 11, 5), Duration.ofMinutes(7), WARSAW))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Timeline(LocalDate.of(2026, 11, 5), Duration.ZERO, WARSAW))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
