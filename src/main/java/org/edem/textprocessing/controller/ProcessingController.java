package org.edem.textprocessing.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.edem.textprocessing.utils.Utils;

import java.util.List;

public class ProcessingController {



    @FXML
    private Label error_text;

    @FXML
    private Label error_text_regex;

    @FXML
    private Button match;

    @FXML
    private Button replace;
    @FXML
    private Label replace_error_text;

    @FXML
    private TextArea replacetext;


    @FXML
    private Button search;

    @FXML
    private TextFlow result;

    @FXML
    private TextArea regex;

    @FXML
    private TextArea text;

    @FXML
    private Button update;

    @FXML
    private Button save;

    @FXML
    private Button delete;

    @FXML
    private ListView<String> listview;

    private ObservableList<String> entries;

    @FXML
    public void initialize() {
        entries = FXCollections.observableArrayList();
        listview.setItems(entries);

        listview.setCellFactory(TextFieldListCell.forListView());


        result.setVisible(false);

        listview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                populateFieldsFromListView(newValue);
            }
        });

        listview.setOnEditCommit(event -> {
            int index = event.getIndex();
            String updatedValue = event.getNewValue();

            if (updatedValue == null || updatedValue.trim().isEmpty()) {
                System.out.println("Invalid value! Edit ignored.");
            } else {
                entries.set(index, updatedValue);
            }
        });


    }

    @FXML
    void match(MouseEvent event) {

        if (regex.getText().isEmpty()) {
            error_text_regex.setText("Regex cannot be empty");
        }
        else if(text.getText().isEmpty()){
            error_text.setText("input text cannot be empty");
        }
        else {
            error_text.setText("");
            error_text_regex.setText("");
            boolean result = Utils.isMatch( text.getText(), regex.getText());

            if (result){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Match Found");
                alert.setContentText("There's a match for the word '"+ text.getText() + "' ");
                alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("No Matches Found");
                alert.setContentText("There's no match for the word '"+ text.getText() + "' ");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void replace(MouseEvent event) {
        if (regex.getText().isEmpty()) {
            showTemporaryMessage(error_text_regex, "Regex cannot be empty", 3000);
        }
        else if (text.getText().isEmpty()) {
            showTemporaryMessage(error_text, "Input text cannot be empty", 3000);
        }
        else if (replacetext.getText().isEmpty()) {
            showTemporaryMessage(replace_error_text, "Replace field cannot be empty", 3000);
        }
        else {
            error_text.setText("");
            error_text_regex.setText("");
            replace_error_text.setText("");
            String originalText = text.getText();
            String regexPattern = regex.getText();
            String replacement = replacetext.getText();

            String replacedText = originalText.replaceAll(regexPattern, replacement);

            text.setText(replacedText);

            result.getChildren().clear();
            result.getChildren().add(new Text(replacedText));
            result.setVisible(true);
        }

    }

    @FXML
    void search(MouseEvent event) {
        if (regex.getText().isEmpty()) {
            showTemporaryMessage(error_text_regex, "Regex cannot be empty", 3000);
        }
        else if (text.getText().isEmpty()) {
            showTemporaryMessage(error_text, "Input text cannot be empty", 3000);
        }
        else if (replacetext.getText().isEmpty()) {
            showTemporaryMessage(replace_error_text, "Replace field cannot be empty", 3000);
        }
        else {
            error_text.setText("");
            error_text_regex.setText("");

            List<List<Integer>> results = Utils.search(regex.getText(), text.getText());
            result.getChildren().clear();
            int lastEnd = 0;
            for (List<Integer> list : results) {
                String beforeMatch = text.getText().substring(lastEnd, list.get(0));
                if (!beforeMatch.isEmpty()) {
                    result.getChildren().add(new Text(beforeMatch));
                }

                String matchText = text.getText().substring(list.get(0), list.get(1));
                Rectangle background = new Rectangle(0, 0, matchText.length() * 7, 15);
                background.setFill(Color.BLACK);

                Text matchTextNode = new Text(matchText);
                matchTextNode.setFill(Color.WHITE);
//                matchTextNode.setFont(Font.font("Arial", 14));

                StackPane matchContainer = new StackPane(background, matchTextNode);
                result.getChildren().add(matchContainer);

                lastEnd = list.get(1);
            }
            String remainingText = text.getText().substring(lastEnd);
            if (!remainingText.isEmpty()) {
                result.getChildren().add(new Text(remainingText));
            }

            result.setVisible(true);
        }
    }

    @FXML
    void deletefromlist(MouseEvent event) {
        String selectedItem = listview.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            entries.remove(selectedItem);
        }
        System.out.println("select an item to delete");
    }

    @FXML
    void savetolist(MouseEvent event) {
        String inputText = text.getText();

        if (inputText.isEmpty()) {
            System.out.println("Input field cannot be empty");
        } else {

            entries.add(inputText);

            text.clear();

        }

    }


    private void populateFieldsFromListView(String selectedItem) {
        text.setText(selectedItem);
    }

    @FXML
    void update (MouseEvent event){
        String oldText = listview.getSelectionModel().getSelectedItem();
        String newText = text.getText();

    }

    private void showTemporaryMessage(Label label, String message, int durationInMilliseconds) {
        label.setText(message);

        Timeline timeline = new Timeline(
                new KeyFrame( javafx.util.Duration.millis(durationInMilliseconds), event -> label.setText(""))
        );
        timeline.setCycleCount(1);
        timeline.play();
    }
}
