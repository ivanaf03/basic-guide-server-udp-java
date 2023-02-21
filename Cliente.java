import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    public static void main(String[] args) {
        byte[] buffer;
        try {
            InetAddress direccionServidor = InetAddress.getByName("localhost");
            DatagramSocket socketUDP = new DatagramSocket();
            String mensaje = "Hola mundo desde el cliente!";
            buffer=new byte[1024];
            buffer = mensaje.getBytes(StandardCharsets.UTF_8);
            DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, direccionServidor, 5000);
            socketUDP.send(pregunta);
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
            socketUDP.receive(peticion);
            mensaje = new String(peticion.getData());
            System.out.println(mensaje);
            socketUDP.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
