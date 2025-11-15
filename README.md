# Investigator Sheet – JavaFX

A Java and JavaFX application for creating and managing RPG-style character sheets, 
including attributes, skills, life calculation, and dice rolls.

## Features

- Editable attributes (Strength, Dexterity, Constitution, etc.)
- Skills automatically linked to their corresponding attributes
- Dice rolling system with basic result evaluation
- Automatic life calculation based on Constitution
- Real-time life bar updates
- Save character data to a JSON file
- Load character data from a JSON file
- Interface built with FXML and CSS

## Technologies Used

- Java 17 or higher
- JavaFX
- Gson
- CSS
- FXML

## How to Run

1. Install Java 17 or higher.
2. Configure the JavaFX SDK in your project or system environment.
3. Run the main class: `application.MainApp`
4. If running from a terminal, manually include the JavaFX modules in the command.

## Project Structure

- application/MainApp.java — application entry point
- controllers/MainController.java — UI logic and event handling
- models/entities — Character, Attributes and Skills
- models/enums/AttributeType.java — attribute types and default skills
- models/utils/DiceRoller.java — dice and skill roll mechanics
- views/MainView.fxml — interface layout
- views/style.css — UI styling

## Saving and Loading

- The **Save** button exports the character data to `character.json`.
- The **Load** button reads this file and restores the character information in the interface.

## License

Free to use and modify.
