package Controllers;

import dictionary.DictionaryCommandline;
import dictionary.DictionaryManagement;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class DictionaryController implements Initializable {

    Scanner sc = new Scanner(System.in);
    DictionaryCommandline dicC = new DictionaryCommandline();
    DictionaryManagement dicMa = new DictionaryManagement();


    ObservableList<String> list = FXCollections.observableArrayList();

    @FXML
    private Button engVietSearch;

    @FXML
    private Button addEngWord;

    @FXML
    private Button removeWord;

    @FXML
    private TextField type;

    @FXML
    private Button search;

    @FXML
    private TitledPane column;

    @FXML
    private TitledPane column1;

    @FXML
    private ListView<String> wordList;

    @FXML
    private Label label2;

    @FXML
    private AnchorPane status;

    @FXML
    private Text bigStatus;

    @FXML
    private Text smallStatus;

    @FXML
    private Button clear;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            dicMa.insertFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleClicks(ActionEvent event) throws Exception {

        if(event.getSource() == engVietSearch) {

            smallStatus.setText("/Dictionary/Eng-Viet/");
            bigStatus.setText("ENGLISH  -  VIETNAMESE");
            search.setText("Search");
            column.setText("WORD LIST");
            column1.setText("ENG - VIET (MEANING)");
            clearLabel();

        } else if(event.getSource() == addEngWord) {

            smallStatus.setText("/Dictionary/addEngWord/");
            bigStatus.setText("ADDING ENGLISH WORD");
            search.setText("Add");
            column.setText("Add instruction");
            column1.setText("ADDED WORD");
            status.setBackground(new Background(new BackgroundFill(Color.rgb(255, 228, 225), CornerRadii.EMPTY, Insets.EMPTY)));
            list.setAll("Type this to add", "English word: ", "english      vietnamese");
            wordList.getItems().setAll(list);

        } else if(event.getSource() == removeWord) {

            smallStatus.setText("/Dictionary/removeWord/");
            bigStatus.setText("REMOVING WORD");
            search.setText("Remove");
            column.setText("Remove Instruction");
            column1.setText("REMOVED WORD");
            status.setBackground(new Background(new BackgroundFill(Color.rgb(255, 228, 225), CornerRadii.EMPTY, Insets.EMPTY)));
            list.setAll("Type this to remove", "English word: ", "delete: english");
            wordList.getItems().setAll(list);

        }
    }


    public void clearLabel() {
        list.clear();
        wordList.getItems().clear();
        label2.setText("");
        type.setText("");
    }


    public void handleButton(ActionEvent event) {
    }

    @FXML
    public void showList(String s) {
        System.out.println("1");
        for(String i : dicC.dictionarySearcher(s, dicMa.words)) {
            list.add(i);
        }
        wordList.getItems().addAll(list);
    }

    @FXML
    public void showMeaning(String s) {
        label2.setText(dicMa.dictionaryLookup(s));
    }

    @FXML
    public void mouseClicked(MouseEvent event) {
        String s = type.getText();
        if (event.getSource() == search) {
            showMeaning(s);
            showList(s);
        }

        if (event.getSource() == clear) {
            clearLabel();
        }
        
    }

}




