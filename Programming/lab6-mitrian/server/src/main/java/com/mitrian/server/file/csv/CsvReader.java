package com.mitrian.server.file.csv;

import com.mitrian.common.commands.util.parser.ArgumentParser;
import com.mitrian.common.commands.util.validator.ArgumentValidator;
import com.mitrian.common.elements.*;
import com.mitrian.common.exceptions.IncorrectFieldException;
import com.mitrian.server.collection.Collection;
import com.mitrian.server.collection.CollectionImpl;
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
    private final static String[] HEADINGS = { "id", "name", "coordinates_x", "coordinates_y",
            "creationDate", "salary", "startDate", "endDate", "status", "person_weight",
            "person_hairColor", "person_nationality", "location_x", "location_y", "location_z"};

    /** Current field for reader */
    private final Reader reader;
    /** Current collection field */
    Collection<Worker> collection;

    public CsvReader(CollectionImpl collection, Reader reader) {
        this.collection = collection;
        this.reader = reader;
    }

    public List<Worker> read() throws IOException, IncorrectFieldException {
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder().setHeader(HEADINGS)
                                       .setSkipHeaderRecord(true)
                                       .setNullString("").setDelimiter(",")
                                       .build();
        List<Worker> workersFileCollection = new LinkedList<>();

        Iterable<CSVRecord> records = csvFormat.parse(reader);

        for (CSVRecord record: records){
            Integer id = ArgumentParser.parseInteger(record.get("id"));
            String name = ArgumentParser.parseString(record.get("name"));
            Long coordinatesX = ArgumentParser.parseLong(record.get("coordinates_x"));
            Integer coordinatesY = ArgumentParser.parseInteger(record.get("coordinates_y"));
            LocalDate creationDate = ArgumentParser.parseLocalDate(record.get("creationDate"));
            Float salary = ArgumentParser.parseFloat(record.get("salary"));
            LocalDate startDate = ArgumentParser.parseLocalDate(record.get("startDate"));
            Date endDate = ArgumentParser.parseDate(record.get("endDate"));
            Status status = ArgumentParser.parseStatus(record.get("status"));
            Double weight = ArgumentParser.parseDouble(record.get("person_weight"));
            Color hairColor = ArgumentParser.parseColor(record.get("person_hairColor"));
            Country nationality = ArgumentParser.parseCountry(record.get("person_nationality"));
            Long locationX = ArgumentParser.parseLong(record.get("location_x"));
            Double locationY = ArgumentParser.parseDouble(record.get("location_y"));
            int locationZ = ArgumentParser.parseInteger(record.get("location_z"));
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
                    .setFileCreationDate(creationDate)
                    .setEndDate(endDate)
                    .setStatus(status)
                    .setSalary(salary)
                    .build();
            worker.setId(id);
            if (ArgumentValidator.validationWorker(worker)){
                workersFileCollection.add(worker);
            }
        }
        if (!ArgumentValidator.validationFileId(workersFileCollection)){
            throw new IncorrectFieldException("Значение поля id должно быть уникальным");
        }
        return workersFileCollection;
    }

    public void close() throws IOException {
        reader.close();
    }
}
