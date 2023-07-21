package stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.Main;

import java.io.IOException;

public class Loginerror extends Stage {     //登陆失败-舞台参数
    public Loginerror() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        this.initModality(Modality.WINDOW_MODAL);
        this.initOwner(Main.pstage);
        this.setTitle("登陆失败");
        this.setResizable(false);
        this.setScene(new Scene(root));
    }
}
