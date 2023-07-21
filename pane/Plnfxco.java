package pane;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import operate.UserScene;
import service.Controller;
import service.Main;
import stage.Changeer;
import stage.Loginerror;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static service.Main.patient;

public class Plnfxco {

    public static Changeer changeer;
    @FXML
    private Pane plnid;

    @FXML
    private TextField pname;

    @FXML
    private TextField pgender;

    @FXML
    private TextField page;

    @FXML
    private TextField pnumber;

    @FXML
    private TextField pmh;

    @FXML
    public void initialize() throws SQLException {
        refresh(this);
    }

    @FXML
    void pchange(ActionEvent event) throws Exception{
        String name = pname.getText();
        String gender =pgender.getText();
        String number =pnumber.getText();
        String history =pmh.getText();
        String age =page.getText();
        if(name.isEmpty()||gender.isEmpty()|| number.isEmpty()||age.isEmpty()||!containsOnlyDigits(age)){
            Platform.runLater(()->{
                try {
                    changeer =new Changeer();     //展示修改时失败界面
                    changeer.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            return;
        }
        else{
            String sql = "UPDATE Patient SET name = ? WHERE patient_id = ?";
            PreparedStatement stmt = Main.conn.prepareStatement(sql);
            stmt.setString(1,name);
            stmt.setInt(2, patient.getPatient_id());
            int row = stmt.executeUpdate();
            if(row<1){

            }
            sql = "UPDATE Patient SET age = ? WHERE patient_id = ?";
            stmt = Main.conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(age));
            stmt.setInt(2, patient.getPatient_id());
            row = stmt.executeUpdate();
            if (row < 1) {
                // 处理更新失败的情况
            }

            sql = "UPDATE Patient SET gender = ? WHERE patient_id = ?";
            stmt = Main.conn.prepareStatement(sql);
            stmt.setString(1, gender);
            stmt.setInt(2, patient.getPatient_id());
            row = stmt.executeUpdate();
            if (row < 1) {
                // 处理更新失败的情况
            }

            sql = "UPDATE Patient SET phone_number = ? WHERE patient_id = ?";
            stmt = Main.conn.prepareStatement(sql);
            stmt.setString(1, number);
            stmt.setInt(2, patient.getPatient_id());
            row = stmt.executeUpdate();
            if (row < 1) {
                // 处理更新失败的情况
            }

            sql = "UPDATE Patient SET medical_history = ? WHERE patient_id = ?";
            stmt = Main.conn.prepareStatement(sql);
            stmt.setString(1, history);
            stmt.setInt(2, patient.getPatient_id());
            row = stmt.executeUpdate();
            if (row < 1) {
                // 处理更新失败的情况
            }
        }
        refresh(this);
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
    }

    @FXML
    void pflush(ActionEvent event) throws SQLException {
        refresh(this);
    }
    void refresh(Plnfxco p) throws SQLException {
        String sql = "select * from patient where patient_id = ?";
        PreparedStatement ptst = Main.conn.prepareStatement(sql);
        ptst.setString(1, String.valueOf(patient.getPatient_id()));
        ResultSet res = ptst.executeQuery();
        if(res.next()){                                 //录入信息
            patient.setPatient_id(res.getInt(1));
            patient.setName(res.getString(2));
            patient.setGender(res.getString(3));
            patient.setAge(res.getInt(4));
            patient.setPhone_number(res.getString(5));
            patient.setMedical_history(res.getString(8));
        }
        p.pname.setText(patient.getName());
        p.page.setText(String.valueOf(patient.getAge()));
        p.pgender.setText(patient.getGender());
        p.pnumber.setText(patient.getPhone_number());
        p.pmh.setText(patient.getMedical_history());
    }
    public static boolean containsOnlyDigits(String str) {
        // 使用正则表达式匹配整个字符串是否只包含数字
        return str.matches("[0-9]+");
    }
}
