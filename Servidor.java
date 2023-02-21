import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class Servidor {

    public static void main(String[] args) {

        byte[] buffer;

        try {
            DatagramSocket socketUDP = new DatagramSocket(5000);
            while (true) {
                buffer=new byte[1024];
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(peticion);
                String mensaje = new String(peticion.getData());
                System.out.println(mensaje);
                int puertoCliente = peticion.getPort();
                InetAddress direccion = peticion.getAddress();
                mensaje = "Hola mundo desde el servidor!";
                buffer=new byte[1024];
                buffer = mensaje.getBytes(StandardCharsets.UTF_8);
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);
                socketUDP.send(respuesta);
            }
        } catch (IOException ex) {
            throw new RuntimeException();
        }

    }

}
