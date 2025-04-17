## Overview

This Android project is inspired by Philipp Lackner’s **Best Practice Guide to Android Architecture**, a **4.5 hour** video course currently available **for free**. Philipp has over **13 years** of industry experience and has helped **10,000+** students master practical Android skills. While the course teaches raw networking strategies and manual dependency wiring to illustrate core principles, this implementation layer uses **Retrofit** for HTTP calls and **Dagger Hilt** for dependency injection.

---

## What You’ll Learn (Course Content)

- **Maintainable Class Structure** – Always know which classes to create for a testable, understandable codebase.
- **Responsive UI** – Build a **100% responsive** app that adapts to any device size.
- **Avoid Planning Pitfalls** – Sidestep common architecture mistakes before they derail your project.
- **Clean Code Principles** – Master modern clean‑code guidelines to stay relevant in today’s Android industry.
- **Modern App Architecture** – Structure your code with current best‑practice patterns for scalability and testability.
- **Material 3 Theming** – Implement light/dark and **dynamic colors** that adapt to the user’s wallpaper and system theme.
- **Efficient Networking** – Learn to organize your data layer and handle REST‑API errors according to architectural guidelines.
- **Custom Line Chart** – Dive into Jetpack Compose’s Canvas API by building a fully custom line‑chart component from scratch.

---

## Architecture Overview

We follow **Clean Architecture** principles, separating concerns into distinct layers:

1. **Domain** – Business models, interfaces, and use‑cases.
2. **Data** – Repository implementations, remote and local data sources.
3. **Presentation** – Jetpack Compose UI, ViewModels, and unidirectional data flow.
4. **DI** – Dagger Hilt modules for wiring dependencies.

This aligns with Android’s recommended layered approach to keep your UI independent of data and business logic.

---

## Tech Stack & Enhancements

Although the course demonstrates manual wiring for clarity, this project integrates production‑ready libraries:

- **Retrofit** + **Kotlin Coroutines** & **Flow** for networking.
- **Dagger Hilt** for dependency injection across modules.
- **Room** for local persistence.
- **Jetpack Compose** for all UI layers.
- **Material 3** components & dynamic color support.

---

## Getting Started

### Prerequisites

- **JDK 11+**
- **Android Studio Arctic Fox** (2020.3.1) or newer
- **Kotlin 1.5+**
- **minSdkVersion 21**
- **CoinCap API 3.0 Access Key** (free signup at [coincap.io](https://coincap.io))

### Setup

```bash
git clone https://github.com/your-username/crypto-tracker.git
cd crypto-tracker
```

1. Open in Android Studio.
2. Add your CoinCap API key to `build.gradle (Module: app)`:
3. Let Gradle sync.
4. Run on emulator or device.

## Acknowledgements

- Philipp Lackner for the foundational Best Practice Guide to Android Architecture course.
- Android Developers for official architecture and theming guidance.
- Material Design 3 documentation on dynamic color.
