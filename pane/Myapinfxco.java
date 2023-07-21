package pane;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.Main;
import stage.Aginfxco;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Myapinfxco {
    public static Myapinfxco m1;

    private int index,sum,num;

    @FXML
    private Label ph1;

    @FXML
    private Label doname1;

    @FXML
    private Label apid1;

    @FXML
    private Label ks1;

    @FXML
    private Label data1;

    @FXML
    private Label ph2;

    @FXML
    private Label doname2;

    @FXML
    private Label apid2;

    @FXML
    private Label ks2;

    @FXML
    private Label data2;

    @FXML
    private Label ph3;

    @FXML
    private Label doname3;

    @FXML
    private Label apid3;

    @FXML
    private Label ks3;

    @FXML
    private Label data3;

    @FXML
    void can1(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../stage/aginfx.fxml"));
        Pane root = loader.load();
        Aginfxco aginfxco = loader.getController();
        aginfxco.set(apid1.getText(),index,sum);
        Platform.runLater(()->{
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("取消预约");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(Main.pstage);
            astage=stage;
            stage.show();
        });
    }

    @FXML
    void can2(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../stage/aginfx.fxml"));
        Pane root = loader.load();
        Aginfxco aginfxco = loader.getController();
        aginfxco.set(apid2.getText(),index,sum);
        Platform.runLater(()->{
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("取消预约");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(Main.pstage);
            astage=stage;
            stage.show();
        });
    }

    @FXML
    void can3(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../stage/aginfx.fxml"));
        Pane root = loader.load();
        Aginfxco aginfxco = loader.getController();
        aginfxco.set(apid3.getText(),index,sum);
        Platform.runLater(()->{
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("取消预约");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(Main.pstage);
            astage=stage;
            stage.show();
        });
    }

    @FXML
    private Button c1;

    @FXML
    private Button c2;

    @FXML
    private Button c3;

    public static Stage astage;

    public void set(int index,int sum) throws SQLException {
        m1=this;
        int doid[]=new int[100];
        int dpid[]=new int[100];
        this.index=index;
        this.sum=sum;
        num=4;
        if(index*5>sum){
            num=sum-(index-1)*5;
        }
        String sql = "SELECT * FROM appointment WHERE patient_id = ?";
        PreparedStatement ptst = Main.conn.prepareStatement(sql);
        ptst.setString(1, String.valueOf(Main.patient.getPatient_id()));
        ResultSet res = ptst.executeQuery();
        if(num<3){
            c3.setDisable(true);
            c3.setStyle("-fx-background-color: rgb(244,244,244);");
            c3.setText("");
            apid3.setText("");
            doname3.setText("");
            ph3.setText("");
            ks3.setText("");
            data3.setText("");
            if(num<2){
                c2.setDisable(true);
                c2.setStyle("-fx-background-color: rgb(233,233,233);");
                c2.setText("");
                apid2.setText("");
                doname2.setText("");
                ph2.setText("");
                ks2.setText("");
                data2.setText("");
                if(num<1){
                    c1.setDisable(true);
                    c1.setStyle("-fx-background-color: rgb(244,244,244);");
                    c1.setText("");
                    apid1.setText("");
                    doname1.setText("");
                    ph1.setText("");
                    ks1.setText("");
                    data1.setText("");
                }
            }
        }
        if (!res.next())
            return;
        int i;
        for(i=1;i<(index-1)*5;i++){
            res.next();
        }
        for (i=1;i<=num;i++,res.next()) {
            if (i == 1) {
                apid1.setText(res.getString(1));
                data1.setText(res.getString(4));
                doid[i]=res.getInt(3);
                dpid[i]=res.getInt(5);
                if(res.getInt(6)==0){
                    c1.setText("取消预约");
                }
                else {
                    c1.setText("已完成");
                    c1.setDisable(true);
                }
            }
            if (i == 2) {
                apid2.setText(res.getString(1));
                data2.setText(res.getString(4));
                doid[i]=res.getInt(3);
                dpid[i]=res.getInt(5);
                if(res.getInt(6)==0){
                    c2.setText("取消预约");
                }
                else {
                    c2.setText("已完成");
                    c2.setDisable(true);
                }
            }
            if (i == 3) {
                apid3.setText(res.getString(1));
                data3.setText(res.getString(4));
                doid[i]=res.getInt(3);
                dpid[i]=res.getInt(5);
                if(res.getInt(6)==0){
                    c3.setText("取消预约");
                }
                else {
                    c3.setText("已完成");
                    c3.setDisable(true);
                }
            }
        }
        sql = "select * from doctor where doctor_id = ?";
        ptst = Main.conn.prepareStatement(sql);
        for (i=1;i<=num;i++,res.next()){
            if (i==1){
                ptst.setString(1, String.valueOf(doid[i]));
                res = ptst.executeQuery();
                if(res.next()){
                    doname1.setText(res.getString(2));
                    ph1.setText(res.getString(5));
                }
            }
            if (i==2){
                ptst.setString(1, String.valueOf(doid[i]));
                res = ptst.executeQuery();
                if(res.next()){
                    doname2.setText(res.getString(2));
                    ph2.setText(res.getString(5));
                }
            }
            if (i==3){
                ptst.setString(1, String.valueOf(doid[i]));
                res = ptst.executeQuery();
                if(res.next()){
                    doname3.setText(res.getString(2));
                    ph3.setText(res.getString(5));
                }
            }
        }
        sql = "select * from department where department_id = ?";
        ptst = Main.conn.prepareStatement(sql);
        for (i=1;i<=num;i++,res.next()){
            if (i==1){
                ptst.setString(1, String.valueOf(dpid[i]));
                res = ptst.executeQuery();
                if(res.next()){
                    ks1.setText(res.getString(2));
                }
            }
            if (i==2){
                ptst.setString(1, String.valueOf(dpid[i]));
                res = ptst.executeQuery();
                if(res.next()){
                    ks2.setText(res.getString(2));
                }
            }
            if (i==3){
                ptst.setString(1, String.valueOf(dpid[i]));
                res = ptst.executeQuery();
                if(res.next()){
                    ks2.setText(res.getString(2));
                }
            }
        }
    }

}
