package com.gfiletransfer;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

// This contains the main method for setting up the Peer.
public class MultiClient {

    public static void main(String[] args) throws RemoteException {
        Scanner sc = new Scanner(System.in);
        String portno = null;
        String directoryName = null;
        String superPeerId = null;
        String cachedDirectoryName = null;

        // Input Peer ID
        System.out.println("Enter Peer ID ");
        String peerId = sc.nextLine();

        // Reading Port details from property file for Instantiating Leaf Node
        SetupConfig scg;
        try {
            scg = new SetupConfig();
            // Getting the calling Super Peer Port number
            for (GetPeerDetails p : scg.arrPD) {
                if (p.getPeer_ID().equalsIgnoreCase(peerId)) {
                    portno = p.getPeer_Port();
                    directoryName = p.getDir();
                    cachedDirectoryName = p.getCacheDir();
                    superPeerId = p.getSuperPeer();
                    break;
                }
            }
        } catch (IOException e1) {
            System.out.println("IOException occurred while reading the property file at Leaf Node Initialization.");
        }

        /*--------- start change ----------*/
        // Registering the peer on the specified port & setting up the remote object
        Registry registry = LocateRegistry.createRegistry(Integer.parseInt(portno));

        // Create the LeafNode implementation object
        AvgRespFileSearch lnImpl = new AvgRespFileSearch(portno, directoryName, superPeerId, peerId, cachedDirectoryName);

        // Exporting the remote object
        LeafNodeInterface lnInter = (LeafNodeInterface) UnicastRemoteObject.exportObject(lnImpl, 0);

        // Binding the remote object in the RMI registry
        registry.rebind("root://LeafNode/" + portno + "/FS", lnInter);
        System.out.println("Peer is up and Running.");
        // Start the work process
        try {
            lnImpl.doWork();
        } catch (IOException e) {
            System.out.println("IO Exception at Leaf Node Main" + e.getMessage());
            e.printStackTrace();
        }
    }
}
