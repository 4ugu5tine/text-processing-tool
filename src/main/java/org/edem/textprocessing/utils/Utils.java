package org.edem.textprocessing.utils;

import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static List<List<Integer>> search( String regex, String text){
        List<List<Integer>> positions = new ArrayList<>();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()){
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(matcher.start());
            temp.add(matcher.end());
            positions.add(temp);
        }
        return positions;
    }

    public static boolean isMatch(String text, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    public static String replace(String text, String regex, String replacement){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return text.replaceAll(regex, replacement);
    }

    public void showNotification(Alert.AlertType type, String title, String message){
        Alert alert = new Alert(type);
        alert.setContentText(message);
        alert.setTitle(title);
        alert.showAndWait();
    }
}
