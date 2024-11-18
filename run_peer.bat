@echo off
set path=%PATH%;C:\Program Files\Java\jdk-23\bin
echo "Setting the Class path"

echo "Running the Peer..."
TIMEOUT /T 3
echo "#################"
echo "Peer is now Running. Please configure your settings."
echo "#################"
java -cp GnutellaP2P.jar;lib\javax.ws.rs-api-2.1.1.jar com.gfiletransfer.LeafNode
pause
