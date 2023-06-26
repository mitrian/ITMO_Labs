package com.mitrian.server.database;

import com.mitrian.common.elements.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;

public class DatabaseUtilies {

    public static int insertElement(Connection connection, Worker worker) throws SQLException {

        connection.setAutoCommit(false);
        int workerId = insertElement(connection, worker);
        connection.commit();
        connection.setAutoCommit(true);
        return workerId;

    }


    public static void createLocationsTable(Connection connection) throws SQLException {
        String sqlLocationsTable = """
                CREATE TABLE IF NOT EXISTS Locations(id SERIAL PRIMARY KEY, x BIGSERIAL, y DOUBLE PRECISION, z INT);
                """;
        PreparedStatement createLocations = connection.prepareStatement(sqlLocationsTable);
        createLocations.executeUpdate();
    }

    public static void createCoordinatesTable(Connection connection) throws SQLException {
        String sqlCoordinatesTable = """
                CREATE TABLE IF NOT EXISTS Coordinates(
                id SERIAL PRIMARY KEY, 
                x BIGSERIAL CHECK (x <= 884),
                y BIGINT NOT NULL
                );
                """;
        PreparedStatement createCoordinates = connection.prepareStatement(sqlCoordinatesTable);
        createCoordinates.executeUpdate();
    }

    public static void createPersonsTable(Connection connection) throws SQLException {
        String sqlPersonsTable = """
                CREATE TABLE IF NOT EXISTS Persons(id SERIAL PRIMARY KEY, weight DOUBLE PRECISION CHECK (weqight>0), 
                hairColor VARCHAR(31), nationality VARCHAR(31), location_id SERIAL REFERENCES Locations);
                """;
        PreparedStatement createPersons = connection.prepareStatement(sqlPersonsTable);
        createPersons.executeUpdate();
    }

    public static void createWorkersTable(Connection connection) throws SQLException {
        String sqlWorkersTable = """
                CREATE TABLE IF NOT EXISTS Workers(id SERIAL PRIMARY KEY, name VARCHAR(31) NOT NULL CHECK (length(name)>0), 
                coordionates_id BIGSERIAL NOT NULL REFERENCES Coordinates, creationDate DATE NOT NULL, salary FLOAT CHECK(salary>0), 
                startDate DATE NOT NULL, endDate Date, status VARCHAR(31), person_id SERIAL NOT NULL REFERENCES Persons);
                """;
        PreparedStatement createWorkers = connection.prepareStatement(sqlWorkersTable);
        createWorkers.executeUpdate();
    }

    public static int insertWorkers(Worker worker, Connection connection) throws SQLException {
        long coordinatesId = insertCoordinates(worker.getCoordinates(), connection);
        long personId = insertPerson(worker.getPerson(), connection);
        String sqlInsertWorker = """
                INSERT INTO Workers(name, coordintes_id, creationDate, salary, startDate, endDate, 
                status, person_id);
                """;
        PreparedStatement insertWorkers = connection.prepareStatement(sqlInsertWorker);
        insertWorkers.setString(1, worker.getName());
        insertWorkers.setLong(2, coordinatesId);
        insertWorkers.setDate(3, Date.valueOf(worker.getCreationDate()));
        insertWorkers.setFloat(4, worker.getSalary());
        insertWorkers.setDate(5, (Date) worker.getEndDate());
        insertWorkers.setString(6, worker.getStatus().toString());
        insertWorkers.setLong(7, personId);
        ResultSet resultWorkers = insertWorkers.executeQuery();
        resultWorkers.next();
        return resultWorkers.getInt("id");
    }

    public static int insertCoordinates(Coordinates coordinates, Connection connection) throws SQLException {
        String sqlInsertCoordinates = """
                INSERT INTO Coordinates(x,y) VALUES (?,?,?) RETURNING ig;
                """;
        PreparedStatement insertCoorindates = connection.prepareStatement(sqlInsertCoordinates);
        insertCoorindates.setLong(1, coordinates.getX());
        insertCoorindates.setInt(2, coordinates.getY());
        ResultSet resultCoordinates = insertCoorindates.executeQuery();
        resultCoordinates.next();
        return resultCoordinates.getInt("id");
    }

    public static int insertPerson(Person person, Connection connection) throws SQLException {
        String sqlInsertPerons = """
                INSERT INTO Persons(weight, hairColor, nationality, location_id) VALUES(?,?,?,?) RETURNING id;
                """;
        PreparedStatement insertPerson = connection.prepareStatement(sqlInsertPerons);
        insertPerson.setDouble(1, person.getWeight());
        insertPerson.setString(2, person.getHairColor().toString()); //TODO
        insertPerson.setString(3, person.getNationality().toString()); //TODO
        insertPerson.setLong(4, insertLocation(person.getLocation(), connection));
        ResultSet resultPersons = insertPerson.executeQuery();
        resultPersons.next();
        return resultPersons.getInt("id");

    }

    public static int insertLocation(Location location, Connection connection) throws SQLException {
        String sqlInsertLocation = """
               INSERT INTO Locations(x,y,z) VALUES (?,?,?) RETURNING id;
                """;
        PreparedStatement insertLocation = connection.prepareStatement(sqlInsertLocation);
        insertLocation.setLong(1, location.getX());
        insertLocation.setDouble(2, location.getY());
        insertLocation.setInt(3, location.getZ());
        ResultSet resultLocation = insertLocation.executeQuery();
        resultLocation.next();
        return resultLocation.getInt("id");
    }

