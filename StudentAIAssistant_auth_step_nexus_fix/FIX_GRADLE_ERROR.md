# Исправление ошибки debugRuntimeClasspathCopy

Ошибка:

```text
Could not resolve all dependencies for configuration ':app:debugRuntimeClasspathCopy'.
Cannot select root node 'debugRuntimeClasspathCopy' as a variant.
```

Обычно появляется из-за несовместимой версии Gradle, которую Android Studio выбрала для проекта.

## Что сделать в Android Studio

1. Открой проект через `File -> Open` и выбери папку `StudentAIAssistant_fixed_gradle`.
2. Открой настройки Gradle:
   `File -> Settings -> Build, Execution, Deployment -> Build Tools -> Gradle`.
3. В поле `Gradle version / Use Gradle from` выбери Gradle 8.9, если Android Studio предлагает выбор.
4. Нажми `Sync Project with Gradle Files`.
5. Если ошибка осталась, нажми:
   `File -> Invalidate Caches... -> Invalidate and Restart`.
6. Удали папки `.gradle` и `build` в проекте, если они появились после неудачной синхронизации.

## Версии в этом проекте

```text
Android Gradle Plugin: 8.6.1
Kotlin: 2.0.21
Compile SDK: 35
Compose BOM: 2024.09.03
Navigation Compose: 2.8.3
```

