<<<<<<< HEAD
=======
package Controllers;

import dictionary.DictionaryCommandline;
import dictionary.DictionaryManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
>>>>>>> f7f291396e2c679739b3f7dc4164635663e25fdf
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
    }

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {

        if(event.getSource() == engVietSearch) {

            smallStatus.setText("/Dictionary/Eng-Viet/");
            bigStatus.setText("ENGLISH  -  VIETNAMESE");
            search.setText("Search");
            column1.setText("ENG - VIET (MEANING)");
            status.setBackground(new Background(new BackgroundFill(Color.rgb( 255, 228, 225), CornerRadii.EMPTY, Insets.EMPTY)));

        } else if(event.getSource() == addEngWord) {

            smallStatus.setText("/Dictionary/addEngWord/");
            bigStatus.setText("ADDING ENGLISH WORD");
            search.setText("Add");
            column1.setText("ADDED WORD");

            status.setBackground(new Background(new BackgroundFill(Color.rgb(255, 228, 225), CornerRadii.EMPTY, Insets.EMPTY)));


        } else if(event.getSource() == removeWord) {

            smallStatus.setText("/Dictionary/removeWord/");
            bigStatus.setText("REMOVING WORD");
            search.setText("Remove");
            column1.setText("REMOVED WORD");

            status.setBackground(new Background(new BackgroundFill(Color.rgb(255, 228, 225), CornerRadii.EMPTY, Insets.EMPTY)));
<<<<<<< HEAD

=======
>>>>>>> f7f291396e2c679739b3f7dc4164635663e25fdf

        }
    }


    public void clearLabel() {
        list.removeAll();
        label2.setText("");
    }


    public void handleButton(ActionEvent event) {
    }

    @FXML
    public void mouseClicked(MouseEvent event) {
        if (event.getSource() == clear) {
            clearLabel();
        }
        String s = type.getText();
        if(event.getSource() == search) {
            label2.setText("xin chao");
            list.addAll("hi", "hello", "holiday", "hope", "hip", "hurricane");
            wordList.getItems().addAll(list);
        }

    }
}




