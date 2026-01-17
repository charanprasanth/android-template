# Android Starter Template (Jetpack Compose + Clean Architecture)

A production-ready Android starter template built with modern Android best practices.
This template is designed to be reused across projects and scale from small apps to large, multi-feature products.

## What This Template Solves

* clean boundaries
* predictable architecture
* reusable feature pattern
* real-world auth setup
* offline-ready foundation

## Tech Stack

### Core

* **Kotlin**
* **Jetpack Compose**
* **MVVM** (State + Event + Effect)
* **Feature-based Clean Architecture**
* **Hilt (Dependency Injection)**

### Networking

* Retrofit
* OkHttp
* JWT Authentication
* Refresh Token Interceptor

### Persistence

* Room (local database)
* DataStore (preferences & tokens)

### Infrastructure

* Error handling layer
* Result wrapper
* Connectivity observer
* Dispatcher provider
* Logging with Timber
* Build variants (dev / release)

## Project Structure

```
com.xlorit.template
│
├── core/                # Shared infrastructure (used by all features)
│   ├── network/         # Retrofit, interceptors, API setup
│   ├── datastore/       # DataStore for tokens & preferences
│   ├── local/           # Room database, DAOs, entities
│   ├── error/           # AppError + ErrorMapper
│   ├── result/          # Result wrapper
│   ├── connectivity/    # Network observer
│   ├── dispatcher/      # Coroutine dispatchers
│   └── utils/           # Logger, helpers
│
├── feature/             # Feature-based clean architecture
│   ├── auth/
│   │   ├── data/
│   │   ├── domain/
│   │   └── presentation/
│   │
│   └── home/
│       ├── data/
│       ├── domain/
│       └── presentation/
│
├── navigation/           # Compose navigation graph
├── di/                   # Hilt modules
├── App.kt                # Application class
└── MainActivity.kt       # Entry point
```

## Architecture Overview

### Feature-based Clean Architecture

Each feature owns its own:

* data layer (API, DB, repository impl)
* domain layer (models, usecases, repository interface)
* presentation layer (UI + ViewModel)

```
UI → ViewModel → UseCase → Repository → (API / DB)
```

### Why feature-based?

* easy to delete or move features
* scalable for large apps
* ready for dynamic features / modules
* clean separation of concerns

## Authentication Flow (JWT + Refresh)

1. User logs in
2. Access + refresh tokens saved in DataStore
3. AuthInterceptor adds JWT to every request
4. If API returns 401:

    * refresh token is used
    * new access token is saved
    * original request is retried

All of this happens automatically at the network layer.

## Error Handling

* No raw exceptions reach UI
* All errors mapped to `AppError`

```kotlin
sealed class AppError {
    object Network
    object Unauthorized
    data class Api(val message: String)
    object Unknown
}
```

ViewModels only deal with **Result**, never try/catch.

## Result Wrapper

Used for all business operations:

```kotlin
Result.Success(data)
Result.Error(error)
```

Loading is handled at UI level for one-shot actions.

## Build Variants

Defined in Gradle:

```gradle
debug   → dev API
release → prod API
```

No hardcoded URLs anywhere in code.

## Localization

* Uses standard `strings.xml`
* Can be extended with DataStore-based language selection

## Connectivity

Connectivity is exposed as a Flow:

```kotlin
ConnectivityObserver.isConnected
```

UI can react to offline / online states.

## How to Add a New Feature

1. Create folder under `feature/`
2. Add `data / domain / presentation`
3. Define repository interface in domain
4. Implement repository in data
5. Create usecases
6. Create ViewModel
7. Create screen
8. Add route to `AppNavHost`

That’s it.

## How to Use This Template

1. Clone repository
2. Rename package
3. Update app name
4. Update base URLs
5. Start building features

If you reuse this template, you’ll never want to start from scratch again.
