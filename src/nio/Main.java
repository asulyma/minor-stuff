package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        int count;
        Path filePath = null;

        //Path to file
        try {
            filePath = Paths.get("D:\\test.txt");
        } catch (InvalidPathException e) {
            System.out.println("Path error " + e);
            return;
        }

        //Get channel to file
        try (SeekableByteChannel fChannel = Files.newByteChannel(filePath)) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(128);
            do {
                count = fChannel.read(byteBuffer);
                //if !end of file
                if (count != -1) {
                    byteBuffer.rewind();
                    for (int i = 0; i < count; i++) {
                        //read and display symbols
                        System.out.println((char) byteBuffer.get());
                    }
                }
            } while (count != -1);
            System.out.println();
        } catch (IOException e) {
            System.out.println("IO Error " + e);
        }
    }
}
