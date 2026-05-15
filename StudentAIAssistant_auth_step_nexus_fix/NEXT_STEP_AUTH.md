# Следующий шаг: модуль авторизации

В проект добавлен модуль авторизации в стиле приложения «AI-ассистент студента».

## Что появилось

- WelcomeScreen — приветственный экран.
- LoginScreen — вход.
- RegisterScreen — регистрация.
- AuthViewModel — состояние авторизации.
- FakeAuthRepository — временное хранилище пользователей.
- LoginUseCase и RegisterUseCase — бизнес-логика входа и регистрации.
- AuthValidator — проверка email, пароля, имени и учебного заведения.
- Unit-тесты для AuthValidator, LoginUseCase, RegisterUseCase.

## Тестовые аккаунты

Студент:

```text
email: student@test.ru
password: 123456
```

Преподаватель:

```text
email: teacher@test.ru
password: 123456
```

## Как запустить тесты

В Android Studio:

```text
app/src/test → ПКМ → Run Tests
```

Или через терминал:

```bash
gradlew test
```

## Что делать дальше

Следующий модуль после авторизации — локальная база Room:

- UserEntity
- SubjectEntity
- ScheduleItemEntity
- DeadlineEntity
- NoteEntity
- GradeEntity

После этого можно будет сохранять данные не только в fake-репозитории, а в настоящей локальной базе.
