import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(1234);
        Server server = new Server(serverSocket);
        server.startServer();
    }
    //this istance(obiect) will be responsible for listening the incoming connections or clients in creating socket objects to comunicat with them
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket){
        this.serverSocket=serverSocket;
    }
//making a method that will be responsible for keeping the server running
    public void startServer(){
        try {
            while (!serverSocket.isClosed()){
                Socket socket=serverSocket.accept();
                System.out.println("Un client nou s-a conectat!");
                ClientHandler clientHandler= new ClientHandler(socket);
                Thread thread= new Thread(clientHandler);
                thread.start();

            }
        }catch (IOException e){

        }
    }


    public void closeServerSocket(){
        try {
            if (serverSocket != null){
                serverSocket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
