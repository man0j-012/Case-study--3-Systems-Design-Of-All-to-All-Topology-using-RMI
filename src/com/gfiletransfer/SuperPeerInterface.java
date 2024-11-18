package com.gfiletransfer;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;

public interface SuperPeerInterface extends Remote {

    // Method for registering files, handling actions like "new", "del", and "upd"
    public void registryFiles(
            String rd,               // action (new, del, or upd)
            String filename,         // file name
            String peerid,           // peer ID
            String port_num,         // port number
            String directory,        // directory where the file is stored
            String sPeer,            // super peer ID
            String copyType,         // type of copy (e.g., Master, Replica)
            String versionNum,       // version number
            String status,           // file status (valid, invalid)
            String lastModTime,      // last modified time
            String TTR,              // time-to-refresh
            String ogPeerId          // original peer ID
    ) throws RemoteException;

    // Method to search for a file by its name
    public Collection<ArrayList<String>> searchFile(String filename) throws RemoteException;

    // Method to send a query to the network to search for a file
    public void query(
            String msgId,             // message ID
            int TTL,                  // time-to-live for the query
            String filename,          // file name to search for
            String reqPeerId,         // requesting peer's ID
            String reqPortNum         // requesting peer's port number
    ) throws RemoteException;

    // Method to return the result of a query, providing the file information
    public void queryHit(
            String msgId,             // message ID
            int TTL,                  // time-to-live after query
            String filename,          // file name searched for
            Collection<ArrayList<String>> resultArr  // search results
    ) throws RemoteException;

    /*--------- start change ----------*/

    // Method for broadcasting the status of a file update to other SuperPeers
    public void broadCastSP(
            String msgId,             // message ID
            String filename,          // file name being updated
            String originLNServer,    // origin LeafNode server
            String verNum             // file version number
    ) throws RemoteException;

    // Method for broadcasting file invalidation to SuperPeers in the network
    public void broadCastSS(
            String msgId,             // message ID
            String filename,          // file name being invalidated
            String originLNServer,    // origin LeafNode server
            String verNum             // file version number
    ) throws RemoteException;

    // Method to check the file version and its status
    public String poll(
            String filename,          // file name
            String verNum,            // version number
            String ogPeerId           // original peer ID
    ) throws RemoteException;

    // Method to retrieve the version number of a file at a given peer and port
    public String getVersionNum(
            String filename,          // file name
            String peerid,            // peer ID
            String port_num,          // port number
            String copyType,          // copy type (Master or Replica)
            String ogPeerId           // original peer ID
    ) throws RemoteException;

    // Method to notify polling request and handle version checking
    public void notifyPoll(
            String filename,          // file name
            String reqPeerid,         // requesting peer ID
            String reqPort_num,       // requesting peer port number
            String copyType,          // copy type (Master or Replica)
            String verNum,            // version number
            String ogPeerId,          // original peer ID
            String TTR,               // time-to-refresh
            String lastModTime        // last modified time
    ) throws RemoteException, NotBoundException;

    /*--------- end change ----------*/
}
