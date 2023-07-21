package stage;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pane.Docinfxco;
import service.Main;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class Yvyuefxco {

    final String[] datan = new String[1];
    private int doid,dpid;

    @FXML
    private ChoiceBox<String> data;

    @FXML
    void ap(ActionEvent event) throws SQLException, IOException {
        String sql = "INSERT INTO appointment ( appointment_id, patient_id, doctor_id, appointment_datetime, department_id, is_completed )VALUES ( ? , ? , ? , ? , ? , 0 )";
        PreparedStatement ptst = Main.conn.prepareStatement(sql);
        ptst.setString(1,String.valueOf(Main.patient.getPatient_id())+String.valueOf(doid)+datan[0]);
        ptst.setString(2, String.valueOf(Main.patient.getPatient_id()));
        ptst.setString(3, String.valueOf(doid));
        ptst.setString(4, datan[0]);
        ptst.setString(5,String.valueOf(dpid));
        int rowsAffected = ptst.executeUpdate();
        if (rowsAffected > 0) {
            Parent root = FXMLLoader.load(getClass().getResource("../stage/sucfx.fxml"));
            Platform.runLater(()->{
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setTitle("预约成功");
                stage.setScene(new Scene(root));
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(Docinfxco.yystage);
                stage.show();
            });
            Docinfxco.yystage.close();
        }
    }

    @FXML
    void canle(ActionEvent event) {
        Docinfxco.yystage.close();
    }

    @FXML
    public void initialize(){
        LocalDate localDate = LocalDate.now();
        String data1 = String.valueOf(localDate);
        String data2 = String.valueOf(localDate.plusDays(1));
        String data3 = String.valueOf(localDate.plusDays(2));
        ObservableList<String> options = FXCollections.observableArrayList(data1,data2,data3);
        datan[0]=data1;
        data.setItems(options);
        data.setValue(data1);
        data.setOnAction(event -> {
            datan[0] =data.getValue();
        });
    }

    public void setdodp(int doid,int dpid){
        this.doid=doid;
        this.dpid=dpid;
    }
}
