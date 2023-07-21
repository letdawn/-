package operate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Sceneco{
    @FXML
    public void initialize(){
        buildwel(this);
    }

    static void buildwel(Sceneco sceneco){
        try {
            // 加载另一个FXML文件
            FXMLLoader loader = new FXMLLoader(Sceneco.class.getResource("../pane/welcomefx.fxml"));
            Pane otherPane1 = loader.load();
            // 将另一个FXML文件中的Pane添加到当前FXML文件的主布局中
            sceneco.mainid.getChildren().clear();
            sceneco.mainid.getChildren().add(otherPane1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private VBox f4;

    @FXML
    private Button pln;

    @FXML
    private Pane mainid;
    @FXML
    void plnac(ActionEvent event) {
        try {
            // 加载另一个FXML文件
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../pane/plnfx.fxml"));
            Pane otherPane = loader.load();
            // 将另一个FXML文件中的Pane添加到当前FXML文件的主布局中
            mainid.getChildren().clear();
            mainid.getChildren().add(otherPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void apointac(ActionEvent event) {
        try {
            // 加载另一个FXML文件
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../pane/appoifx.fxml"));
            Pane otherPane = loader.load();
            // 将另一个FXML文件中的Pane添加到当前FXML文件的主布局中
            mainid.getChildren().clear();
            mainid.getChildren().add(otherPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void myapo(ActionEvent event) {
        try {
            // 加载另一个FXML文件
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../pane/myapofx.fxml"));
            Pane otherPane = loader.load();
            // 将另一个FXML文件中的Pane添加到当前FXML文件的主布局中
            mainid.getChildren().clear();
            mainid.getChildren().add(otherPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
