package stage;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pane.Myapinfxco;
import service.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Aginfxco {

    private String apid;

    private int a,b;

    @FXML
    void qd(ActionEvent event) throws SQLException {
        String sql = "DELETE FROM appointment WHERE appointment_id = ?";
        PreparedStatement ptst = Main.conn.prepareStatement(sql);
        System.out.println(apid);
        ptst.setString(1,apid);
        ptst.executeUpdate();
        sql = "SELECT count(*) FROM appointment WHERE patient_id = ? and is_completed = 0";
        ptst = null;
        try {
            ptst = Main.conn.prepareStatement(sql);
            ptst.setString(1, String.valueOf(Main.patient.getPatient_id()));
            ResultSet res = ptst.executeQuery();
            if(res.next()){
                Myapinfxco.m1.set(a,--b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Myapinfxco.astage.close();
        return;
    }

    @FXML
    void qx(ActionEvent event) {
        Myapinfxco.astage.close();
        return;
    }

    public void set(String apid,int a,int b){
        this.a=a;
        this.b=b;
        this.apid=apid;
        return;
    }
}
