package com.mitrian.lab.server.file.csv;

import com.mitrian.lab.common.elements.Coordinates;
import com.mitrian.lab.common.elements.Location;
import com.mitrian.lab.common.elements.Person;
import com.mitrian.lab.common.elements.Worker;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.util.List;

/**
 * Class for writing elements of collection to file
 */
public class CsvLoader {

    /** Current heading for csv */
    private final static String[] HEADINGS = { "id", "name", "Coordinates_x", "Coordinates_y",
            "creationDate", "salary", "startDate", "endDate", "Status", "person_weight",
            "person_hairColor", "person_nationality", "location_x", "location_y", "location_z"};

    /** Current csv formater for building */
    private CSVFormat csvFormat;
    /** Current field for writing in file */
    private FileWriter fileWriter;
    /** Current file for writing */
    private File file;


    private PrintWriter printWriter;

    /**
     * Contracture for creating object of Person Console Reader class
     * @param file file for initializing file field
     */
    public CsvLoader(File file) throws IOException {
        this.file = file;
        fileWriter = new FileWriter(file);
        csvFormat = CSVFormat.DEFAULT.builder().setHeader(HEADINGS)
                                     .setNullString("").setDelimiter(",")
                                     .build();
    }

    public void load(List<Worker> workerCollection) throws IOException {
        CSVPrinter csvPrinter = csvFormat.print(fileWriter);
        for (Worker element: workerCollection){
            Worker worker = element;
            Coordinates coordinates = worker.getCoordinates();
            Person person = worker.getPerson();
            Location location = person.getLocation();
            csvPrinter.printRecord(worker.getId(), worker.getName(), coordinates.getX(),
                    coordinates.getY(), worker.getCreationDate(), worker.getSalary(),
                    worker.getStartDate(), worker.getEndDate(), worker.getStatus(),
                    person.getWeight(), person.getHairColor(), person.getNationality(),
                    location.getX(), location.getY(), location.getZ());
        }
    }

    public void close() throws IOException {
        fileWriter.close();
    }
}
