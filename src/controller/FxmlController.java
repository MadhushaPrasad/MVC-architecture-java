/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import tableModel.EmployeeTM;

/**
 * FXML Controller class
 *
 * @author chandrabanu
 */
public class FxmlController implements Initializable {

    @FXML
    private TableView<EmployeeTM> tblEmployee;
    @FXML
    private JFXTextField txtID;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtSalary;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        tblEmployee.getColumns().get(0).setStyle("-fx-alignment: CENTER;");
        tblEmployee.getColumns().get(1).setStyle("-fx-alignment: CENTER;");
        tblEmployee.getColumns().get(2).setStyle("-fx-alignment: CENTER;");
        tblEmployee.getColumns().get(3).setStyle("-fx-alignment: CENTER;");
        
        //----------------------------------------------------------------------
        
        tblEmployee.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("eid"));
        tblEmployee.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblEmployee.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblEmployee.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("salary"));
        
        try {
            loadAll();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FxmlController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    

    @FXML
    private void txtIDAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        
        //---------create database connecion-------------
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunday_class", "root", "mysql");
        
        //---------Get customer Details as a Result set and load to each text field-----------
        String sql="SELECT*FROM employee WHERE eid=?";
        PreparedStatement pstm= connection.prepareStatement(sql);
        pstm.setObject(1, txtID.getText());
        ResultSet rst = pstm.executeQuery();
        while (rst.next()) {
            txtName.setText(rst.getString(2));
            txtAddress.setText(rst.getString(3));
            txtSalary.setText(rst.getString(4));
        }
  
        //---------Focus next field----------------------
        txtName.requestFocus();
    }

    @FXML
    private void txtNameAction(ActionEvent event) {
        txtAddress.requestFocus();
    }

    @FXML
    private void txtAddressAction(ActionEvent event) {
        txtSalary.requestFocus();
    }

    @FXML
    private void txtSalaryAction(ActionEvent event) {
        btnSave.requestFocus();
    }

    @FXML
    private void btnSaveAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        //---------create database connecion-------------
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunday_class", "root", "mysql");
        
        //---------Save data into the database-----------
        String sql="INSERT INTO employee values(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, txtID.getText());
        pstm.setObject(2, txtName.getText());
        pstm.setObject(3, txtAddress.getText());
        pstm.setObject(4, Double.parseDouble(txtSalary.getText()));
        
        //---------Feedback-------------------------------
        int r= pstm.executeUpdate();
        if(r==1){
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Done", ButtonType.OK);
                a.show();
        }else{
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Failed", ButtonType.OK);
                a.show();
        }
        
        //---------clear text fields and focus next-----
        clearTxt();
        txtID.requestFocus();
        
        loadAll();
    }
    
    @FXML
    private void btnSaveActionKey(KeyEvent event) {
        
        //btnSaveAction(event);
    }

    @FXML
    private void btnUpdateAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        
        //---------create database connecion-------------
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunday_class", "root", "mysql");
        
        //---------Update database-----------------------
        String sql="UPDATE employee SET name=?, address=?, salary=? WHERE eid=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, txtName.getText());
        pstm.setObject(2, txtAddress.getText());
        pstm.setObject(3, Double.parseDouble(txtSalary.getText()));
        pstm.setObject(4, txtID.getText());
        
        //---------Feedback-------------------------------
        int r= pstm.executeUpdate();
        if(r==1){
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Done", ButtonType.OK);
                a.show();
        }else{
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Failed", ButtonType.OK);
                a.show();
        }
        
        //---------clear text fields and focus next-----
        clearTxt();
        txtID.requestFocus();
    }
    
    @FXML
    private void btnUpdateActionKey(KeyEvent event) {
        
        clearTxt();
        txtID.requestFocus();
    }

    @FXML
    private void btnDeleteAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        
        //---------create database connecion-------------
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunday_class", "root", "mysql");
        
        //---------Get customer Details as a Result set and load to each text field-----------
        String sql="DELETE FROM employee WHERE eid=?";
        PreparedStatement pstm= connection.prepareStatement(sql);
        pstm.setObject(1, txtID.getText());
        
        //---------Feedback-------------------------------
        int r= pstm.executeUpdate();
        if(r==1){
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Done", ButtonType.OK);
                a.show();
        }else{
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Failed", ButtonType.OK);
                a.show();
        }
        
        loadAll();
        
        //---------Focus next field----------------------
        txtID.requestFocus();
    
    }
    
    @FXML
    private void btnDeleteActionKey(KeyEvent event) {
        
        clearTxt();
        txtID.requestFocus();
    }
    
    void clearTxt(){
        txtID.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtSalary.setText("");
    }
    
    void loadAll() throws ClassNotFoundException, SQLException{
        
        String id;
        String name;
        String address;
        String salary;
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sunday_class", "root", "mysql");
        
        String sql="SELECT*FROM employee";
        PreparedStatement pstm= connection.prepareStatement(sql);
        ResultSet rst = pstm.executeQuery();
        //----------------------------------
        ArrayList<EmployeeTM> all= new ArrayList<>();
        while (rst.next()) {
            id= rst.getString(1);
            name= rst.getString(2);
            address= rst.getString(3);
            salary= rst.getString(4);
            
            EmployeeTM employeeTM= new EmployeeTM(id, name, address, salary);
            all.add(employeeTM);
        }
        //--------------------------------------
        
                //----------------------------------------
        tblEmployee.setItems(FXCollections.observableArrayList(all));
    }
 
}
