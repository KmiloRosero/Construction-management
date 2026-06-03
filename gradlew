#!/usr/bin/env sh
# Gradle wrapper script (generated)
DIRNAME=$(dirname "$0")
APP_BASE_NAME=$(basename "$0")
APP_HOME="$(cd "$DIRNAME" && pwd -P)"
CLASSPATH="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"
if [ ! -f "$CLASSPATH" ]; then
  echo "Missing $CLASSPATH. Run 'gradle wrapper' or download the jar." >&2
  exit 1
fi
exec java -jar "$CLASSPATH" "$@"
