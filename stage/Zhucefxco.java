package stage;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pane.Docinfxco;
import pane.Plnfxco;
import service.Controller;
import service.Main;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static pane.Plnfxco.changeer;

public class Zhucefxco {
    @FXML
    private TextField name1;

    @FXML
    private PasswordField ps1;

    @FXML
    private TextField name11;

    @FXML
    void zc1(ActionEvent event) throws SQLException, IOException {
        if (name1.getText() != null && ps1.getText() != null) {
            String sql = "INSERT INTO patient (patient_id, name ,password)VALUES (?,?,?);";
            PreparedStatement ptst = Main.conn.prepareStatement(sql);
            ptst.setString(1, name11.getText());
            ptst.setString(2, name1.getText());
            ptst.setString(3, ps1.getText());
            int row = ptst.executeUpdate();
            if (row > 0) {
                Parent root = FXMLLoader.load(getClass().getResource("../stage/sucfx.fxml"));
                Platform.runLater(() -> {
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.setTitle("成功");
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(Docinfxco.yystage);
                    stage.show();
                });
                Controller.astage.close();
            } else {
                Platform.runLater(() -> {
                    try {
                        changeer = new Changeer();     //展示修改时失败界面
                        Changeerco changeerco= changeer.refr();
                        changeerco.o.setText("账号需为全数字，且不能重复，请修改");
                        changeer.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
        else {
            Platform.runLater(() -> {
                try {
                    changeer = new Changeer();     //展示修改时失败界面
                    changeer.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
