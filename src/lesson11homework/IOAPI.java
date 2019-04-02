package lesson11homework;

import java.io.*;
import java.util.Arrays;
import java.util.Vector;

public class IOAPI {
    public static void main(String[] args) {
        try {
          //  copyFile("wp.txt", "src/wp2.txt");
          //  splitFile("wp.txt", 1024000);
          //  glueFile("wp.txt", 4);
            encryptByPass("wp.txt", "wp.crypt", "der parol");
            encryptByPass("wp.crypt", "wp.decrypt", "der parol");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void copyFile(String source, String destination) throws IOException {
        try (
                FileInputStream fileInputStream = new FileInputStream(source);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                FileOutputStream fileOutputStream = new FileOutputStream(destination);
                BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream)
        )
        {


            byte[] buf = new byte[1024];

            int len;

            while ((len = fileInputStream.read(buf)) > 0) {
                 byteArrayOutputStream.write(buf, 0, len);
            }
            outputStream.write(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.toByteArray().length);
        }
    }
    private static void splitFile(String source, int parts) throws IOException {
        try (
                FileInputStream fileInputStream = new FileInputStream(source);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        )
        {
            byte[] buf = new byte[parts];
            int len;
            int i = 0;
            while ((len = fileInputStream.read(buf)) > 0) {
                try(
                       FileOutputStream fileOutputStream = new FileOutputStream(source + i);
                       BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream)
                ){
                    outputStream.write(buf, 0, buf.length);
                }
                i++;
            }
        }
    }
    private static void glueFile(String destination, int numFiles) throws IOException {
        try (/*FileInputStream inputStream1 = new FileInputStream(files[0]);
            FileInputStream inputStream2 = new FileInputStream(files[1]);*/
            ByteArrayOutputStream bout = new ByteArrayOutputStream())
            {
                Vector<InputStream> sequence = new Vector<>();
                for (int i = 0; i < numFiles; i++) {
                    InputStream stream = new FileInputStream(destination + i);
                    sequence.add(stream);
                }
                SequenceInputStream sequenceStream = new SequenceInputStream(sequence.elements());
                byte[] buf  = new byte[1024];
                int len;
                while ((len = sequenceStream.read(buf)) > 0) {
                    bout.write(buf, 0, len);
                }
                FileOutputStream fileOutputStream = new FileOutputStream(destination);
                BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream);
                outputStream.write(bout.toByteArray(), 0, bout.toByteArray().length);

            }
        }
    private static void encryptByPass(String source, String destination, String pass) throws IOException {
        try (
                FileInputStream fileInputStream = new FileInputStream(source);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                FileOutputStream fileOutputStream = new FileOutputStream(destination);
                BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream)
        )
        {
            byte[] buf = new byte[1024];
            int len;
            while ((len = fileInputStream.read(buf)) > 0) {
                byteArrayOutputStream.write(buf, 0, len);
            }
            byte[] bytePass = pass.getBytes();
            byte[] file = byteArrayOutputStream.toByteArray();
            for (int i = 0; i < file.length; i++) {
                file[i] = (byte) (file[i]^bytePass[i%bytePass.length]);

            }
            outputStream.write(file, 0, file.length);
        }

    }
    }

