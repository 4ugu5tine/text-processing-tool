package org.edem.textprocessing.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import org.edem.textprocessing.TextApplication;

public class LayoutController {
    @FXML
    private VBox content;

    @FXML
    void navRegex() {
        loadPage("processor.fxml");
    }

    public void loadPage (String pageName){
        try{
            FXMLLoader loader = new FXMLLoader(TextApplication.class.getResource(pageName));
            Parent page = loader.load();
            content.getChildren().clear();
            content.getChildren().add(page);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
