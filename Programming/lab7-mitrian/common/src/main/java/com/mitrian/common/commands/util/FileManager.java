package com.mitrian.common.commands.util;

import com.mitrian.common.exceptions.impl.file.FileReadableException;
import com.mitrian.common.exceptions.impl.file.NoSuchFileException;
import com.mitrian.common.exceptions.impl.file.FileWritableException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Class to getting file by name
 */
public class FileManager {

    /**
     * Taking file by name
     * @param fileName of file
     * @return file
     * @throws FileReadableException file not readable
     * @throws NoSuchFileException absence of file
     * @throws FileWritableException file not writable
     */
    public static File getFileByName(String fileName) throws FileReadableException, NoSuchFileException, FileWritableException {
        File file = new File(fileName);

        checkFileAvailable(file);
        checkFileReadable(file);
        checkFileWritable(file);

        return file;
    }


    /**
     * Check possibility of reading file
     * @param file to check
     * @throws FileReadableException if there is no possibility
     */
    private static void checkFileReadable(File file) throws FileReadableException{
        if  (!Files.isReadable(Path.of(file.toURI()))) {
            throw new FileReadableException("У вас недостаточно прав для чтения файла");
        }
    }


    /**
     * Checking possibility or writing in file
     * @param file to check
     * @throws FileWritableException if there is no possibility
     */
    private static void checkFileWritable(File file) throws FileWritableException{
        if (!Files.isWritable(Path.of(file.toURI()))) {
            throw new FileWritableException("У вас недостаточно прав для записи в файл");
        }
    }


    /**
     * Checking if file with inputted name exists
     * @param file to check
     * @throws NoSuchFileException if file doesn't exist
     */
    private static void checkFileAvailable(File file) throws NoSuchFileException {
        if (!file.exists()){
            throw new NoSuchFileException("Такого файла не существует");
        }
    }

}
