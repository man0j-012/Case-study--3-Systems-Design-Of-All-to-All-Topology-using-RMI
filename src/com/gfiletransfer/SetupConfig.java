package com.gfiletransfer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SetupConfig {

    // Lists to store configuration details
    ArrayList<GetPeerDetails> arrPD = new ArrayList<>();
    ArrayList<GetSuperPeerDetails> arrSPD = new ArrayList<>();
    ArrayList<GetTopologyDetails> arrTD = new ArrayList<>();
    String topology = null;
    String consisApp = null; // Consistency mechanism (e.g., PUSH, PULL1, PULL2)

    /**
     * Constructs a SetupConfig object and loads all configuration files.
     *
     * @throws IOException if any configuration file cannot be read.
     */
    public SetupConfig() throws IOException {
        // Peer configuration
        File peerConfig = new File("config/peer_config.txt");
        loadPeerConfig(peerConfig);

        // SuperPeer configuration
        File superPeerConfig = new File("config/superpeer_config.txt");
        loadSuperPeerConfig(superPeerConfig);

        // Topology details configuration
        File topologyDetailsConfig = new File("config/topology_config.txt");
        loadTopologyDetailsConfig(topologyDetailsConfig);

        // Topology type
        File topologyFile = new File("config/topology.txt");
        loadTopology(topologyFile);

        // Consistency mechanism
        File consistencyConfigFile = new File("config/consistency_config.txt");
        loadConsistencyMechanism(consistencyConfigFile);
    }

    /**
     * Loads the peer configuration file.
     *
     * @param file The peer configuration file.
     * @throws IOException if the file cannot be read.
     */
    private void loadPeerConfig(File file) throws IOException {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty() || line.startsWith("#")) continue;

                List<String> tokens = Arrays.asList(line.split("\\s*,\\s*"));
                if (tokens.size() != 5) {
                    System.out.println("Invalid line in peer_config.txt: " + line);
                    continue;
                }

                GetPeerDetails pd = new GetPeerDetails();
                pd.setPeer_ID(tokens.get(0));
                pd.setPeer_Port(tokens.get(1));
                pd.setDir(tokens.get(2));
                pd.setCacheDir(tokens.get(3));
                pd.setSuperPeer(tokens.get(4));
                arrPD.add(pd);
            }
        }
        System.out.println("Loaded " + arrPD.size() + " peer configurations.");
    }

    /**
     * Loads the superpeer configuration file.
     *
     * @param file The superpeer configuration file.
     * @throws IOException if the file cannot be read.
     */
    private void loadSuperPeerConfig(File file) throws IOException {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty() || line.startsWith("#")) continue;

                List<String> tokens = Arrays.asList(line.split("\\s*,\\s*"));
                if (tokens.size() < 3) {
                    System.out.println("Invalid line in superpeer_config.txt: " + line);
                    continue;
                }

                GetSuperPeerDetails spd = new GetSuperPeerDetails();
                spd.setPeer_ID(tokens.get(0));
                spd.setPeer_Port(tokens.get(1));
                spd.setLeaf_ID(String.join(",", tokens.subList(2, tokens.size())));
                arrSPD.add(spd);
            }
        }
        System.out.println("Loaded " + arrSPD.size() + " superpeer configurations.");
    }

    /**
     * Loads the topology details configuration file.
     *
     * @param file The topology details configuration file.
     * @throws IOException if the file cannot be read.
     */
    private void loadTopologyDetailsConfig(File file) throws IOException {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim(); // Trim to handle leading/trailing spaces
                System.out.println("Raw Line: " + line); // Print out raw line for debugging

                if (line.isEmpty() || line.startsWith("#")) continue; // Skip empty or comment lines

                List<String> tokens = Arrays.asList(line.split("\\s*;\\s*"));
                System.out.println("Tokens: " + tokens); // Print tokens for debugging

                if (tokens.size() != 3) {
                    System.out.println("Invalid line in topology_config.txt: " + line);
                    continue;
                }

                GetTopologyDetails td = new GetTopologyDetails();
                td.setPeer_ID(tokens.get(0));
                td.setLinear_Neighbour(tokens.get(1));
                td.setAll_Neighbour(tokens.get(2));

                arrTD.add(td);
            }
        }
        System.out.println("Loaded " + arrTD.size() + " topology configurations.");
    }

    /**
     * Loads the topology type configuration file.
     *
     * @param file The topology type configuration file.
     * @throws IOException if the file cannot be read.
     */
    private void loadTopology(File file) throws IOException {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty() || line.startsWith("#")) continue;

                topology = line.trim();
            }
        }
        System.out.println("Topology type: " + topology);
    }

    /**
     * Loads the consistency mechanism configuration file.
     *
     * @param file The consistency mechanism configuration file.
     * @throws IOException if the file cannot be read.
     */
    private void loadConsistencyMechanism(File file) throws IOException {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty() || line.startsWith("#")) continue;

                consisApp = line.trim();
            }
        }
        System.out.println("Consistency mechanism: " + consisApp);
    }
}
