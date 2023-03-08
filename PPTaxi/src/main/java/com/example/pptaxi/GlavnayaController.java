package com.example.pptaxi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class GlavnayaController {

    @FXML
    private TextField numtf;

    @FXML
    private TextField tfFIO;

    @FXML
    private TextField tfVoz;

    @FXML
    private TextField tfSt;

    @FXML
    private TextField tfNumSer;

    @FXML
    private TextField tfMaN;

    @FXML
    private TextField tfGosN;

    @FXML
    private Button btnOtpr;

    @FXML
    private Button btnOtprZ;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnAuth;

    @FXML
    private Pane pGlav;

    @FXML
    private Label wtext;

    @FXML
    void initialize() {

        btnOtpr.setOnAction(event -> {
            pGlav.setVisible(true);
        });

        btnBack.setOnAction(event -> {
            pGlav.setVisible(false);
        });

        btnAuth.setOnAction(event -> {
            btnAuth.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("authorization.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent room = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(room));
            stage.show();
        });

        btnOtprZ.setOnAction(event -> {
            if (!(tfFIO.getText().isEmpty() || tfVoz.getText().isEmpty()
                    || tfSt.getText().isEmpty() || tfNumSer.getText().isEmpty()
                    || tfMaN.getText().isEmpty() || tfGosN.getText().isEmpty() || numtf.getText().isEmpty())
                    && (isNumeric(numtf.getText()) || numtf.getText().length() == 11) && (isNumeric(tfNumSer.getText()) || tfNumSer.getText().length() == 10)) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                    try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/taxi",
                            "root", "MySQL")) {
                        PreparedStatement statement = conn.prepareStatement
                                ("INSERT into spvod(FIO, Voz, St, NumSer, MaN, GosN, Num) VALUES(?,?,?,?,?,?,?)");
                        statement.setString(1, tfFIO.getText());
                        statement.setString(2, tfVoz.getText());
                        statement.setString(3, tfSt.getText());
                        statement.setString(4, tfNumSer.getText());
                        statement.setString(5, tfMaN.getText());
                        statement.setString(6, tfGosN.getText());
                        statement.setString(7, numtf.getText());
                        statement.executeUpdate();

                    }
                } catch (Exception e) {
                    System.out.println("Ошибка с заполнением");
                }
            } else wtext.setText("Данные в полях некорректны");
        });

    }

    private boolean isNumeric(String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

}