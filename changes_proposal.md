# Android Challenge Modernization Plan

## Context

This is a take-home interview project for Android candidates. It was last updated several years ago and is now meaningfully out of date in its build tooling and dependencies. The goal is to bring it current so candidates are working against a modern Android build environment — one that mirrors the production app's configuration as closely as possible.

All proposals have been reviewed and approved. Versions are sourced from the production app's `libs.versions.toml` where available.

---

## Confirmed Changes

### Build Tooling

| Component | Current | Target | Source |
|---|---|---|---|
| Gradle Wrapper | 8.4 | **8.13.2** | Requested |
| Android Gradle Plugin | 8.3.0 | **8.13.2** | Production app |
| Kotlin | 1.9.23 | **2.3.0** | Production app |
| Java compatibility | 11 | **17** | Production app |
| Build file format | Groovy DSL (`.gradle`) | **Kotlin DSL (`.gradle.kts`)** | Requested |
| Version catalog | None | **`gradle/libs.versions.toml`** | Requested |
| compileSdk / targetSdk | 34 | **35** | Approved |

### Dependency Versions (production app TOML as source of truth)

| Library | Current | Target |
|---|---|---|
| `core-ktx` | 1.12.0 | **1.17.0** |
| `appcompat` | 1.6.1 | **1.7.1** |
| `material` | 1.11.0 | **1.12.0** |
| `constraintlayout` | 2.1.4 | **2.2.1** |
| `navigation` | 2.7.7 | **2.9.6** |
| `coroutines` | 1.8.0 | **1.10.2** |
| `retrofit` | 2.9.0 | **2.11.0** |
| `okhttp` | 4.10.0 / 4.12.0 (mixed) | **4.12.0** (unified) |
| `gson` | 2.10.1 | **2.13.2** |
| `viewpager2` | 1.0.0 | **1.1.0** |
| Compose BOM | (pinned per-library at 1.6.5) | **2026.01.00** |
| `junit` | 4.13.2 | 4.13.2 (unchanged) |
| `junit-ext` | 1.1.5 | **1.2.1** |
| `espresso` | 3.5.1 | **3.6.1** |
| `robolectric` | 4.8.1 | **4.12.2** |
| `mockito` | 3.10.0 | **5.14.2** |
| `arch-core-testing` | `android.arch.core` 1.1.1 | **`androidx.arch.core` 2.2.0** |
| `mockwebserver` | 4.9.3 | **4.12.0** |
| `coroutines-test` | (missing) | **1.10.2** (added) |

### Cleanup Fixes

| Issue | Fix |
|---|---|
| `jcenter()` in repositories | Remove — JCenter has been shut down since 2021 |
| `core-ktx` and `appcompat` declared twice | Remove duplicate declarations |
| `junit-ktx` scoped as `implementation` | Move to `androidTestImplementation` — bug that ships test code into production APK |
| `transport-runtime` declared directly | Remove — transitive Firebase dep, not used in source |
| `okhttp-urlconnection` declared | Remove — legacy bridge adapter, not used with Retrofit |
| OkHttp version mismatch (4.10/4.12 mixed) | Unified to 4.12.0 via single TOML entry |
| `android.arch.core` namespace | Migrate to `androidx.arch.core` |
| `coroutines-test` missing from test deps | Add `testImplementation` — required for `runTest {}` in coroutine unit tests |

---

## Files Changed

| File | Action |
|---|---|
| `gradle/libs.versions.toml` | **CREATE** |
| `settings.gradle` | **DELETE** → replaced by `settings.gradle.kts` |
| `settings.gradle.kts` | **CREATE** |
| `build.gradle` | **DELETE** → replaced by `build.gradle.kts` |
| `build.gradle.kts` | **CREATE** |
| `app/build.gradle` | **DELETE** → replaced by `app/build.gradle.kts` |
| `app/build.gradle.kts` | **CREATE** |
| `gradle/wrapper/gradle-wrapper.properties` | **UPDATE** |
| `gradle.properties` | **UPDATE** — remove stale flags |

---

## Implementation Steps

### Step 1 — Create `gradle/libs.versions.toml`

Centralized version catalog with all versions, library aliases, and plugin aliases. Compose libraries are declared without version (resolved via BOM). All aliases will use the `libs.*` accessor pattern in build files.

### Step 2 — Update `gradle/wrapper/gradle-wrapper.properties`

```
distributionUrl=https\://services.gradle.org/distributions/gradle-8.13.2-bin.zip
```

### Step 3 — Create `settings.gradle.kts`

- Kotlin DSL syntax
- `pluginManagement {}` block for plugin resolution (replaces `buildscript {}`)
- `dependencyResolutionManagement {}` block with only `google()` and `mavenCentral()` — `jcenter()` removed
- Version catalog auto-enabled (built-in for Gradle 8+, no feature preview flag needed)

### Step 4 — Create `build.gradle.kts` (project-level)

- `buildscript {}` block removed entirely
- Plugins declared via `plugins {}` using version catalog aliases with `apply false`
- Kotlin DSL syntax throughout

### Step 5 — Create `app/build.gradle.kts` (module-level)

- Kotlin DSL syntax throughout
- All dependencies use `libs.*` version catalog references — no hardcoded version strings
- Plugin IDs updated for Kotlin 2.x:
  - `com.android.application` (unchanged)
  - `kotlin-android` → `org.jetbrains.kotlin.android`
  - Add `org.jetbrains.kotlin.plugin.compose` (Kotlin 2.x Compose compiler plugin)
- `composeOptions { kotlinCompilerExtensionVersion }` block removed — no longer needed in Kotlin 2.x
- Compose dependencies use BOM platform import; no version on individual Compose artifacts
- `compileSdk` / `targetSdk` updated to 35
- Java source/target compatibility updated to `JavaVersion.VERSION_17`
- `kotlinOptions { jvmTarget = "17" }`
- All cleanup fixes applied (duplicates removed, scopes corrected, removed deps dropped)

### Step 6 — Update `gradle.properties`

Remove stale / redundant flags that are now defaults or no longer needed.

---

## Not Changing

- **README** — left as-is
- **Source code** — no architecture changes, no DI framework, no StateFlow migration
- **ProGuard / R8** — untouched
- **AndroidManifest** — untouched
- **Test coverage** — existing placeholder tests kept as-is

---

## Verification

1. `./gradlew assembleDebug` — clean build, no deprecated API warnings
2. `./gradlew testDebugUnitTest` — existing tests pass
3. No `jcenter()` in any build file
4. No hardcoded version strings in any `.gradle.kts` file — all via `libs.*`
5. Android Studio sync succeeds with no errors or yellow dependency highlights
