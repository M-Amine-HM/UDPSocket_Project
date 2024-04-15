package serverPackage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server {

    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(1234);
            System.out.println("Serveur UDP en attente...");

            //EX1 : dans cet exercice Le serveur  répond le client par un message de la forme « Bonjour Prénom Nom LCS-GLSI3_X.Y ! »

            // Attendre et afficher le message reçu du client

            // Création d'un tableau de bytes pour stocker les données
            byte[] receiveData = new byte[1024];
            // Création d'un paquet pour recevoir les données
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            // Réception des données
            serverSocket.receive(receivePacket);
            // Extraction des données du paquet
            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            // Affichage du message reçu
            System.out.println("TP4 EX1 : \nMessage reçu du client : " + receivedMessage);

            // Envoyer un message de réponse au client
            String responseMessage = "Bonjour "+receivedMessage;
            byte[] sendData = responseMessage.getBytes();
            // Création d'un paquet à envoyer en réponse
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
            serverSocket.send(sendPacket);

            //EX2 : dans cet exercice le serveur met en majuscule le message envoyé par le client
            // reçu du client
            receiveData = new byte[1024];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

            System.out.println("TP4 EX2 : \nMessage reçu du client : " + receivedMessage);

            //met le message en majuscule et l'envoyer
            responseMessage = receivedMessage.toUpperCase();
            sendData = responseMessage.getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
            serverSocket.send(sendPacket);


            //EX3 : dans cet exercice Le serveur envoie la date et l’heure courante
            // apres la reçu de la question de client
            receiveData = new byte[1024];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

            System.out.println("TP4 EX3 : \nMessage reçu du client : " + receivedMessage);

            //envoyer au client

            // Obtention de la date et de l'heure actuelles
            //LocalDateTime est une classe qui représente une date et une heure sans information de fuseau horaire
            LocalDateTime dateHeureCourante = LocalDateTime.now();

            // Formatage de la date et de l'heure
            //DateTimeFormatter est utilisé pour formater et analyser les dates et heures en chaînes de caractères.
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String dateHeureFormatee = dateHeureCourante.format(formatter);

            // Affichage de la date et de l'heure actuelles
           // System.out.println("Date et heure courantes : " + dateHeureFormatee);
            responseMessage = dateHeureFormatee;
            sendData = responseMessage.getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
            serverSocket.send(sendPacket);

            // Fermer la socket du serveur
            serverSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}