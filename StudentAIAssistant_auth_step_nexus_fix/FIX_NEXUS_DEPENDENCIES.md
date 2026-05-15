# Фикс ошибки с lifecycle-viewmodel:2.8.6

Ошибка возникала из-за того, что Android Studio искала зависимости только во внутреннем Nexus:

```text
http://nexus.ngknn.local:8081/repository/maven-public/
```

В Nexus не оказалось версии:

```text
androidx.lifecycle:lifecycle-viewmodel:2.8.6
```

В проекте версии `lifecycle-viewmodel-compose` и `lifecycle-viewmodel-ktx` понижены с `2.8.6` до `2.8.3`, потому что остальные зависимости проекта уже используют ветку `2.8.3`.

Изменён файл:

```text
app/build.gradle.kts
```

Было:

```kotlin
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")
implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")
```

Стало:

```kotlin
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.3")
implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.3")
```

После открытия проекта в Android Studio:

1. Нажать `File -> Sync Project with Gradle Files`.
2. Если ошибка осталась, удалить папки `.gradle`, `build`, `app/build`.
3. Выполнить `File -> Invalidate Caches... -> Invalidate and Restart`.
