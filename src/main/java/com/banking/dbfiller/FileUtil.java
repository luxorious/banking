package com.banking.dbfiller;

import java.util.List;
/**
 * Interface for reading data from a file and storing it in a list.
 */
public interface FileUtil {
    /**
     * Reads data from a file located at the specified file path and stores it in a list.
     *
     * @param filePath The file path from which to read the data.
     * @return A list of strings containing the data read from the file.
     */
    List<String> writeFromFileToList(String filePath);
}
