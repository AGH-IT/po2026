#!/usr/bin/env bash
# Sprawdza składnię (domyślnie) albo renderuje diagramy PlantUML z tego katalogu.
#
# Użycie:
#   ./render.sh          sprawdza składnię wszystkich plików .puml (kod wyjścia != 0 przy błędzie)
#   ./render.sh check    to samo
#   ./render.sh png      renderuje PNG do podkatalogu render/
#
# Wymaga: Javy oraz polecenia `plantuml` w PATH albo zmiennej PLANTUML_JAR (ścieżka do pliku .jar).
set -euo pipefail
cd "$(dirname "$0")"

tryb="${1:-check}"

if [ -n "${PLANTUML_JAR:-}" ]; then
  uruchom=(java -jar "$PLANTUML_JAR")
elif command -v plantuml >/dev/null 2>&1; then
  uruchom=(plantuml)
else
  echo "Brak PlantUML: zainstaluj polecenie 'plantuml' albo ustaw PLANTUML_JAR." >&2
  exit 2
fi

shopt -s nullglob
pliki=(./*.puml)
if [ "${#pliki[@]}" -eq 0 ]; then
  echo "Brak plików .puml w $(pwd)." >&2
  exit 2
fi

case "$tryb" in
  check)
    "${uruchom[@]}" -charset UTF-8 -checkonly "${pliki[@]}"
    echo "OK: składnia ${#pliki[@]} plików .puml jest poprawna."
    ;;
  png)
    mkdir -p render
    "${uruchom[@]}" -charset UTF-8 -tpng -o render "${pliki[@]}"
    echo "OK: wyrenderowano ${#pliki[@]} diagramów do $(pwd)/render/."
    ;;
  *)
    echo "Nieznany tryb: $tryb (dostępne: check, png)" >&2
    exit 1
    ;;
esac
