package nio;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Main {
    public static void main(String[] args) {
        channelWriteFile();
    }

    private static void channelReadFile() {
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

    private static void channelWriteFile() {
        //Get channel to file through the path with someone parameters
        try (FileChannel fChannel = (FileChannel) Files.newByteChannel(Paths.get("D:\\test.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            MappedByteBuffer mappedByteBuffer = fChannel.map(FileChannel.MapMode.READ_WRITE, 0, 26);

            for (int i = 0; i < 26; i++)
                mappedByteBuffer.put((byte) ('A' + i));
        } catch (IOException e) {
            System.out.println("IO Error " + e);
            System.exit(1);
        }
    }

    private static void streamReadFile() {
        int count;

        try (InputStream fileInputStream = Files.newInputStream(Paths.get("D:\\test.txt"))) {
            do {
                count = fileInputStream.read();
                if (count != -1) System.out.println((char) count);
            } while (count != -1);
        } catch (IOException e) {
            System.out.println("IO Error " + e);
        }
    }

    private static void streamWriteFile() {
        try (OutputStream fileOutputStream = new BufferedOutputStream(Files.newOutputStream(Paths.get("D:\\test.txt")))) {
            for (int j = 0; j < 26; j++) {
                fileOutputStream.write((byte) 'A' + j);
            }
        } catch (IOException e) {
            System.out.println("IO Error " + e);
        }
    }
}
