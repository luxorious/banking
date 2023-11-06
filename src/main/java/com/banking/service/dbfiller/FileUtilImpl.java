package com.banking.service.dbfiller;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Implementation of the FileUtil interface to read data from a file and store it in a list.
 */
@Component
public class FileUtilImpl implements FileUtil {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> writeFromFileToList(String filePath) {
        File file = new File(filePath);
        List<String> stringsFromFile;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            stringsFromFile = bufferedReader.lines().toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringsFromFile;
    }
}
