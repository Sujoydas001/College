import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
/*
 * @author sujoy das - 19 
 * 
 * */

public class FTPServer{
    ServerSocket server_socket ;
    Socket server ;
    DataInputStream input_text ;
    DataOutputStream toClient ;
    public FTPServer(Integer port ) throws IOException {
        try {

            server_socket = new ServerSocket(Integer.valueOf(port));

            while( true ) {
                server = server_socket.accept();
                input_text = new DataInputStream((server.getInputStream()));
                toClient = new DataOutputStream(server.getOutputStream()) ;
                String path ="" ;

                try {
                    path =(String) input_text.readUTF(); //this should be path of file
                    String output_text = "" ;
                    if ( !(new File(path)).exists() ){
                        output_text = "file not found ; " ;
                    }else {
                        System.out.println("file searched for path : " + path );
                        BufferedReader bReader = new BufferedReader(new FileReader((path)) )  ;
                        while( bReader.ready()){
                            output_text += bReader.readLine() ;
                        }
                        System.out.println("server : sending file to client "  );
                    }
                    
                    toClient.writeUTF(output_text);
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }catch (IOException e) {
        	 toClient.writeUTF("probable connection off " );
            toClient.close() ;
            input_text.close();
            server_socket.close();
            server.close();
            e.printStackTrace();
        }

        System.out.println("closing connection");
    }
    public static void main(String[] args) throws IOException {
        new FTPServer(8080) ;
    }

}
