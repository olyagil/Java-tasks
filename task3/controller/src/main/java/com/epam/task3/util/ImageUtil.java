package com.epam.task3.util;

import lombok.extern.slf4j.Slf4j;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
@Slf4j
public class ImageUtil {

    public static String encoder(Blob image) throws SQLException {

        byte[] bytes = image.getBytes(1L, (int) image.length());

        return Base64.getEncoder().encodeToString(bytes);
    }

    public static Blob decoder(String base64Image) throws SQLException {

        byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
        return new SerialBlob(imageByteArray);
    }

    public static String encoderFromFile(String path) {
        File file = new File(path);
        FileInputStream fileInputStreamReader = null;
        try {
            fileInputStreamReader = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            log.error("Can't find the file" + path);
        }
        byte[] bytes = new byte[(int) file.length()];
        try {
            fileInputStreamReader.read(bytes);
        } catch (NullPointerException | IOException e) {
            log.error("Can't read from file.", e);
        }

        return Base64.getEncoder().encodeToString(bytes);
    }
}
