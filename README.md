# Java Hotel Management System

This repository contains a simple hotel management system implemented in Java, utilizing design patterns to demonstrate modular and maintainable code. The system supports room bookings, customer management, and basic functionalities of a hotel management system. Key design patterns such as Singleton, Factory, Observer, and Decorator are implemented to structure the application efficiently.

## Project Overview
The hotel management system is designed to handle essential tasks:
1. **Room Booking**: Managing different types of room bookings.
2. **Customer Management**: Tracking customer details and notifications.
3. **Room Service and Extras**: Adding optional services to room bookings.

## Design Patterns Used
### 1. **Singleton Pattern**
   - Used for classes like `DatabaseConnection`, ensuring a single instance of the database connection throughout the application.

### 2. **Factory Pattern**
   - Used to create different types of rooms (e.g., `SingleRoom`, `DoubleRoom`, `SuiteRoom`) based on customer requests, enabling flexible and efficient room creation.

### 3. **Observer Pattern**
   - Implements a notification system for customers on the waitlist. For example, when a room becomes available, waiting customers are automatically notified.

### 4. **Decorator Pattern**
   - Allows adding extra features to rooms (e.g., WiFi, breakfast, parking) dynamically without altering the core `Room` class, providing a flexible way to extend room functionalities.

