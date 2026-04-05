This repository contains a collection of four Android applications developed as part of a college project. These apps demonstrate proficiency in UI/UX design, hardware interaction, multimedia streaming, and modern file system management using Java and XML.

📱 Project Overview
1. Currency Converter (with Theme Persistence)

A high-precision converter for four major currencies (INR, USD, JPY, EUR).

Key Features: Real-time calculation, custom dropdown selection, and a dedicated Settings page.

Technical Logic:

Theme Management: Utilizes SharedPreferences and AppCompatDelegate to persist user theme choices (Light/Dark mode) across app restarts.

Mathematical Model: Implements a USD-base conversion logic to ensure modularity when adding new currencies.

UI/UX: Uses CardView and Material Design attributes for a clean, floating-element interface.

2. Media Player (Hybrid Playback & Streaming)

A versatile media tool capable of handling both local disk files and network-based streams.

Key Features: Open local audio files, stream MP4 video from URLs, and full playback controls (Play, Pause, Stop, Restart).

Technical Logic:

Hardware Decoders: Uses MediaPlayer for audio and VideoView for video to optimize system resource usage.

Intent System: Implements ACTION_OPEN_DOCUMENT to securely browse device storage without compromising system security.

Network Security: Configured with usesCleartextTraffic and OnPreparedListener to handle asynchronous network buffering.

3. Sensors (Hardware Interaction)

An app that monitors and displays live data from the device's physical hardware sensors.

Key Features: Real-time tracking of Accelerometer (Motion), Light (Illuminance), and Proximity (Distance).

Technical Logic:

Sensor Framework: Utilizes SensorManager and SensorEventListener to create a low-latency data stream.

Battery Optimization: Logically registers listeners in onResume() and unregisters them in onPause() to prevent background battery drain.

Data Formatting: Real-time XYZ coordinate mapping and scalar unit conversion (Lux/cm).

4. SnapGrid Gallery (Scoped Storage & Media Management)

A comprehensive photo management system that integrates camera hardware with the Android File System.

Key Features: Capture full-res photos, custom folder selection (SAF), grid-based gallery, image metadata viewing, and secure deletion.

Technical Logic:

Scoped Storage (SAF): Implements Intent.ACTION_OPEN_DOCUMENT_TREE and DocumentFile API to comply with modern Android security, allowing users to choose specific folders.

FileProvider: Uses content:// URIs instead of raw file paths to safely share captured media with the system camera.

Adapter Pattern: Features a RecyclerView with GridLayoutManager for memory-efficient image rendering.

CRUD Operations: Implements file deletion with a Confirmation AlertDialog and extracts metadata (size, date, path) via the Document Provider.

🛠 Technical Stack

Language: Java (JDK 11+)

Layout: XML (Material Design 3 Components)

Build System: Gradle (Kotlin DSL/Groovy)

Minimum SDK: API 23 (Android 7.0 Nougat)

Target SDK: API 34 (Android 14.0 Upside Down Cake)

🚀 Installation & Setup

Clone the Repository:

code
Bash
download
content_copy
expand_less
git clone https://github.com/yourusername/snapgrid-portfolio.git

Open in Android Studio:

File > Open > Select Project Folder.

Sync Gradle:

Ensure all dependencies (CardView, Material Components, DocumentFile) are downloaded.

Run on Emulator/Physical Device:

Note for SnapGrid: Grant Camera and Storage permissions when prompted on the first run.

Note for Media Player: Use a direct MP4 link (e.g., https://www.w3schools.com/html/mov_bbb.mp4) for testing the stream.

📐 Logical Design Principles

Throughout this portfolio, I have followed standard Android development best practices:

Activity Lifecycle: Ensuring state preservation and resource cleanup during screen transitions.

Visual Hierarchy: Using elevation, contrast, and spacing to guide user interaction.

Input Validation: Handling null file paths, empty URL strings, and invalid numeric inputs to prevent app crashes.

Navigation Stack: Properly managing the Activity Back-Stack using finish() to provide a seamless "Up" navigation experience.
