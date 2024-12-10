# **AuthFlow - Android Authentication App**

**AuthFlow** is a simple Android app demonstrating clean architecture principles and modern Android development practices. 
It features user authentication with Register, Login, and Home screens, integrating APIs for real-world scenarios.
The app is designed using **MVVM architecture** and implements the **Repository Pattern**, ensuring scalability and testability.

---

## **Features**
- User Registration and Login using the [Reqres API](https://reqres.in/).
- Home screen displaying a success message after authentication.
- Built with **Kotlin** and adheres to clean architecture principles.
- State management with **Kotlin Flow** and **StateFlow**.
- Dependency injection with **Dagger Hilt**.
- Asynchronous API calls with **Coroutines** and **Retrofit**.
- Follows **MVVM architecture** for a clear separation of concerns.
- Uses **ViewBinding** for easy and safe access to UI elements.

---

## **Tech Stack**
- **Language**: Kotlin
- **Architecture**: Model-View-ViewModel (MVVM)
- **Dependency Injection**: Dagger Hilt
- **Networking**: Retrofit + OkHttp
- **Coroutines**: For asynchronous programming
- **State Management**: Kotlin Flow and StateFlow
- **View Binding**: Simplified UI interactions

---

## **Project Structure**
The project is organized into clear and separate layers for better maintainability:

- **`ui`**: Contains Activities and UI logic (View layer).
- **`viewmodel`**: Handles business logic and exposes UI state using Flow (ViewModel layer).
- **`repository`**: Interfaces and implementations for accessing data (e.g., API calls).
- **`datasource`**: Network-related logic (Retrofit API interface, DI setup).
- **`model`**: Data models for API requests and responses.

---
## **To Test this App**
- **use email :* **eve.holt@reqres.in** with any password*
