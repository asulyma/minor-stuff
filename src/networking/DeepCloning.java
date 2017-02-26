package networking;

/**
 * The implementation of deep copy.
 */

import java.io.*;

public class DeepCloning {
    public void work(){
        Networking networking = new Networking("Original");
        ByteArrayOutputStream write = new ByteArrayOutputStream();
        ObjectOutputStream outputStream;

        try {
            outputStream = new ObjectOutputStream(write);
            outputStream.writeObject(networking);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }




        byte[] buffer = write.toByteArray();
        ByteArrayInputStream read = new ByteArrayInputStream(buffer);
        ObjectInputStream inputStream;
        Networking networkingClone = null;

        try {
            inputStream = new ObjectInputStream(read);
            networkingClone = (Networking)inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(networking);
        System.out.println(networkingClone);

    }
}
