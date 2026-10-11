# Offline-First Reading App

An Android reading app built for a startup client with Kotlin and Jetpack Compose. Chapters are fetched from a REST API and cached locally with Room, so anything you've downloaded can be read without an internet connection.

> **Status:** in active development (started March 2026).

## Features

- **Chapter list** fetched from the API on first launch, then cached so it loads instantly and works offline
- **Bilingual reading screen** showing Arabic text alongside an English translation, verse by verse
- **Offline caching:** download a single chapter, or every chapter at once with **Download all**
- **Live download indicators:** the list updates as chapters are saved, driven by a Room `Flow`
- **Cache-first loading:** a chapter is read from Room if it's been downloaded, and only requested from the API if it hasn't
- **Adjustable text size** (Small → Extra Large) for accessibility, with separate sizes for Arabic and English text, saved with Jetpack DataStore so it persists between sessions
- **Navigation drawer** with Navigation Compose

## Architecture

Clean Architecture with MVVM, organised by feature:

```
feature_reading/
├── presentation/   Compose screens, ViewModels, UI state, navigation drawer
├── domain/         Use cases, models, repository interfaces
└── data/           Retrofit API, DTOs, Room database and DAO, repository implementations
```

- **Use cases** keep business logic out of the ViewModels: `GetSurahs`, `GetSurah`, `CacheSurah`, `CacheSurahs`, and text-size use cases (`GetTextSize`, `IncreaseTextSize`, `DecreaseTextSize`)
- **Repository pattern:** the domain layer depends on interfaces; the implementations decide whether data comes from Room or the network
- **Dependency injection** with Hilt

## Data layer

- **Room** with two linked tables: chapters, and the verses that belong to them
- The verses table uses a **composite primary key** (`surahNo`, `ayahNo`) together with `@Upsert`, so downloading a chapter again updates the existing rows instead of creating duplicates
- **Retrofit** for the REST API, with DTOs mapped to domain models and Room entities
- **Jetpack DataStore** for the user's text-size preference

## Tech stack

Kotlin · Jetpack Compose · Material 3 · Coroutines & Flow · Hilt · Room · Retrofit · DataStore · Navigation Compose

Min SDK 25 · Target SDK 36

## Roadmap

- [ ] Contributors screen (navigation route in place, UI in progress)
- [ ] Unit tests for use cases and ViewModels (MockK and coroutines-test are already set up)
- [ ] Repository tests against an in-memory Room database
- [ ] Error and retry states for failed downloads

## Running the project

Requirements: Android Studio (recent version), JDK 17, an emulator or device running Android 7.1 (API 25) or later

```bash
git clone https://github.com/omarefaruk/api-caching-android.git
```

Open the project in Android Studio, let Gradle sync, and run the `app` configuration.

## Contact

Omare Faruk · Android Developer · [GitHub](https://github.com/omarefaruk) · [LinkedIn](https://www.linkedin.com/in/omarefaruk/)
