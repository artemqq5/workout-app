### README.md

# WorkoutApp

WorkoutApp is a mobile application designed for planning and tracking workouts, built with a clean architecture approach and modern Android libraries. The app provides a user-friendly interface, allows users to select workout difficulty levels, and displays detailed workout information.

## Benefits of Clean Architecture

WorkoutApp follows **clean architecture principles**, which bring the following advantages:

- **Separation of Concerns**: Clean architecture organizes code into separate layers — Presentation (UI), Domain (business logic), and Data (data sources) — making it more maintainable and understandable.

- **Flexibility**: With a dedicated `Domain Layer`, the main business logic is decoupled from external libraries and interfaces. This enables developers to change or update data sources and UI components without affecting core business logic.

- **Ease of Testing**: Clean architecture simplifies testing by separating business logic from UI and data layers. This allows for focused unit testing on the Domain layer, where the main app functionality resides, and testing UI elements and data retrieval independently.

- **Reusability of Components**: Using `UseCases` in the Domain layer enables reusability of logic across different parts of the UI and for adding new features, as each `UseCase` encapsulates a single responsibility.

## Project Structure

The project structure is organized into three main layers:

1. **Presentation**: Handles UI display and user interactions.
2. **Domain**: Contains core business logic, including `UseCases` for managing user actions and interactions between Presentation and Data layers.
3. **Data**: Responsible for handling data operations through repositories, including retrieving data from various sources like databases, network, and local storage.

## Libraries Used and Their Advantages

WorkoutApp utilizes a set of modern libraries that ensure stability, simplify development, and improve app performance:

### 1. **Lottie** (`implementation(libs.lottie)`)
- **Overview**: Lottie allows seamless integration of JSON animations, created in Adobe After Effects, into Android applications.
- **Benefits**: The library enables developers to add high-quality animations to enhance user experience, making the interface more dynamic and visually engaging.

### 2. **Hilt for Dependency Injection** (`implementation(libs.hilt.android)`, `kapt(libs.dagger.hilt.compiler)`)
- **Overview**: Hilt is a dependency injection library built on Dagger and optimized for Android.
- **Benefits**: Hilt simplifies dependency management, reduces boilerplate code, and makes components easier to test. It provides a convenient and scalable way to inject dependencies, such as repositories and ViewModels, throughout the app.

### 3. **Navigation Component** (`implementation(libs.androidx.navigation.fragment.ktx)`, `implementation(libs.androidx.navigation.ui.ktx)`)
- **Overview**: Navigation Component is a framework that simplifies navigation between fragments and provides safe data transfer between them.
- **Benefits**: This library allows developers to manage navigation effectively, reducing the risk of errors and simplifying back stack management. With Navigation Component, developers can implement smooth transitions and safe argument passing, making the navigation experience seamless.

## XML Design Consideration

In one part of the UI, a "workaround" approach was used for creating a specific view layout with multiple `ImageViews` positioned relatively. While functional, this layout has limitations:

1. **Scalability**: Using relative positioning attributes can limit the flexibility of the layout, especially for adapting to different screen sizes and orientations.
2. **Limited Customization**: The current approach can be challenging to expand or modify, particularly if elements need to be updated or adjusted for new features.

### Recommendation
A better solution for future updates would be to create a **custom view**, which would allow for more flexible and scalable design. Custom views offer precise control over positioning and behavior, making it easier to adapt to different screen sizes and maintain high-quality UI components.

## Conclusion

WorkoutApp leverages clean architecture principles and modern libraries to ensure a well-structured, maintainable, and scalable codebase. This setup allows for easy testing, smooth extension of features, and a better overall user experience. By adhering to these practices, WorkoutApp remains flexible and efficient in its development and maintenance.