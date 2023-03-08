package com.example.pptaxi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class WinSotrContr {

    @FXML
    private Button btnSave;

    @FXML
    private Button btnOtm;

    @FXML
    private Button btnOtmizm;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnIzm;

    @FXML
    private Button btnDel;

    @FXML
    private Pane pAddVod;

    @FXML
    private Pane pIzmVod;

    @FXML
    private TextField tfFIOizm;

    @FXML
    private TextField tfVozizm;

    @FXML
    private TextField tfStizm;

    @FXML
    private TextField tfNumSerizm;

    @FXML
    private TextField tfMNizm;

    @FXML
    private TextField tfGosizm;

    @FXML
    private TextField tfNumizm;

    @FXML
    private TextField tfFIOSp;

    @FXML
    private TextField tfVozSp;

    @FXML
    private TextField tfStSp;

    @FXML
    private TextField tfNumSerSp;

    @FXML
    private TextField tfMNSp;

    @FXML
    private TextField tfGosSp;

    @FXML
    private TextField tfNumSp;

    @FXML
    private Label wtext;

    @FXML private TableView<DBtable> tableSpVod;

    @FXML private TableColumn<DBtable, Integer> CodeID;
    @FXML
    private TableColumn<DBtable, String> TbFIO;
    @FXML
    private TableColumn<DBtable, String> TbVoz;
    @FXML
    private TableColumn<DBtable, String> TbSt;
    @FXML
    private TableColumn<DBtable, String> TbNS;
    @FXML
    private TableColumn<DBtable, String> TbMN;
    @FXML
    private TableColumn<DBtable, String> TbGosN;
    @FXML
    private TableColumn<DBtable, String> TbNum;

    private DBtable selectedTable1 = new DBtable();

    private final ObservableList<DBtable> tableSpVodDat = FXCollections.observableArrayList();

    public int CodeZ;

    @FXML
    void initialize() {

        CodeID.setCellValueFactory(new PropertyValueFactory<>("CodeID"));
        TbFIO.setCellValueFactory(new PropertyValueFactory<>("TbFIO"));
        TbVoz.setCellValueFactory(new PropertyValueFactory<>("TbVoz"));
        TbSt.setCellValueFactory(new PropertyValueFactory<>("TbSt"));
        TbNS.setCellValueFactory(new PropertyValueFactory<>("TbNS"));
        TbMN.setCellValueFactory(new PropertyValueFactory<>("TbMN"));
        TbGosN.setCellValueFactory(new PropertyValueFactory<>("TbGosN"));
        TbNum.setCellValueFactory(new PropertyValueFactory<>("TbNum"));

        tablerefresh();

        btnAdd.setOnAction(event -> {
            pAddVod.setVisible(true);
        });

        btnOtm.setOnAction(event -> {
            pAddVod.setVisible(false);
        });

        btnIzm.setOnAction(event -> {
            pIzmVod.setVisible(true);
            selectedTable1 = tableSpVod.getSelectionModel().getSelectedItem();
            izmenTable(selectedTable1.getIDCode());
            CodeZ = selectedTable1.getIDCode();
        });

        btnOtmizm.setOnAction(event -> {
            pIzmVod.setVisible(false);
        });

        btnDel.setOnAction(event ->{
            selectedTable1 = tableSpVod.getSelectionModel().getSelectedItem();
            selectedTable1.getIDCode();
            deleteTable(selectedTable1.getIDCode());
            tableSpVod.getItems().remove(selectedTable1);
        });

        btnBack.setOnAction(event -> {
            btnBack.getScene().getWindow().hide();

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

        btnSave.setOnAction(event -> {
            if (!(tfFIOSp.getText().isEmpty() || tfVozSp.getText().isEmpty() || tfStSp.getText().isEmpty()
                    || tfNumSerSp.getText().isEmpty() || tfMNSp.getText().isEmpty()
                    || tfGosSp.getText().isEmpty() || tfNumSp.getText().isEmpty())) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                    try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/taxi",
                            "root", "MySQL")) {
                        PreparedStatement statement = conn.prepareStatement
                                ("INSERT into spvod(FIO, Voz, St, NumSer, MaN, GosN, Num) VALUES(?,?,?,?,?,?,?)");
                        statement.setString(1, tfFIOSp.getText());
                        statement.setString(2, tfVozSp.getText());
                        statement.setString(3, tfStSp.getText());
                        statement.setString(4, tfNumSerSp.getText());
                        statement.setString(5, tfMNSp.getText());
                        statement.setString(6, tfGosSp.getText());
                        statement.setString(7, tfNumSp.getText());
                        statement.executeUpdate();
                        tablerefresh();

                    }
                } catch (Exception e) {
                    System.out.println("Ошибка с заполнением");
                }
            } else wtext.setText("Данные в полях некорректны");
        });

    }

    public static void deleteTable(int Code) {
        String zayav = "DELETE FROM statement WHERE 'id_spvod' = " + Code;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/taxi",
                    "root", "MySQL");
            System.out.println ("Подключение к бд");
            PreparedStatement preparedStatement = conn.prepareStatement(zayav);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Ошибка с удалением");
        }
    }

    public void izmenTable(int Code) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/taxi",
                "root", "MySQL")) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM spvod WHERE id_spvod = " + Code );
            while (resultSet.next()) {
                tfFIOizm.setText(resultSet.getString("FIO"));
                tfVozizm.setText(resultSet.getString("Voz"));
                tfStizm.setText(resultSet.getString("St"));
                tfNumSerizm.setText(resultSet.getString("NumSer"));
                tfMNizm.setText(resultSet.getString("MaN"));
                tfGosizm.setText(resultSet.getString("GosN"));
                tfNumizm.setText(resultSet.getString("Num"));
            }
        } catch (Exception e) {
            System.out.println("Ошибка с изменением в текстфилд");
        }
    }

    private void tablerefresh() {
        tableSpVodDat.clear();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/taxi",
                    "root", "MySQL")) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM spvod");
                while (resultSet.next()) {
                    tableSpVodDat.add(new DBtable(
                            resultSet.getInt("id_spvod"),
                            resultSet.getString("FIO"),
                            resultSet.getString("Voz"),
                            resultSet.getString("St"),
                            resultSet.getString("NumSer"),
                            resultSet.getString("MaN"),
                            resultSet.getString("GosN"),
                            resultSet.getString("Num")
                    ));
                }
            }
            if (!tableSpVodDat.isEmpty()) {
                tableSpVod.setItems(tableSpVodDat);
            }
        } catch (Exception e) {
            System.out.println("Ошибка с таблицей");
        }
    }

}
