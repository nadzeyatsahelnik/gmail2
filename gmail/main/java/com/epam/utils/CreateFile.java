package com.epam.utils;

import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nadzeya_Tsahelnik
 */
public class CreateFile {

    public static StringSelection createFile(String filePath) {
        File file = new File(filePath);
        StringSelection stringSelection = new StringSelection(file.getAbsolutePath());
        return stringSelection;
    }

    public static StringSelection createFileWithSize(String filePath, long size) {
        StringSelection stringSelection = null;
        try {
            File file = new File(filePath);
            try (RandomAccessFile rf = new RandomAccessFile(file, "rw")) {
                rf.setLength(size);
            }
            stringSelection = new StringSelection(file.getAbsolutePath());

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stringSelection;
    }
}
