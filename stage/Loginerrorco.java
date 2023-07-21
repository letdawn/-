package stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import service.Controller;

public class Loginerrorco {     //登陆失败-舞台控制器

    @FXML
    private Button loginreturn;     //返回按钮
    @FXML
    void loginreturn1(ActionEvent event) {
        Controller.loginer.close();
    }  //点击返回事件

}