package pane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import service.Main;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Appoifxco {

    final String[] dep = new String[1];
    final String[] title = new String[1];
    String namelike;
    @FXML
    private Pagination pa;

    @FXML
    private Pane top;

    @FXML
    private ChoiceBox<String> kscho;

    @FXML
    private TextField text1;

    @FXML
    void search1(ActionEvent event) throws SQLException {
        namelike =text1.getText();
        ref(dep[0],title[0],namelike);
    }

    @FXML
    private ChoiceBox<String> ttcho;

    @FXML
    public void initialize() throws SQLException {
        dep[0]="所属科室";
        title[0] = "职称";
        ref(dep[0], title[0], namelike);
        ObservableList<String> options = FXCollections.observableArrayList("所属科室", "心脏科", "皮肤科","内分泌科","胃肠科","神经科","眼科","儿科","精神科","外科","耳鼻喉科");
        ObservableList<String> options2=FXCollections.observableArrayList("职称","医士","住院医师","主治医师","副主任医师","主任医师");
        ttcho.setItems(options2);
        ttcho.setValue("职称");
        kscho.setItems(options);
        kscho.setValue("所属科室");
        kscho.setOnAction(event -> {
            dep[0] = kscho.getValue();
            title[0] = ttcho.getValue();
            try {
                ref(dep[0],title[0], namelike);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        ttcho.setOnAction(event -> {
            dep[0] = kscho.getValue();
            title[0] = ttcho.getValue();
            try {
                ref(dep[0],title[0], namelike);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
    void ref(String dep, String title, String namelike) throws SQLException {
        String sql;
        PreparedStatement ptst;
        pa.setMaxPageIndicatorCount(6);
        if (dep.equals("所属科室") && title.equals("职称") && namelike == null) {
            sql = "select count(*) from doctor";
            ptst = Main.conn.prepareStatement(sql);
        } else if (!dep.equals("所属科室") && title.equals("职称") && namelike == null) {
            sql = "select count(*) from doctor where department_id=(select department_id from department where name = ?)";
            ptst = Main.conn.prepareStatement(sql);
            ptst.setString(1, dep);
        } else if (dep.equals("所属科室") && !title.equals("职称") && namelike == null) {
            sql = "select count(*) from doctor where title = ?";
            ptst = Main.conn.prepareStatement(sql);
            ptst.setString(1, title);
        } else if (dep.equals("所属科室") && title.equals("职称") && namelike != null) {
            sql = "select count(*) from doctor where name like ?";
            ptst = Main.conn.prepareStatement(sql);
            ptst.setString(1, "%" + namelike + "%");
        } else if (!dep.equals("所属科室") && title.equals("职称") && namelike != null) {
            sql = "select count(*) from doctor where department_id=(select department_id from department where name = ?) and name like ?";
            ptst = Main.conn.prepareStatement(sql);
            ptst.setString(1, dep);
            ptst.setString(2, "%" + namelike + "%");
        } else if (dep.equals("所属科室") && !title.equals("职称") && namelike != null) {
            sql = "select count(*) from doctor where title = ? and name like ?";
            ptst = Main.conn.prepareStatement(sql);
            ptst.setString(1, title);
            ptst.setString(2, "%" + namelike + "%");
        }else if (!dep.equals("所属科室") && !title.equals("职称") && namelike == null) {
            sql = "select count(*) from doctor where title = ? and  department_id=(select department_id from department where name = ?)";
            ptst = Main.conn.prepareStatement(sql);
            ptst.setString(1, title);
            ptst.setString(2, dep);
        }
        else {
            sql = "select count(*) from doctor where department_id=(select department_id from department where name = ?) and title = ? and name like ?";
            ptst = Main.conn.prepareStatement(sql);
            ptst.setString(1, dep);
            ptst.setString(2, title);
            ptst.setString(3, "%" + namelike + "%");
        }
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
        int finalCount = count;
        pa.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer param) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("docinfx.fxml"));
                try {
                    Pane root = loader.load();
                    Docinfxco docinfxco = loader.getController();
                    docinfxco.setnum(param.intValue()+1,finalCount,dep,title,namelike);
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
