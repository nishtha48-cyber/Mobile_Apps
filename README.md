## 🛠 Tech Stack & Tools
*   **Language:** Java (JDK 11+)
*   **UI Framework:** XML 
*   **Build System:** Gradle (Groovy/Kotlin DSL)
*   **Minimum SDK:** API 23 (Android 7.0)
*   **Target SDK:** API 34 (Android 14.0)

---

## 🚀 Applications Overview

### 1️⃣ Currency Converter (Theme Persistence)
A high-precision conversion utility for **INR, USD, JPY, and EUR**.
*   **Key Feature:** Implements a dedicated Settings page to toggle between **Light and Dark themes**.
*   **Logic:** Uses `SharedPreferences` to persist the theme state. The conversion logic uses a base-currency mathematical model to ensure O(1) calculation efficiency.

### 2️⃣ Advanced Media Player (Streaming & Local)
A dual-mode player for both disk-based audio and network-based video.
*   **Key Feature:** Streams direct MP4 video from a URL and opens `.mp3` files from the device disk.
*   **Logic:** Utilizes `VideoView` with `ZOrder` management and `MediaPlayer` with lifecycle-aware `release()` calls to prevent memory leaks and hardware resource conflicts.

### 3️⃣ Real-Time Sensor Data (Hardware Interaction)
A live monitoring tool for physical device sensors.
*   **Key Feature:** Tracks **Accelerometer**, **Light**, and **Proximity** sensors simultaneously.
*   **Logic:** Implements the `SensorEventListener` interface. Logically manages battery consumption by registering listeners in `onResume()` and unregistering in `onPause()`.

### 4️⃣ SnapGrid Gallery (Scoped Storage & Media CRUD)
*7-Mark Advanced Project:* A full-stack photo management system.
*   **Key Features:** 
    *   **Capture:** Uses `MediaStore` and `FileProvider` to take full-res photos.
    *   **SAF (Storage Access Framework):** Allows users to **choose any folder** on the device to save or view photos.
    *   **Grid UI:** Features a `RecyclerView` with `GridLayoutManager` for a gallery-style view.
    *   **Metadata:** Extracts image name, path, size (KB), and date taken using the `DocumentFile` API.
    *   **Secure Deletion:** Implements a `Confirmation AlertDialog` before permanent file deletion.

---

## 📐 Logical Implementation Details

### ✅ Scoped Storage & Security
For the **SnapGrid** app, I avoided hardcoded paths. I logically implemented `Intent.ACTION_OPEN_DOCUMENT_TREE` to let the user select a folder. This satisfies modern Android security requirements and ensures the app only accesses user-approved directories.

### ✅ Activity Back-Stack Management
Navigating between the Camera, Gallery, and Details pages is handled via `finish()` and `startActivity()`. This ensures a logical "Up Navigation" flow where the user can always return to the previous state without creating redundant activity instances.

### ✅ Modern Material UI
All apps utilize `CardView` for visual elevation and `MaterialButton` for a consistent, professional design. Every input field is styled with custom XML drawables to ensure readability in both Light and Dark modes.

---

## 🔧 How to Run
1.  **Clone the Repo:** `git clone https://github.com/nishtha48-cyber/Mobile_Apps.git`
2.  **Open in Android Studio:** Open the project and wait for Gradle sync.
3.  **Permissions:** Grant **Camera** and **Internet** permissions when prompted.
4. **Build and run the application on:**  Android Emulator or physical Android device
---

## 👨‍💻 Author

Nishtha gupta