    public static void deleteWorkers(int workerId, Connection connection) throws SQLException {
        String sqlDeleteWorkers = """
                DELETE FROM Workers WHERE id = ? RETURNING coordinates_id, person_id;
                """;
        PreparedStatement deleteWorkers = connection.prepareStatement(sqlDeleteWorkers);
        deleteWorkers.setInt(1,workerId);
        ResultSet zap = deleteWorkers.executeQuery();
        zap.next();
        int coordinates_id = zap.getInt("coordinates_id");
        int person_id = zap.getInt("person_id");
        deleteCoordinates(coordinates_id, connection);
        deletePersons(person_id, connection);
    }

    public static void deletePersons(int personsId, Connection connection) throws SQLException {
        String sqlDeletePersons = """
                DELETE FROM Persons WHERE id = ? RETURNING location_id;
                """;
        PreparedStatement deletePersons = connection.prepareStatement(sqlDeletePersons);
        deletePersons.setInt(1, personsId);
        ResultSet zap = deletePersons.executeQuery();
        zap.next();
        int location_id = zap.getInt("location_id");
        deleteLocation(location_id, connection);
    }

    public static void deleteCoordinates(int coordinatesId, Connection connection) throws SQLException {
        String sqlDeleteCoordinates = """
                DELETE FROM Coordinates WHERE id = ?;
                """;
        PreparedStatement deleteCoordinates = connection.prepareStatement(sqlDeleteCoordinates);
        deleteCoordinates.setInt(1, coordinatesId);
        deleteCoordinates.executeUpdate();
    }

    public static void deleteLocation(int locationId, Connection connection) throws SQLException {
        String sqlDeleteLocation = """
                DELETE FROM Locations WHERE id = ?;
                """;
        PreparedStatement deleteLocation = connection.prepareStatement(sqlDeleteLocation);
        deleteLocation.setInt(1,locationId);
        deleteLocation.executeUpdate();
    }

    public static boolean updateWorker(Connection connection, int id, String name, Coordinates coordinates, float salary,
                                       LocalDate startDate, Date endDate, Status status,
                                       Person person, Location location) throws SQLException {

            connection.setAutoCommit(false);
            String sqlIds = """
                    SELECT coordinates_id, person_id FROM Workers WHERE id = ?;
                    """;
            PreparedStatement ids = connection.prepareStatement(sqlIds);
            ids.setInt(1, id);
            ResultSet idsResult = ids.executeQuery();
            idsResult.next();
            int coordinatesId = idsResult.getInt("coordinates_id");
            int personId = idsResult.getInt("person_id");
            String sqlLocId = """
                    SELECT location_id FROM Persons WHERE id = ?;
                    """;
            PreparedStatement locId = connection.prepareStatement(sqlLocId);
            locId.setInt(1, personId);
            ResultSet locIdResult = locId.executeQuery();
            locIdResult.next();
            int locationId = locIdResult.getInt("location_id");

            String sqlUpdateLocation = """
                    UPDATE locations SET x=?, y=?, z=? WHERE id=?;
                    """;
            PreparedStatement updateLocation = connection.prepareStatement(sqlUpdateLocation);
            updateLocation.setLong(1, location.getX());
            updateLocation.setDouble(2, location.getY());
            updateLocation.setInt(3, location.getZ());
            updateLocation.setInt(4, locationId);
            updateLocation.executeUpdate();

            String sqlUpdatePerson = """
                    UPDATE persons SET weight = ?, hairColor = ?, nationality = ?, location_id = ? WHERE id =?;
                    """;
            PreparedStatement updatePerson = connection.prepareStatement(sqlUpdatePerson);
            updatePerson.setDouble(1,person.getWeight());
            updatePerson.setString(2,person.getHairColor().toString());
            updatePerson.setString(3, person.getNationality().toString());
            updatePerson.setInt(4, locationId);
            updatePerson.setInt(5,personId);
            updatePerson.executeUpdate();

            String sqlUpdateCoordinates = """
                    UPDATE Coordinates SET x = ?, y = ? WHERE id = ?;
                    """;
            PreparedStatement updateCoordinates = connection.prepareStatement(sqlUpdateCoordinates);
            updateCoordinates.setLong(1, coordinates.getX());
            updateCoordinates.setInt(2, coordinates.getY());
            updateCoordinates.setInt(3, coordinatesId);
            updateCoordinates.executeUpdate();

            String sqlUpdateWorker = """
                    UPDATE Workers SET name =?, salary = ?, startDate =?, endDate=?, status=? WHERE id=?;
                    """;
            PreparedStatement updateWorker = connection.prepareStatement(sqlUpdateWorker);
            updateWorker.setString(1, name);
            updateWorker.setFloat(2, salary);
            updateWorker.setDate(3, Date.valueOf(String.valueOf(startDate)));
            updateWorker.setDate(4, Date.valueOf(endDate.toLocalDate()));
            updateWorker.setString(5, status.toString());
            updateWorker.setInt(6, id);
            updateWorker.executeUpdate();

            connection.commit();
            connection.setAutoCommit(false);
            return true;

    }

}

