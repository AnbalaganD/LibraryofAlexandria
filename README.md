# 📚 Library of Alexandria

A personal book library management app for Android — because even ancient libraries need a modern upgrade! 🏛️

## About

**Library of Alexandria** is a hobby Android project for cataloging and managing your personal book collection. Whether you're a casual reader or a bibliophile with overflowing shelves, this app helps you keep track of all your books in one place.

## ✨ Features

- **📖 Add Books** — Easily add books with details like title, author, ISBN, publisher, genre, edition, year, and description
- **🔍 Search** — Quick search across book names, authors, descriptions, genres, and publishers
- **📋 Browse Collection** — View all your books in a clean, scrollable list
- **📝 View & Edit Details** — Tap any book to see full details and make updates
- **💾 Local Storage** — All data stored locally using Room database (your collection stays on your device!)

## 🛠️ Tech Stack

| Component | Technology |
|-----------|------------|
| **Language** | Kotlin |
| **Database** | Room (SQLite) |
| **Architecture** | ViewModel + LiveData |
| **UI** | XML Layouts + Material Design |
| **Async** | Kotlin Coroutines |
| **Min SDK** | 26 (Android 8.0 Oreo) |
| **Target SDK** | 36 |

## 📱 Screenshots

*Coming soon! Feel free to add screenshots here.*

## 🚀 Getting Started

### Prerequisites
- Android Studio Ladybug or later
- JDK 11+

### Build & Run
1. Clone the repository
   ```bash
   git clone https://github.com/AnbalaganD/LibraryofAlexandria.git
   ```
2. Open the project in Android Studio
3. Sync Gradle files
4. Run on an emulator or physical device

## 📁 Project Structure

```
app/src/main/
├── java/edu/anbu/libraryofalexandria/
│   ├── Book.kt                  # Book data model (Room entity)
│   ├── BookDao.kt               # Database access interface
│   ├── AppDatabase.kt           # Room database setup
│   ├── HomeActivity.kt          # Main screen with book list
│   ├── HomeViewModel.kt         # ViewModel for home screen
│   ├── AddBookActivity.kt       # Add/Edit book screen
│   ├── BookDetailActivity.kt    # Book details screen
│   ├── SearchBookActivity.kt    # Search functionality
│   ├── BookAdapter.kt           # RecyclerView adapter
│   └── BookItemClickListener.kt # Click listener interface
└── res/
    ├── layout/                  # Activity & item layouts
    ├── menu/                    # Menu resources
    └── values/                  # Colors, strings, styles
```

## 🎯 Roadmap / Ideas

- [ ] Sort books by name, author, or publisher
- [ ] Book cover images (camera or gallery)
- [ ] Export/Import collection (JSON/CSV)
- [ ] Reading status (Read, Reading, Want to Read)
- [ ] Barcode scanner for quick ISBN lookup
- [ ] Dark mode support
- [ ] Backup to cloud

## 🤝 Contributing

This is a hobby project, but feel free to fork it, play around, or suggest improvements! 

## 📜 License

This project is for learning and personal use. Do whatever you want with it!
