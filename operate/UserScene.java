package operate;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import service.Main;

import java.io.IOException;

public class UserScene {    //      用户操作界面
    public static void bulidoperate() throws IOException {
        Parent root = FXMLLoader.load(UserScene.class.getResource("scene.fxml"));
        Scene scene = new Scene(root);
        root.getStylesheets().add(UserScene.class.getResource("styles.css").toExternalForm());
        Main.pstage.setScene(scene);
        Main.pstage.show();
    }
}
