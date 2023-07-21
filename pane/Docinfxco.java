package pane;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.Main;
import stage.Loginerror;
import stage.Yvyuefxco;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Docinfxco {
    public static Stage yystage;
    private int index,sum,num;
    int id[] = new int[100];
    int di[]=new int[1000];
    @FXML
    private Label name1;

    @FXML
    private Label ks1;

    @FXML
    private Label ph1;

    @FXML
    private Label tt1;

    @FXML
    private Button bu1;

    @FXML
    private Label name2;

    @FXML
    private Label ks2;

    @FXML
    private Label ph2;

    @FXML
    private Label tt2;

    @FXML
    private Button bu2;

    @FXML
    private Label name3;

    @FXML
    private Label ks3;

    @FXML
    private Label ph3;

    @FXML
    private Label tt3;

    @FXML
    private Button bu3;

    @FXML
    private Label name4;

    @FXML
    private Label ks4;

    @FXML
    private Label ph4;

    @FXML
    private Label tt4;

    @FXML
    private Button bu4;

    @FXML
    private Label name5;

    @FXML
    private Label ks5;

    @FXML
    private Label ph5;

    @FXML
    private Label tt5;

    @FXML
    private Button bu5;

    @FXML
    void ap1(ActionEvent event) throws Exception {
        apo(id[1],di[1]);
    }

    @FXML
    void ap2(ActionEvent event) throws Exception {
        apo(id[2],di[2]);
    }

    @FXML
    void ap3(ActionEvent event) throws Exception {
        apo(id[3],di[3]);
    }

    @FXML
    void ap4(ActionEvent event) throws Exception {
        apo(id[4],di[4]);
    }

    @FXML
    void ap5(ActionEvent event) throws Exception {
        apo(id[5],di[5]);
    }

    @FXML
    public void setnum(int index, int sum, String dep, String title, String namelike) throws Exception {
        String sql;
        PreparedStatement ptst = null;
        this.index=index;
        this.sum=sum;
        num=5;
        if(index*5>sum){
            num=sum-(index-1)*5;
        }
        else if(sum<5){
            num=sum;
        }
        if(num<5){
            bu5.setDisable(true);
            bu5.setStyle("-fx-background-color: rgb(244,244,244);");
            if(num<4){
                bu4.setDisable(true);
                bu4.setStyle("-fx-background-color: rgb(233,233,233);");
                if(num<3){
                    bu3.setDisable(true);
                    bu3.setStyle("-fx-background-color: rgb(244,244,244);");
                    if(num<2){
                        bu2.setDisable(true);
                        bu2.setStyle("-fx-background-color: rgb(233,233,233);");
                        if(num<1){
                            bu1.setDisable(true);
                            bu1.setStyle("-fx-background-color: rgb(244,244,244);");
                        }
                    }
                }
            }
        }
        if (dep.equals("所属科室") && title.equals("职称") && namelike == null) {
            sql = "SELECT * FROM doctor";
            ptst = Main.conn.prepareStatement(sql);
        } else if (dep.equals("所属科室") && title.equals("职称") && namelike != null) {
            sql = "SELECT * FROM doctor WHERE name like ?";
            ptst = Main.conn.prepareStatement(sql);
            ptst.setString(1, "%" + namelike + "%");
        } else if (dep.equals("所属科室") && !title.equals("职称") && namelike == null) {
            sql = "SELECT * FROM doctor WHERE title = ?";
            ptst = Main.conn.prepareStatement(sql);
            ptst.setString(1, title);
        } else if (!dep.equals("所属科室") && title.equals("职称") && namelike == null) {
            sql = "SELECT * FROM doctor WHERE department_id = (SELECT department_id FROM department WHERE name = ?)";
            ptst = Main.conn.prepareStatement(sql);
            ptst.setString(1, dep);
        } else if (!dep.equals("所属科室") && title.equals("职称") && namelike != null) {
            sql = "SELECT * FROM doctor WHERE department_id = (SELECT department_id FROM department WHERE name = ?) AND name like ?";
            ptst = Main.conn.prepareStatement(sql);
            ptst.setString(1, dep);
            ptst.setString(2, "%" + namelike + "%");
        }else if (dep.equals("所属科室") && !title.equals("职称") && namelike != null) {
            sql = "SELECT * FROM doctor WHERE title = ? AND name like ?";
            ptst = Main.conn.prepareStatement(sql);
            ptst.setString(2, title);
            ptst.setString(2, "%" + namelike + "%");
        } else if (!dep.equals("所属科室") && !title.equals("职称") && namelike == null) {
            sql = "SELECT * FROM doctor WHERE department_id = (SELECT department_id FROM department WHERE name = ?) AND title = ?";
            ptst = Main.conn.prepareStatement(sql);
            ptst.setString(1, dep);
            ptst.setString(2, title);
        } else if (!dep.equals("所属科室") && !title.equals("职称") && namelike != null) {
            sql = "SELECT * FROM doctor WHERE department_id = (SELECT department_id FROM department WHERE name = ?) AND title = ? AND name like ?";
            ptst = Main.conn.prepareStatement(sql);
            ptst.setString(1, dep);
            ptst.setString(2, title);
            ptst.setString(3, "%" + namelike + "%");
        }
        ResultSet res = ptst.executeQuery();
        if (!res.next())
            return;
        int i;
        for(i=1;i<(index-1)*5;i++){
            res.next();
        }
        for (i=1;i<=num;i++,res.next()){
            if(i==1){
                id[i]= res.getInt(1);
                di[i]= res.getInt(4);
                name1.setText(res.getString(2));
                ph1.setText(res.getString(5));
                tt1.setText(res.getString(3));
                bu1.setText("预约");

            }
            if(i==2){
                id[i]= res.getInt(1);
                di[i]= res.getInt(4);
                name2.setText(res.getString(2));
                ph2.setText(res.getString(5));
                tt2.setText(res.getString(3));
                bu2.setText("预约");
            }
            if(i==3){
                id[i]= res.getInt(1);
                di[i]= res.getInt(4);
                name3.setText(res.getString(2));
                ph3.setText(res.getString(5));
                tt3.setText(res.getString(3));
                bu3.setText("预约");
            }
            if(i==4){
                id[i]= res.getInt(1);
                di[i]= res.getInt(4);
                name4.setText(res.getString(2));
                ph4.setText(res.getString(5));
                tt4.setText(res.getString(3));
                bu4.setText("预约");
            }
            if(i==5){
                id[i]= res.getInt(1);
                di[i]= res.getInt(4);
                name5.setText(res.getString(2));
                ph5.setText(res.getString(5));
                tt5.setText(res.getString(3));
                bu5.setText("预约");
            }
        }
        for(i=1;i<=num;i++){
            sql ="select name from department where department_id = ? ";
            ptst = Main.conn.prepareStatement(sql);
            ptst.setString(1, String.valueOf(di[i]));
            res = ptst.executeQuery();
            while (res.next()) {
                if(i==1)
                    ks1.setText(res.getString(1));
                if(i==2)
                    ks2.setText(res.getString(1));
                if(i==3)
                    ks3.setText(res.getString(1));
                if(i==4)
                    ks4.setText(res.getString(1));
                if(i==5)
                    ks5.setText(res.getString(1));

            }
        }
    }
    void apo(int doid,int dpid) throws SQLException, IOException {
        String sql = "SELECT COUNT(*) FROM appointment WHERE patient_id = ? AND is_completed = 0";
        PreparedStatement ptst = Main.conn.prepareStatement(sql);
        ptst.setString(1, String.valueOf(Main.patient.getPatient_id()));
        ResultSet res = ptst.executeQuery();
        if(res.next()){
            if(res.getInt(1)>0){//展示预约时失败界面
                Parent root = FXMLLoader.load(getClass().getResource("../stage/error.fxml"));
                Platform.runLater(()->{
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.setTitle("预约失败");
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(Main.pstage);
                    stage.show();
                });
                return;
            }
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../stage/yvyuefx.fxml"));
        Pane root = loader.load();
        Yvyuefxco yvyuefxco = loader.getController();
        yvyuefxco.setdodp(doid,dpid);
        Platform.runLater(()->{
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("预约");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(Main.pstage);
            yystage=stage;
            stage.show();
        });
    }
}
