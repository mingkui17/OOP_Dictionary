package Controllers;

import dictionary.DictionaryCommandline;
import dictionary.DictionaryManagement;
import dictionary.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class DictionaryController implements Initializable {

    Scanner sc = new Scanner(System.in);
    DictionaryCommandline dicC = new DictionaryCommandline();
    DictionaryManagement dicMa = new DictionaryManagement();
    Word w = new Word();

    ObservableList<String> list = FXCollections.observableArrayList();

    @FXML
    private TextField type;

    @FXML
    private TextField typeAdd;

    @FXML
    private TextField typeRemove;

    @FXML
    private Button search;

    @FXML
    private ListView<String> wordList;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Button clear;

    @FXML
    private Button add;

    @FXML
    private Button remove;

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

    }

    public void clearLabel() {
        list.clear();
        wordList.getItems().clear();
        label2.setText("");
        type.setText("");
        label1.setText("");
        typeAdd.setText("");
        typeRemove.setText("");
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

        String s1 = typeAdd.getText();
        if(event.getSource() == add) {
            label1.setText(dicMa.addWord(s1));
        }

        String s2 = typeRemove.getText();
        if(event.getSource() == remove) {
            label1.setText(dicMa.deleteWord(s2));
        }

    }

}



