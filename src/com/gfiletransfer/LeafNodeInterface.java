package com.gfiletransfer;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

public interface LeafNodeInterface extends Remote {
    // Method to download a file, given the list of directory paths
    public byte[] fileDownload(ArrayList<String> searchedDir) throws RemoteException;

    // Method to handle the query response from a Super Peer
    public boolean queryHit(String msgId, int TTL, String filename, Collection<ArrayList<String>> resultArr) throws RemoteException;

    /*--------- start change ----------*/

    // Poll method to check if the file is up to date
    public String poll(String filename, String verNum) throws RemoteException, NotBoundException;

    // Method to invalidate a file
    public void invalidate(String filename, String originLNServer, String verNum) throws RemoteException;

    // Method to mark a file as out of date
    public void outOfDate(String filename, String invalidStatus, String ogPeerId) throws RemoteException;

    // Method to get the status of a file
    public String getStatus(String filename) throws RemoteException;

    /*--------- end change ----------*/
}
