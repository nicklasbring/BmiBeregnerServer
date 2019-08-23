package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Controller{

    @FXML
    TextArea textArea_calculation;

    private int port = 8000;
    private ServerSocket server;
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private double height;
    private double weight;

    public void initialize(){
        textArea_calculation.setEditable(false);

        try {
            server = new ServerSocket(port);
            System.out.println("Serveren kører!");
            socket = server.accept();
            System.out.println("Klient tilsluttet");

            output = new DataOutputStream(socket.getOutputStream());
            input = new DataInputStream(socket.getInputStream());

            height = input.readDouble();
            weight = input.readDouble();

            double BMI = weight / Math.pow((height / 100), 2);

            String underWeightOverWeight;
            if (BMI < 18.5){
                underWeightOverWeight = "undervægtig";
            } else if (BMI < 25) {
                underWeightOverWeight = "normalvægtig";
            } else if (BMI < 30) {
                underWeightOverWeight = "overvægtig";
            } else if (BMI < 40) {
                underWeightOverWeight = "svært overvægtig";
            } else {
                underWeightOverWeight = "ekstremt overvægtig";
            }

            textArea_calculation.appendText("heigh = " + height + "\nWidth = " + weight + "\nDit BMI er: " +
                    BMI + "\nBMI svarer til at være " + underWeightOverWeight);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
