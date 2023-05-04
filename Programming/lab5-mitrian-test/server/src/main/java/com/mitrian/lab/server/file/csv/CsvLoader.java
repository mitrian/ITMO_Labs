package com.mitrian.lab.server.file.csv;

import com.mitrian.lab.common.elements.Coordinates;
import com.mitrian.lab.common.elements.Location;
import com.mitrian.lab.common.elements.Person;
import com.mitrian.lab.common.elements.Worker;
import com.mitrian.lab.common.utils.Printer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.util.List;

/**
 * Class for writing elements of collection to file
 */
public class CsvLoader {

    /** Current heading for csv */
    private final static String[] HEADINGS = { "id", "name", "coordinates_x", "coordinates_y",
            "creationDate", "salary", "startDate", "endDate", "status", "person_weight",
            "person_hairColor", "person_nationality", "location_x", "location_y", "location_z"};

    /** Current csv formater for building */
    private final CSVFormat csvFormat;
    /** Current field for writing in file */
    private final FileWriter fileWriter;
    /** Current file for writing */
    private File file;

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
            Coordinates coordinates = element.getCoordinates();
            Person person = element.getPerson();
            Location location = person.getLocation();
            csvPrinter.printRecord(element.getId(), element.getName(), coordinates.getX(),
                    coordinates.getY(), element.getCreationDate(), element.getSalary(),
                    element.getStartDate(), element.getEndDate(), element.getStatus(),
                    person.getWeight(), person.getHairColor(), person.getNationality(),
                    location.getX(), location.getY(), location.getZ());
        }
    }

    public void close() throws IOException {
        fileWriter.close();
    }
}
