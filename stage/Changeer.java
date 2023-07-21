package stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.Main;

import java.io.IOException;

public class Changeer extends Stage {
    Parent root;
    FXMLLoader loader;
    public Changeer() throws IOException {
        loader = new FXMLLoader(getClass().getResource("changeer.fxml"));
        root = FXMLLoader.load(getClass().getResource("changeer.fxml"));
        this.initModality(Modality.WINDOW_MODAL);
        this.initOwner(Main.pstage);
        this.setTitle("修改失败");
        this.setResizable(false);
        this.setScene(new Scene(root));
    }
    Changeerco refr(){
        Changeerco changeerco = loader.getController();
        return changeerco;
    }
}
