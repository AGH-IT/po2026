package pl.agh.po.vpp.domain;

/**
 * Decyzja strategii w jednym kroku: ile mocy ma iść do baterii, ile z/do sieci.
 * Znaki jak w StepResult: batteryKw + ładowanie, gridKw + import.
 * To jest życzenie strategii; bateria może je przyciąć do swoich limitów.
 */
public record Dispatch(double batteryKw, double gridKw) {
}
