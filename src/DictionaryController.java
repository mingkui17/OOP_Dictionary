import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class DictionaryController implements Initializable {
    @FXML
    private Button engVietSearch;

    @FXML
    private Button vietEngSearch;

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
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private AnchorPane status;

    @FXML
    private Text bigStatus;

    @FXML
    private Text smallStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void handleClicks(ActionEvent event) {

        if(event.getSource() == engVietSearch) {

            smallStatus.setText("/Dictionary/Eng-Viet/");
            bigStatus.setText("ENGLISH  -  VIETNAMESE");
            search.setText("Search");
            column1.setText("ENG - VIET (MEANING)");

            status.setBackground(new Background(new BackgroundFill(Color.rgb( 255, 228, 225), CornerRadii.EMPTY, Insets.EMPTY)));


        } else if(event.getSource() == vietEngSearch) {

            smallStatus.setText("/Dictionary/Viet-Eng/");
            bigStatus.setText("VIETNAMESE  -  ENGLISH");
            search.setText("Search");
            column1.setText("VIET - ENG");

            status.setBackground(new Background(new BackgroundFill(Color.rgb(255, 228, 225), CornerRadii.EMPTY, Insets.EMPTY)));



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


        }
    }

    @FXML
    private void handleButton(ActionEvent event) {

    }


    @FXML
    private void handClose(MouseEvent event) {

    }
}




