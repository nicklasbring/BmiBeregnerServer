package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private int port = 8000;
    private String serverIP = "localhost";
    private DataInputStream input;
    private DataOutputStream output;
    private double hight;
    private double weight;

    public Client() {
        try {
            Socket socket = new Socket(serverIP, port);

            output = new DataOutputStream(socket.getOutputStream());
            input = new DataInputStream(socket.getInputStream());

            Scanner scn = new Scanner(System.in);

            System.out.println("Indtast din højde i cm: ");
            hight = scn.nextDouble();
            if (Double.isNaN(hight)){
                System.out.println("indtast venligst et tal");
            }
            else{
                output.writeDouble(hight);
            }

            System.out.println("Indtast din vægt i kilo: ");
            weight = scn.nextDouble();
            if (Double.isNaN(weight)){
                System.out.println("indtast venligst et tal");
            }
            else {
                output.writeDouble(weight);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Client client = new Client();
    }
}
