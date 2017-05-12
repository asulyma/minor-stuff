package nio;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        //Get channel to file through the path
        try (FileChannel fChannel = (FileChannel) Files.newByteChannel(Paths.get("D:\\test.txt"))) {
            long fileSize = fChannel.size();
            MappedByteBuffer mappedByteBuffer = fChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileSize);

            for (int i = 0; i < fileSize; i++)
                System.out.println((char) mappedByteBuffer.get());
            System.out.println();
        } catch (IOException e) {
            System.out.println("IO Error " + e);
        }
    }
}
