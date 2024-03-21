
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.Socket;
/*
 * @author sujoy das - 19 
 * 
 * */
public class FTPClient {

    Socket client_socket ;
    String outputToShow = "" ; 
    DataOutputStream output_text ;
    DataInputStream  inputFromServer ;
    public FTPClient(String ipaddress, Integer port ,String path  ) throws NoRouteToHostException,ConnectException{
        try {
            client_socket = new Socket(ipaddress,port);
            output_text = new DataOutputStream(client_socket.getOutputStream());
            inputFromServer = new DataInputStream(client_socket.getInputStream()) ; 
            
            try {                
                output_text.writeUTF(path);
                output_text.flush();
                outputToShow = inputFromServer.readUTF() ; 
                System.out.println("client : from server got : " + outputToShow);
            }catch (IOException e) {
                e.printStackTrace();
            }
            
            output_text.close();
            client_socket.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String exportFile() {
    	return outputToShow ; 
    }

}

