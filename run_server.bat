@echo off
set path=%PATH%;C:\Program Files\Java\jdk-23\bin
echo "Setting the Class path"

echo "Running the Server..."
TIMEOUT /T 3
echo "#################"
echo "SuperPeer is now Running. Please configure your settings."
echo "#################"
java -cp GnutellaP2P.jar;lib\javax.ws.rs-api-2.1.1.jar com.gfiletransfer.SuperPeer
pause
