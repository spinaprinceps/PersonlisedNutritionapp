# Smart Nutrition Android App

A comprehensive nutrition tracking and meal planning application for Android devices.

## Features

- User authentication with email and Google Sign-In
- Personalized profile setup with BMI calculation
- Diet recommendations based on user preferences
- Food analysis using camera
- Recipe suggestions based on available ingredients
- Nutrition overview and tracking
- Regional Indian cuisine support

## Prerequisites

- Android Studio Arctic Fox or newer
- Android SDK 21 or higher
- Google Play Services
- Firebase account

## Setup Instructions

1. Clone the repository:
```bash
git clone https://github.com/yourusername/smart-nutrition.git
```

2. Open the project in Android Studio

3. Create a new Firebase project:
   - Go to [Firebase Console](https://console.firebase.google.com)
   - Create a new project
   - Add an Android app with package name `com.smartnutrition.app`
   - Download the `google-services.json` file
   - Place it in the `app` directory

4. Enable Firebase Authentication:
   - In Firebase Console, go to Authentication
   - Enable Email/Password and Google Sign-In methods

5. Enable Firebase Firestore:
   - In Firebase Console, go to Firestore Database
   - Create a new database in test mode

6. Build and run the project:
   - Connect an Android device or start an emulator
   - Click the "Run" button in Android Studio

## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/smartnutrition/app/
│   │   │   ├── LoginActivity.java
│   │   │   ├── ProfileSetupActivity.java
│   │   │   ├── DashboardActivity.java
│   │   │   └── ...
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   ├── values/
│   │   │   └── ...
│   │   └── AndroidManifest.xml
│   └── test/
└── build.gradle
```

## Dependencies

- Firebase Authentication
- Firebase Firestore
- Google ML Kit for food recognition
- MPAndroidChart for nutrition visualization
- CameraX for food image capture
- Material Design Components

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Firebase for backend services
- Google ML Kit for food recognition
- Material Design for UI components 