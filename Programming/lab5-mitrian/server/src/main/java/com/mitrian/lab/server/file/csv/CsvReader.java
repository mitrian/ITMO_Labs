package com.mitrian.lab.server.file.csv;

import com.mitrian.lab.common.commands.utils.ArgumentParser;
import com.mitrian.lab.common.commands.utils.ArgumentValidator;
import com.mitrian.lab.common.elements.*;
import com.mitrian.lab.common.exceptions.IncorrectFieldException;
import com.mitrian.lab.server.collection.Collection;
import com.mitrian.lab.server.collection.CollectionImpl;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class CsvReader {

    /** Current heading for csv */
    private final static String[] HEADINGS = { "id", "name", "Coordinates x", "Coordinates y",
            "creationDate", "salary", "startDate", "endDate", "Status", "person weight",
            "person hairColor", "person nationality", "location x", "location y", "location z"};

    /** Current field for reader */
    private final Reader reader;
    /** Current collection field */
    Collection<Worker> collection;

    public CsvReader(CollectionImpl collection, Reader reader) {
        this.collection = collection;
        this.reader = reader;
    }

    public void read() throws IOException, IncorrectFieldException {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder().setHeader(HEADINGS)
                                       .setNullString("").setDelimiter(",")
                                       .build();
        List<Worker> workersFileCollection = new LinkedList<>();

        Iterable<CSVRecord> records = csvFormat.parse(reader);

        for (CSVRecord record: records){
            long id = ArgumentParser.parseLong(record.get("id"));
            String name = record.get("name");
            Long coordinatesX = ArgumentParser.parseLong(record.get("Coordinates x"));
            Integer coordinatesY = ArgumentParser.parseInteger(record.get("Coordinates y"));
            LocalDate creationDate = ArgumentParser.parseLocalDate("creationDate");
            Float salary = ArgumentParser.parseFloat("salary");
            LocalDate startDate = ArgumentParser.parseLocalDate("startDate");
            Date endDate = ArgumentParser.parseDate("endDate");
            Status status = ArgumentParser.parseStatus("status");
            Double weight = ArgumentParser.parseDouble("person weight");
            Color hairColor = ArgumentParser.parseColor("person hairColor");
            Country nationality = ArgumentParser.parseCountry("person nationality");
            Long locationX = ArgumentParser.parseLong("location x");
            Double locationY = ArgumentParser.parseDouble("location y");
            int locationZ = ArgumentParser.parseInteger("location z");
            Location location = Location.newBuilder()
                    .setX(locationX).setY(locationY)
                    .setZ(locationZ).build();
            Person person = new Person.Builder(weight, hairColor)
                    .setNationality(nationality)
                    .setLocation(location).build();
            Coordinates coordinates = Coordinates.newBuilder()
                    .setX(coordinatesX)
                    .setY(coordinatesY)
                    .build();
            Worker worker = new Worker.Builder(name, coordinates, startDate, person)
                    .setCreationDate()
                    .setEndDate(endDate)
                    .setStatus(status)
                    .setSalary(salary)
                    .build();

            workersFileCollection.add(worker);
        }
        if (ArgumentValidator.validationFileId(workersFileCollection)){
            for (Worker worker: workersFileCollection){
                collection.add(worker);
            }
        } else {
            throw new IncorrectFieldException("Значение поля id должно быть уникальным");
        }
    }
}
