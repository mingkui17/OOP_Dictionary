package Controllers;

import dictionary.DictionaryManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class DictionaryController implements Initializable {
    DictionaryManagement dicMa = new DictionaryManagement();
    ObservableList<String> list = FXCollections.observableArrayList();

    @FXML
    private TextField type;

    @FXML
    private Button search;

    @FXML
    private ListView<String> wordList;

    @FXML
    private Label label2;

    @FXML
    private Button clear;

    @FXML
    private Button speaker;

    @FXML
    private TextField engWordAdded;

    @FXML
    private TextField vieWordAdded;

    @FXML
    private Button addButton;

    @FXML
    private Label labelAdd;

    @FXML
    private TextField removeWord;

    @FXML
    private Button removeButton;

    @FXML
    private Label labelRemove;

    @FXML
    private TextField oldWord;

    @FXML
    private TextField newWord;

    @FXML
    private Button editButton;

    @FXML
    private Label labelEdit;

    @FXML
    private TextField newMeaning;

    @FXML
    private Button export;

    @FXML
    private Label labelExport;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            dicMa.insertFromDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clearLabel() {
        list.clear();
        wordList.getItems().clear();
        label2.setText("");
        type.setText("");
        labelExport.setText("");
    }

    @FXML
    public void showList(String s) {
        for(String i : dicMa.dictionarySearcher1(s)) {
            list.add(i);
        }
        wordList.getItems().addAll(list);
    }

    @FXML
    public void showMeaning(String s) {
        label2.setText(dicMa.dictionaryLookup(s));
    }

    @FXML
    public void chooseWordFromList(MouseEvent event) {
        String w = wordList.getSelectionModel().getSelectedItem();
        type.setText(w);
        showMeaning(w);
    }

    @FXML
    public void mouseClicked(MouseEvent event) {

        String s = type.getText();
        if (event.getSource() == search) {
            clearLabel();
            type.setText(s);
            showMeaning(s);
            showList(s);
        }

        if (event.getSource() == clear) {
            clearLabel();
        }

        if(event.getSource() == speaker) {
            dicMa.TextToSpeech(s);
        }

    }

    @FXML
    public void exporting(MouseEvent event) {
        if(event.getSource() == export) {
            clearLabel();
            dicMa.dictionaryExportToFile();
            labelExport.setText("Export to file successfully!");
        }
    }

    @FXML
    public void addWindow(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Add");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addingWord(ActionEvent event) {
        String s1 = engWordAdded.getText();
        String s2 = vieWordAdded.getText();
        if(event.getSource() == addButton) {
            labelAdd.setText(dicMa.addWord(s1,s2));
        }
    }

    @FXML
    public void removeWindow(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RemoveWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Remove");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't load new window");
        }
    }

    @FXML
    public void removingWord(ActionEvent event) {
        String s = removeWord.getText();
        if(event.getSource() == removeButton) {
            labelRemove.setText(dicMa.deleteWord(s));
        }
    }

    @FXML
    public void editWindow(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Edit");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't load new window");
        }
    }

    @FXML
    public void editingWord(ActionEvent event) {
        String s1 = oldWord.getText();
        String s2 = newWord.getText();
        String s3 = newMeaning.getText();
        if(event.getSource() == editButton) {
            labelEdit.setText(dicMa.editWord(s1, s2, s3));
        }
    }
}




