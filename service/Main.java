package service;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import po.Patient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {
    public static Patient patient;
    public static Connection conn;
    public static Stage pstage;
    public static void main(String[] args) throws Exception{
        launch(args);
    }

    @Override
    public void init() throws Exception {   //提前建立数据库连接
        super.init();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1","root","");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接失败！");
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {    //提前搭建主舞台
        Parent root = FXMLLoader.load(getClass().getResource("hellp.fxml"));
        pstage=primaryStage;
        primaryStage.setResizable(false);
        primaryStage.setTitle("智慧医务系统");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {       //关闭一系列事件
        conn.close();
        super.stop();
    }
}
