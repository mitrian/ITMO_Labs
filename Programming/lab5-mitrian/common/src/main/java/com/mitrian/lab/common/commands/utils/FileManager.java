package com.mitrian.lab.common.commands.utils;

import com.mitrian.lab.common.exceptions.impl.file.NoSuchFileException;
import com.mitrian.lab.common.exceptions.impl.file.FileReadableException;
import com.mitrian.lab.common.exceptions.impl.file.FileWritableException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {
    public static File getFileByName(String fileName) throws FileReadableException, NoSuchFileException, FileWritableException {
        File file = new File(fileName);

        checkFileAvailable(file);
        checkFileReadable(file);
        checkFileWritable(file);

        return file;
    }

    private static void checkFileReadable(File file) throws FileReadableException{
        if  (!Files.isReadable(Path.of(file.toURI()))) {
            throw new FileReadableException("У вас недостаточно прав для чтения файла");
        }
    }

    private static void checkFileWritable(File file) throws FileWritableException{
        if (!Files.isWritable(Path.of(file.toURI()))) {
            throw new FileWritableException("У вас недостаточно прав для записи в файл");
        }
    }

    private static void checkFileAvailable(File file) throws NoSuchFileException {
        if (!file.exists()){
            throw new NoSuchFileException("Такого файла не существует");
        }
    }

}
