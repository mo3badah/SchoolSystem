package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main extends Application {
    // Adding text fields
    TextField name = new TextField();
    TextField mail = new TextField();
    TextField phone = new TextField();
    String mainly = "mainly.txt";
    String fork = "fork.txt";
    Text marksheet = new Text(400, 300, "welcome ");
    Text alert = new Text("alert");
    Font f2 =new Font(16);
    static boolean showing = false;

    @Override
    public void start(Stage primaryStage) throws Exception {


        //Adding Buttons
        Button add = new Button("Add");
        Button search = new Button("Search");
        Button modify = new Button("Modify");
        Button delete = new Button("Delete");
        Button mark = new Button("Mark Sheet");
        //Adding labels
        Label nm = new Label(" Name ");
        Label em = new Label(" Email");
        Label tel = new Label(" Phone ");
        Label greats = new Label("Welcome \n you can add anew student just fill all fields \n search , delet , modify just enter by one field only ");

        //Using HBox and VBox
        HBox hBox1 = new HBox(30);
        HBox hBox2 = new HBox(30);
//        VBox hBox3 = new VBox(30);
        VBox vBox1 = new VBox(30);
        VBox vBox2 = new VBox(30);
        VBox vBox3 = new VBox(30);


        // Add Elements

        hBox2.getChildren().addAll(greats, mark);
        hBox1.getChildren().addAll(add, search, modify, delete);
//        hBox3.getChildren().addAll(alert);
        vBox1.getChildren().addAll(nm, em, tel);
        vBox2.getChildren().addAll(name, mail, phone, alert);
        vBox3.getChildren().addAll(marksheet);

        // Stablish pane and add controls to it
        BorderPane pan = new BorderPane();
        pan.setTop(hBox2);
        pan.setBottom(hBox1);
//        pan.setBottom(hBox3);
        pan.setLeft(vBox1);
        pan.setCenter(vBox2);
        pan.setRight(vBox3);


        //pading
        pan.setPadding(new Insets(15));
        vBox1.setSpacing(40);
        greats.setTextAlignment(TextAlignment.CENTER);
        hBox1.setAlignment(Pos.CENTER);
        marksheet.setTextAlignment(TextAlignment.CENTER);
        hBox1.setSpacing(30);
        greats.setMinSize(400, 60);
        greats.setFont(f2);
        marksheet.setFont(f2);
        marksheet.setFill(Color.BLUE);
        marksheet.maxWidth(300);
        alert.setFill(Color.RED);

        // Our Buttons
        // Adding button to add elements and save it in files
        add.setOnAction(event -> Addnewstudent());
        search.setOnAction(event -> SearchStudents());
        modify.setOnAction(event -> ModifyStudent());
        delete.setOnAction(event -> DeleteStudents());
        mark.setOnAction(event -> ShowStudents());




        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Student Recorder System");
        Scene scene = new Scene(pan, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    void SearchStudents() {
        // save data from textfields
        String aname = name.getText();
        String amail = mail.getText();
        String aphone = phone.getText();
        boolean exist = false;
        //add afile
        File storing = new File(mainly);
        //write in the file with try and catch
        try {//to override exceptions
            PrintWriter pri = new PrintWriter(new FileOutputStream(new File(mainly), true));//to write in main file
            Scanner Scanner = new Scanner(new File(mainly));// to read from our file
            Scanner.useDelimiter("[*\n]");//to spacific saving data
            while (Scanner.hasNext()) {//loop to search
                String isname = Scanner.next();
                String ismail = Scanner.next();
                String isphone = Scanner.next();
                if (aname.equals(isname) || amail.equals(ismail) || aphone.equals(isphone)) {//searching
                    exist = true;
                    alert.setText("student name :" + isname + "  ,mail :" + ismail + "  ,Telephone :" + isphone + " \n has been Founded successfully");
                }
            }
            if (!exist) {//if not founded
                alert.setText("student name :" + aname + "  ,mail :" + amail + "  ,Telephone :" + aphone + " \n   has Not Founded   \n Try again.......");
            }
        } catch (Exception e) {
            alert.setText("there is an error");
        }
        ClearFields();//clear fields
    }


    void Addnewstudent() {
        // save data from textfields
        String aname = name.getText();
        String amail = mail.getText();
        String aphone = phone.getText();
        boolean exist = false;
        //add afile
        File storing = new File(mainly);
        //write in the file with try and catch
        try {
            PrintWriter pri = new PrintWriter(new FileOutputStream(new File(mainly), true));//to write in main file
            Scanner Scanner = new Scanner(new File(mainly));// to read from our file
            Scanner.useDelimiter("[*\n]");//to spacific saving data
            while (Scanner.hasNext()) {//loop to search if exist
                String isname = Scanner.next();
                String ismail = Scanner.next();
                String isphone = Scanner.next();
                if (aname.equals(isname) || amail.equals(ismail) || aphone.equals(isphone)) {
                    exist = true;
                }

            }
            if (aname.equals("") || amail.equals("") || aphone.equals("")) {//to ignore null data
                alert.setText("please enter all fields to be added");
            } else if (exist) {////to ignore exist data
                alert.setText("Repeated data enter anew one");
            } else {//to add to main
                pri.println(aname + "*" + amail + "*" + aphone);
                pri.flush();
                pri.close();
                alert.setText("student name :" + aname + "  ,mail :" + amail + "  ,Telephone :" + aphone + " \n has been Added successfully");//message
            }
            ClearFields();////clear fields


        } catch (Exception e) {
            alert.setText("there is an error");
        }


    }

    void ModifyStudent() {
        // save data from textfields
        String aname = name.getText();
        String amail = mail.getText();
        String aphone = phone.getText();
        boolean exist = false;
        //add afile
        File storing = new File(mainly);
        File temp = new File(fork);
        //write in the file with try and catch
        try {
            PrintWriter pri = new PrintWriter(new FileOutputStream(new File(fork), true));//to write in alternative file
            Scanner Scanner = new Scanner(new File(mainly));// to read from main file
            Scanner.useDelimiter("[*\n]");//to spacific saving data
            while (Scanner.hasNext()) {//loop to search
                String isname = Scanner.next();
                String ismail = Scanner.next();
                String isphone = Scanner.next();
                if (aname.equals(isname) || amail.equals(ismail) || aphone.equals(isphone)) {//if is wanted one replace old with new in the alternative file
                    exist = true;
                    pri.println(aname + "*" + amail + "*" + aphone);
                    alert.setText("student name :" + isname + "  ,mail :" + ismail + "  ,Telephone :" + isphone + " \n has been Modified successfully");//alert message

                } else {//if not wanted one save them in the alternative file
                    pri.println(isname + "*" + ismail + "*" + isphone);
                }

            }
            if (!exist) {//if not matched data try again
                alert.setText("student name :" + aname + "  ,mail :" + amail + "  ,Telephone :" + aphone + " \n   has Not Founded   \n Try again.......");
            }
            Scanner.close();//close reading from main
            pri.flush();//save data writed in alternative file
            pri.close();//close writing in alternative file
            storing.delete();//delete main file(error from ide )
            File flash = new File(mainly);//new file for renaming
            temp.renameTo(flash);//rename alternative file as the main one for other process

        } catch (Exception e) {
            alert.setText("there is an error");
        }
        ClearFields();//clear fields
    }

    void DeleteStudents() {
        // save data from textfields
        String aname = name.getText();
        String amail = mail.getText();
        String aphone = phone.getText();
        boolean exist = false;
        //add afile
        File storing = new File(mainly);
        File temp = new File(fork);
        //write in the file with try and catch
        try {
            PrintWriter pri = new PrintWriter(new FileOutputStream(new File(fork), true));//to write in alternative file
            Scanner Scanner = new Scanner(new File(mainly));// to read from our file// to read from main file
            Scanner.useDelimiter("[*\n]");//to spacific saving data
            while (Scanner.hasNext()) {//loop to search
                String isname = Scanner.next();
                String ismail = Scanner.next();
                String isphone = Scanner.next();
                if (aname.equals(isname) || amail.equals(ismail) || aphone.equals(isphone)) {//if is wanted one dont save in the alternative file
                    exist = true;
                    alert.setText("student name :" + isname + "  ,mail :" + ismail + "  ,Telephone :" + isphone + " \n has been Deleted successfully");
                } else {//if not wanted one save them in the alternative file
                    pri.println(isname + "*" + ismail + "*" + isphone);
                }

            }
            if (!exist) {//if not matched data try again
                alert.setText("student name :" + aname + "  ,mail :" + amail + "  ,Telephone :" + aphone + " \n   has Not Founded   \n Try again.......");
            }
            Scanner.close();//close reading from main
            pri.flush();//save data writed in alternative file
            pri.close();//close writing in alternative file
            storing.delete();//delete main file(error from ide )
            File flash = new File(mainly);//new file for renaming
            temp.renameTo(flash);//rename alternative file as the main one for other process

        } catch (Exception e) {
            alert.setText("there is an error");
        }

        ClearFields();//clear fields
    }
    void ShowStudents(){
        if (!showing){//to enable and desable showing
        File storing = new File(mainly);//mke afile is not founded
        String marks="";//store some data tempory
        try {
            Scanner Scanner = new Scanner(new File(mainly));// to read from our file
            Scanner.useDelimiter("[*\n]");//to spacific saving data
            while (Scanner.hasNext()) {//saving data
                String isname = Scanner.next();
                String ismail = Scanner.next();
                String isphone = Scanner.next();
                marks+="name :" + isname + "    mail :" + ismail + "  phone :" + isphone +"\n" ;//save in it
                marksheet.setText(marks);//put in screen

        }
            showing=true;//if is shown enabled

        }catch (Exception e) {
            alert.setText("there is an error");}
    }else {marksheet.setText("");//stop showing
            showing=false;//if is shown enabled
            }}

    void ClearFields() {
        name.setText("");
        mail.setText("");
        phone.setText("");
    }


//    public boolean isuniqe(String x,String y,String z)


    public static void main(String[] args) {
        launch(args);
    }}


