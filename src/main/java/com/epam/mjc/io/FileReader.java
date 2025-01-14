package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileReader {
    static final Logger LOGGER = Logger.getLogger(FileReader.class.getName());

    public Profile getDataFromFile(File file) {
        StringBuilder builder = new StringBuilder();

        try (FileInputStream in = new FileInputStream(file.getAbsolutePath())) {

            int read;
            while ((read = in.read()) != -1) {
                builder.append((char) read);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] keyValuePairs = builder.toString().split("\\R");
        Profile profile = new Profile();

        for (String keyValuePair : keyValuePairs) {
            String[] keyValue = keyValuePair.split(" ");
            String fieldName = keyValue[0].substring(0, keyValue[0].length() - 1);
            switch (fieldName) {
                case "Age":
                    profile.setAge(Integer.parseInt(keyValue[1]));
                    break;
                case "Name":
                    profile.setName(keyValue[1]);
                    break;
                case "Email":
                    profile.setEmail(keyValue[1]);
                    break;
                case "Phone":
                    profile.setPhone(Long.parseLong(keyValue[1]));
                    break;
                default:
                    LOGGER.log(Level.SEVERE, () -> "Wrong field name" + fieldName);
                    break;
            }

        }

        return profile;
    }
}
