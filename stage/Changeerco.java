package stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pane.Plnfxco;
import service.Controller;

public class Changeerco {     //登陆失败-舞台控制器

    @FXML
    public Label o;
    @FXML
    private Button loginreturn;     //返回按钮
    @FXML
    void loginreturn1(ActionEvent event) {
        Plnfxco.changeer.close();
    }  //点击返回事件

}