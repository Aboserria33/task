# Java Login Form

A simple desktop login application built with **Java 21** and the **AWT** (Abstract Window Toolkit) GUI framework. It presents a login window where a user authenticates with a username, password, and role, and is taken to a welcome page on success.

## Features

- **Login authentication** — credentials are checked against a list of registered users.
- **Username and password validation** — empty or incorrect credentials are rejected with clear error messages.
- **Role selection** — the user must pick the correct role: **Student**, **TA**, or **Professor**.
- **Three login attempts limit** — after 3 wrong passwords, login is disabled.
- **Welcome page** — a personalized "Hello" screen shown after a successful login.

## Technologies Used

- **Java 21**
- **AWT** (Abstract Window Toolkit) for the GUI

## How to Compile and Run

Make sure you have JDK 21 installed, then from the project directory:

```bash
# Compile
javac LoginFormTask.java

# Run
java LoginFormTask
```

## Demo Login Credentials

| Username  | Password | Role      |
|-----------|----------|-----------|
| `student1`| `pass123`| Student   |
| `ta1`     | `ta123`  | TA        |
| `prof1`   | `prof123`| Professor |

> Note: You must also select the matching role in the dropdown for login to succeed.
