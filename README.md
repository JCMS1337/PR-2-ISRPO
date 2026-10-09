# todo-app

Консольное приложение для ведения списка задач (учебный проект, ИСРПО).
Написано на Java 23, сборка — Gradle (wrapper), тесты — JUnit 5, распространяется
как исполняемый «толстый» JAR (Shadow).

[![CI / Build & Release](https://github.com/JCMS1337/PR-2-ISRPO/actions/workflows/ci.yml/badge.svg)](https://github.com/JCMS1337/PR-2-ISRPO/actions/workflows/ci.yml)
[![Publish to GitHub Packages](https://github.com/JCMS1337/PR-2-ISRPO/actions/workflows/publish-package.yml/badge.svg)](https://github.com/JCMS1337/PR-2-ISRPO/actions/workflows/publish-package.yml)
[![Nightly Build](https://github.com/JCMS1337/PR-2-ISRPO/actions/workflows/nightly.yml/badge.svg)](https://github.com/JCMS1337/PR-2-ISRPO/actions/workflows/nightly.yml)

## Возможности

- Добавление, удаление и отметка задач как выполненных.
- Поиск по подстроке без учёта регистра.
- Очистка всего списка.
- В списке статус задачи отображается как `[x]` (выполнена) и `[ ]` (активна).

## Стек

- Java 23 (Temurin)
- Gradle 9.6 (через `./gradlew` / `gradlew.bat`)
- JUnit 5 (`junit-bom:5.10.0`)
- Shadow (`com.gradleup.shadow 8.3.11`) — сборка fat-JAR

## Сборка

```bash
./gradlew build
```

В Windows PowerShell:

```powershell
.\gradlew.bat build
```

Команда прогоняет тесты и собирает артефакт в `build/libs/todo-app-<version>.jar`.

## Запуск

```bash
java -jar build/libs/todo-app-<version>.jar
```

Например, для текущей версии:

```bash
java -jar build/libs/todo-app-0.3.0.jar
```

## Команды приложения

| Команда | Описание |
| --- | --- |
| `add <task>` | Добавить задачу (текст обрезается, `null`/пустые игнорируются) |
| `remove <index>` | Удалить задачу по индексу |
| `done <index>` | Отметить задачу выполненной |
| `search <text>` | Найти задачи по подстроке (без учёта регистра) |
| `clear` | Удалить все задачи |
| `list` | Показать список задач |
| `exit` | Выйти |

Пример сессии:

```
> add Buy milk
Added: Buy milk
> add write report
Added: write report
> list
0: [ ] Buy milk
1: [ ] write report
> done 0
Marked task at index 0 as done.
> search milk
[x] Buy milk
> exit
Bye.
```

## CI/CD (GitHub Actions)

В проекте три workflow (`.github/workflows/`):

1. **CI / Build & Release** (`ci.yml`) — запускается при push в `main`.
   Собирает проект, прогоняет тесты (`./gradlew --no-daemon clean build`) и при
   успехе создаёт GitHub Release с тегом `v<version>` и прикрепляет
   `todo-app-<version>.jar`.
2. **Publish to GitHub Packages (Maven)** (`publish-package.yml`) — запускается
   после успешного завершения CI (`workflow_run`) и публикует fat-JAR в
   GitHub Packages (Maven) под координатами `org.example:todo-app`.
3. **Nightly Build** (`nightly.yml`) — по расписанию (`0 2 * * *`) и вручную
   (`workflow_dispatch`); выполняет `./gradlew --no-daemon clean build` без
   релиза и публикации.

## Процесс разработки (GitHub Flow)

- Ветка `main` защищена: прямых коммитов нет, кроме начального.
- Каждая задача — отдельная ветка от `main` (`feat/...`, `docs/...`).
- Изменения вливаются в `main` только через Pull Request и merge
  (`gh pr merge --merge`), после review и зелёного CI.
- Коммиты — осмысленные, короткие, на английском.

## Ссылки

- Репозиторий: https://github.com/JCMS1337/PR-2-ISRPO
- Actions: https://github.com/JCMS1337/PR-2-ISRPO/actions
- Releases: https://github.com/JCMS1337/PR-2-ISRPO/releases
- Packages: https://github.com/JCMS1337/PR-2-ISRPO/packages
