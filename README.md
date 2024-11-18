## Overview

This project implements a hierarchical Peer-to-Peer (P2P) file sharing system inspired by the Gnutella architecture. The system is designed to facilitate efficient and reliable file distribution across a decentralized network using **Java Remote Method Invocation (RMI)**. It ensures strong file consistency with both **push-based** and **pull-based** mechanisms.

## Features

- **Hierarchical Architecture**: SuperPeers manage file indexing, queries, and consistency for connected LeafNodes.
- **File Consistency**:
  - **Push-Based Consistency**: Proactively invalidates cached copies after file updates.
  - **Pull-Based Consistency**: LeafNodes periodically poll SuperPeers to verify file validity.
- **Network Topology**:
  - **All-to-All**: SuperPeers are fully connected for robust communication.
  - **Linear**: SuperPeers connect only to immediate neighbors for scalability.
- **Java RMI**: Simplifies communication between SuperPeers and LeafNodes.

## Components

### 1. **SuperPeer**
- Manages file indexing, query handling, and consistency.
- Key classes:
  - `SuperPeer.java`: Initializes and binds the SuperPeer implementation.
  - `SuperPeerImpl.java`: Implements core functionalities.
  - `SuperPeerInterface.java`: Defines remote methods.

### 2. **LeafNode**
- Represents individual peers responsible for file storage and user interactions.
- Key classes:
  - `AvgRespFileSearch.java`: Implements LeafNode functionalities.
  - `LeafNodeInterface.java`: Defines remote methods.

### 3. **Configuration Management**
- Configuration files specify system settings:
  - `peer_config.txt`: Peer details (IDs, ports, directories).
  - `superpeer_config.txt`: SuperPeer details.
  - `topology.txt`: Network topology.
  - `consistency_config.txt`: Consistency mechanism.

### 4. **Consistency Mechanisms**
- **Push-Based**:
  - SuperPeers broadcast invalidation messages to LeafNodes.
  - Suitable for strong consistency requirements.
- **Pull-Based**:
  - LeafNodes poll SuperPeers to verify file validity.
  - Reduces network overhead.

## Setup and Execution

### Prerequisites
- **Java Development Kit (JDK)** 8 or higher.
- **Apache Ant** (optional, for building the project).

### Configuration
1. Update configuration files in the `config/` directory as per your network setup:
   - Define peers in `peer_config.txt`.
   - Define SuperPeers in `superpeer_config.txt`.
   - Choose topology in `topology.txt`.
   - Set consistency mechanism in `consistency_config.txt`.

### Running the System
1. Compile the source code:
   ```bash
   javac -d bin/ src/com/gfiletransfer/*.java
Start the SuperPeer:

java -cp bin com.gfiletransfer.SuperPeer
Start the LeafNode:

java -cp bin com.gfiletransfer.LeafNode
Run test cases:

java -cp bin Test_Push_Mechanism/TestPushMain
java -cp bin Test_Pull_Mechanism/TestPullMain
Performance Metrics
Performance results can be found in the Performance Results/ directory. They include metrics such as:

Query response times
Bandwidth usage
Consistency validation results

Future Enhancements
Dynamic topology handling (peer join/leave mechanisms).
Enhanced fault tolerance (redundant SuperPeers and heartbeat mechanisms).
Security improvements for file transfers.
