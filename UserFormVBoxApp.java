package com.example.userformvboxapp;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class UserFormVBoxApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Step 1: Create Labels and Inputs
        Label nameLabel = new Label("Name");
        TextField nameField = new TextField();

        Label fatherNameLabel = new Label("Father Name");
        TextField fatherNameField = new TextField();

        Label cnicLabel = new Label("CNIC");
        TextField cnicField = new TextField();

        Label dateLabel = new Label("Date (Picker)");
        DatePicker datePicker = new DatePicker();

        Label genderLabel = new Label("Gender");
        RadioButton maleButton = new RadioButton("Male");
        RadioButton femaleButton = new RadioButton("Female");
        ToggleGroup genderGroup = new ToggleGroup();
        maleButton.setToggleGroup(genderGroup);
        femaleButton.setToggleGroup(genderGroup);
        HBox genderBox = new HBox(10, maleButton, femaleButton);

        Label cityLabel = new Label("City");
        ComboBox<String> cityComboBox = new ComboBox<>();
        cityComboBox.getItems().addAll("City 1", "City 2", "City 3");

        Label imageLabel = new Label("Image");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an Image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);

        Button chooseImageButton = new Button("Choose Image");
        chooseImageButton.setOnAction(event -> {
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                imageView.setImage(new Image(selectedFile.toURI().toString()));
            }
        });

        VBox imageBox = new VBox(10, chooseImageButton, imageView);

        Button saveButton = new Button("Save");

        // Reset Button
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(event -> {
            nameField.clear();
            fatherNameField.clear();
            cnicField.clear();
            datePicker.setValue(null);
            genderGroup.selectToggle(null);
            cityComboBox.setValue(null);
            imageView.setImage(null);
        });

        // Step 2: Use HBox for Label and Input Pairs
        HBox nameRow = new HBox(10, nameLabel, nameField);
        HBox fatherNameRow = new HBox(10, fatherNameLabel, fatherNameField);
        HBox cnicRow = new HBox(10, cnicLabel, cnicField);
        HBox dateRow = new HBox(10, dateLabel, datePicker);
        HBox genderRow = new HBox(10, genderLabel, genderBox);
        HBox cityRow = new HBox(10, cityLabel, cityComboBox);
        HBox imageRow = new HBox(10, imageLabel, imageBox);

        // Step 3: Arrange Rows in a VBox
        VBox root = new VBox(15, nameRow, fatherNameRow, cnicRow, dateRow, genderRow, cityRow, imageRow, saveButton, resetButton);
        root.setPadding(new Insets(10)); // Add padding around the VBox

        // Step 4: Create the Scene and Show the Stage
        Scene scene = new Scene(root, 600, 500);
        primaryStage.setTitle("User Form - VBox Layout");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
