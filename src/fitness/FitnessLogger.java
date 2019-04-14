package fitness;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

public class FitnessLogger {
    private static final FitnessLogger INSTANCE = new FitnessLogger();
    private File file;
    private String fileName = "log.txt";
    private FitnessLogger(){
        file = new File(fileName);
    }
    public static FitnessLogger getIinstance() {
        return INSTANCE;
    }
    public void write(Human human, FitnessServiceEnumeration type, LocalDateTime time, String result) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(human.getSurname()).append(" ")
                .append(human.getName()).append(" ")
                .append(type).append(" ")
                .append(time).append(" ")
                .append(result).append(" ")
                .append(System.lineSeparator());
        try(FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream)){
            byte[] buffer = sb.toString().getBytes();
            outputStream.write(buffer, 0, buffer.length);
        }
    }
}
