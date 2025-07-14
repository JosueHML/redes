package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    // declaramos los sockets y los streams
    private Socket s = null;
    private DataInputStream in =null;
    private DataOutputStream out = null;
    // Constructutor del cliente  que recibe direccion
    public Client(String addr, int port){
        try {
            // cramos el socket que se conecta con el servidor
            s = new Socket(addr, port );
            System.out.println("conectado al servidor");

            //entrada  de datos
            in = new DataInputStream(System.in);

            //salida
            out = new DataOutputStream(s.getOutputStream());

        }catch (UnknownHostException u){
            System.out.println("error de host" + u);
            return;

        }catch (IOException i){
            System.out.println("Error es E/S"+ i);
            return ;
        }

        String mensaje = "";

        // Bucle que seguira enviando datos para que el usuarios escriba over

        while (!mensaje.equals("Over")) {
            try {
                mensaje = in.readLine();
                out.writeUTF(mensaje);

            }catch(IOException i){
                System.out.println(i);
            }
            //cierre de conexion

            try{
                in.close();
                out.close();
                s.close();
            }catch (IOException i){
                System.out.println(i);
            }
        }
        public static void main( String[] args) {
            new Client("127.0.0.1", 5000);  // IP y puerto del servidor
        }


    }

}
