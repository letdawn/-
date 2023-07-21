package service;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import operate.UserScene;
import po.Patient;
import stage.Loginerror;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Controller {           //主舞台控制器
    public static Stage loginer;    //登陆失败界面

    public static Stage astage;
    @FXML
    private TextField zhanghao;     //账号文本框

    @FXML
    private PasswordField password;     //密码文本框

    @FXML
    private Button dl;              //登录按键

    @FXML
    void dengliclick(ActionEvent event) throws Exception{   //处理点击登录事件
        String zh = zhanghao.getText();
        String mm = password.getText();
        String sql = "select * from patient where patient_id = ? and password = ?";
        PreparedStatement ptst = Main.conn.prepareStatement(sql);
        ptst.setString(1,zh);
        ptst.setString(2,mm);
        ResultSet res = ptst.executeQuery();
        if(res.next()){                                 //登录成功
            int patientId = res.getInt("patient_id"); // 假设从结果集中获取患者ID
            Main.patient = new Patient(patientId);
            UserScene.bulidoperate();
        }
        else
            Platform.runLater(()->{
                try {
                    loginer=new Loginerror();     //展示登陆时失败界面
                    loginer.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
    }

    @FXML
    void zzc(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../stage/zhucefx.fxml"));
        Pane root = loader.load();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("注册");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.pstage);
        astage=stage;
        stage.show();
    }

}
