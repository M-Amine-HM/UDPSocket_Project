package clientPackage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {


    public static void main(String[] args) {
        try {
            // Créer une socket UDP pour le client
            DatagramSocket clientSocket = new DatagramSocket();

            // Adresse IP du serveur
            InetAddress serverAddress = InetAddress.getByName("localhost");

            //EX1 : dans cet execice Le client envoie le message de la forme « Prénom Nom LCS-GLSI3_X.Y » au serveur.
            // Message à envoyer
            String messageToSend = "Mohamed Amine Haj Mohamed GLSI C";
            byte[] sendData = messageToSend.getBytes();
            // Création d'un paquet à envoyer
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 1234);

            // Envoyer le message au serveur
            clientSocket.send(sendPacket);

            // Attendre et afficher la réponse du serveur
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            // Extraction des données de la réponse
            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            // Affichage de la réponse du serveur
            System.out.println("Réponse du serveur EX1:\n" + receivedMessage);


            //EX2 :dans cet exercice le client envoie un texte en minuscule au serveur
            messageToSend = "ce message le serveur doit le met en majuscule";
            sendData = messageToSend.getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 1234);
            clientSocket.send(sendPacket);

            // Attendre et afficher la réponse du serveur
            receiveData = new byte[1024];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Réponse du serveur EX2: \n" + receivedMessage);


            //EX3 :dans cet exercice Le client interroge le serveur comme suit : « Quelle est heure est-il ? »
            messageToSend = "Quelle est heure est-il ?";
            sendData = messageToSend.getBytes();
            sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 1234);
            clientSocket.send(sendPacket);

            // Attendre et afficher la réponse du serveur
            //Le client affiche la date et l’heure courante reçues à l’utilisateur.
            receiveData = new byte[1024];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Réponse du serveur EX3: \n" +"Date et heure courantes :"+ receivedMessage);

            // Fermer la socket du client
            clientSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



