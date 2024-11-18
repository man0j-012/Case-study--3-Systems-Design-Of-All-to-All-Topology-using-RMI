@echo off
set path=%PATH%;C:\Program Files\Java\jdk-23\bin
echo "Setting the Class path"

echo "Running the Test (Multiple Peers)..."
TIMEOUT /T 3
echo "#################"
echo "Running the test with multiple peers."
echo "#################"
java -cp GnutellaP2P.jar;lib\javax.ws.rs-api-2.1.1.jar com.gfiletransfer.MultiClient
pause
