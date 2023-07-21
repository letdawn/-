package pane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import service.Main;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Myapofxco {

    @FXML
    private Pagination pa;

    @FXML
    public void initialize() throws SQLException {
        pa.setMaxPageIndicatorCount(6);
        String sql = "SELECT count(*) FROM appointment WHERE patient_id = ?";
        PreparedStatement ptst = Main.conn.prepareStatement(sql);
        ptst.setString(1, String.valueOf(Main.patient.getPatient_id()));
        ResultSet res = ptst.executeQuery();
        int count = 0;
        if(res.next()){
            count = res.getInt(1);
        }
        if(count%5==0){
            pa.setPageCount(count/5);
        }
        else
            pa.setPageCount(count/5+1);
        int finalCount1 = count;
        pa.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer param) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("myapinfx.fxml"));
                try {
                    Pane root = loader.load();
                    Myapinfxco myapinfxco = loader.getController();
                    myapinfxco.set(param.intValue()+1, finalCount1);
                    return root;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
}
