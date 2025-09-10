# Samagam

**Samagam** is an interactive event scheduler application built in Kotlin. It allows users to efficiently create, manage, and visualize events through a user-friendly interface—ideal for personal planning or small team coordination.

---

##  Features

- **Create, edit, and delete events**—customize titles, descriptions, dates, times, and optional reminders.
- **Interactive calendar view**—display events in a daily, weekly, or monthly layout.
- **Notifications or reminders**—stay informed about upcoming events (pending your implementation).
- **Responsive UI**—designed with intuitive navigation and smooth interactions (assuming modern Android or desktop GUI frameworks).
- **Data persistence**—events are saved locally using SQLite, Room, or serialized files (adjust per actual usage).

## Usage

- Once launched, Samagam provides:
- An interactive calendar to view existing events.
- Buttons to add new events—specify details like title, date/time, description.
- Options to edit or delete events via event interactions.
- Reminders or notifications triggered at scheduled times.

---

### Prerequisites
- JDK 11 or higher
- Kotlin 1.5+  
- Android Studio (for Android version) or IntelliJ IDEA (for desktop version)
- (Optional) SQLite or Room Libraries

### Clone & Setup

```bash
git clone https://github.com/sksinha2410/Samagam.git
cd Samagam
```

### Build & Run (Android)
- Open the project in Android Studio.
- Sync dependencies and run on emulator or device via Run ▶.

###Build & Run (Desktop / JVM)
- Open in IntelliJ IDEA.
- Build with Gradle:
```
./gradlew build
./gradlew run
```
- Launch the app with the generated jar, if applicable.
