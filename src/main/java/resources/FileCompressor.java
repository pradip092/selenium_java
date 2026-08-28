package resources;

import java.io.*;
import java.util.zip.*;

public class FileCompressor {
    public static void main(String[] args) {
        String sourceFilePath = "C://LMS_MPC//Automation//Automation_Reports//Test-Automation-Report.html";
        String compressedFilePath = "C://Users///OneDrive -//Desktop/file.zip";

        try {
            FileInputStream fileInputStream = new FileInputStream(sourceFilePath);
            FileOutputStream fileOutputStream = new FileOutputStream(compressedFilePath);
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
            ZipEntry zipEntry = new ZipEntry("compressed_file.html");

            zipOutputStream.putNextEntry(zipEntry);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fileInputStream.read(buffer)) > 0) {
                zipOutputStream.write(buffer, 0, length);
            }

            zipOutputStream.closeEntry();
            zipOutputStream.close();
            fileInputStream.close();
            fileOutputStream.close();

            System.out.println("File compressed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
