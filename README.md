# Java OOP Game Collection

A Java Swing desktop application containing multiple games in one interface.
The project demonstrates core **object-oriented programming** concepts through game development.

## Games

* **DinoSim** – an endless runner where the player jumps over obstacles.
* **Pac-Man** – a maze game inspired by the classic Pac-Man.

## Features

* Game-selection home page
* Multiple games using `CardLayout`
* Reusable 60 FPS game loop
* Keyboard controls and animated sprites
* Entity updating, collision handling, and removal
* Automatic game start and stop when switching screens

## OOP Concepts

* **Abstraction:** a shared abstract `GamePanel`
* **Inheritance:** each game extends the core game panel
* **Polymorphism:** different objects use a common `Entity` type
* **Encapsulation:** fields and object state are protected
* **Composition:** game objects contain input handlers and managers
* **Dependency injection:** dependencies are passed through constructors

## Technologies

* Java
* Java Swing and AWT
* Multithreading
* Event-driven programming

## Project Structure

```text
src/
├── main/
│   ├── Main.java
│   └── homepage/
└── games/
    ├── core/
    ├── dinosim/
    └── pacman/
```

## Run the Project

1. Open the project in IntelliJ IDEA, Eclipse, or NetBeans.
2. Use Java 17 or newer.
3. Ensure the resource folders are included in the classpath.
4. Run `main.Main`.

## Adding a New Game

1. Create a new package under `games`.
2. Extend the core `GamePanel`.
3. Implement `update()`, `draw()`, and `restart()`.
4. Add the panel to the `CardLayout` container in `Main`.

## License

Created for educational purposes as an object-oriented programming project.
::: 
