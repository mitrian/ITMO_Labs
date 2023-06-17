package com.mitrian.server.database;

import com.mitrian.common.elements.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

public class DatabaseManager {

    private  DBConnectionManagerImpl databaseConnectionManager;

    public DatabaseManager(DBConnectionManagerImpl databaseConnectionManager) {
        this.databaseConnectionManager = databaseConnectionManager;
    }

    public List<Worker> getAllWorkers(Connection connection) throws SQLException {
        String sqlGetWorkers = """
                SELECT w.id, w.name, c.x_coordinates, c.y_coordinates, w.creationDate, w.salary, w.startDate, w.endDate, w.status,
                p.weight, p.hairColor, p.nationality, l.x, l.y, l.z
                  FROM Workes w 
                  JOIN Coordinates c ON c.id = w.coordinates_id
                  JOIN Persons p ON p.id = w.person_id
                  JOIN Locations l ON p.location_id = l.id;
                """;
        PreparedStatement getWorkers = connection.prepareStatement(sqlGetWorkers);
        ResultSet workerFields = getWorkers.executeQuery();
        List<Worker> workers = new LinkedList<>();
        while (workerFields.next()){
            String workerName = workerFields.getString("name");
            long coordinatesX = workerFields.getLong("x_coordinates");
            int coordinatesY = workerFields.getInt("x_coordinates");
            LocalDate creationDate = ((Date) workerFields.getDate("creationDate")).toLocalDate();
            Float salary = workerFields.getFloat("salary");
            LocalDate startDate = ((Date) workerFields.getDate("startDate")).toLocalDate();
            Date endDate = workerFields.getDate("endDate");
            Status status = Status.valueOf(workerFields.getString("status"));
            Double weight = workerFields.getDouble("weight");
            Color hairColor = Color.valueOf(workerFields.getString("hairColor"));
            Country nationality = Country.valueOf(workerFields.getString("nationality"));
            long locationX = workerFields.getInt("x");
            long locationY = workerFields.getLong("y");
            int locationZ = workerFields.getInt("z");
            Location location =  Location.newBuilder()
                    .setX(locationX)
                    .setY(locationY)
                    .setZ(locationZ)
                    .build();
            Person person = new Person.Builder(weight, hairColor)
                    .setLocation(location)
                    .setNationality(nationality)
                    .build();
            Coordinates coordinates = Coordinates.newBuilder().setX(coordinatesX).setY(coordinatesY).build();
            Worker worker = new Worker.Builder(workerName,coordinates,startDate,person)
                    .setFileCreationDate(creationDate)
                    .setEndDate(endDate)
                    .setSalary(salary)
                    .setStatus(status)
                    .build();
            workers.add(worker);
        }
        return workers;
    }

    public void deleteAllWorkers(Connection connection){

    }

    public int insertWorker(Worker worker) throws SQLException {
        Connection connection = databaseConnectionManager.getConnection();
        try (connection){
            connection.setAutoCommit(false);
            int workerId = insertWorker(worker, connection);
            connection.commit();
            connection.setAutoCommit(true);
            return workerId;
        }
    }

    public void createTablesIfNotExists() throws SQLException{
        Connection connection = databaseConnectionManager.getConnection();
        try (connection) {
            connection.setAutoCommit(false);
            createLocationsTable(connection);
            createCoordinatesTable(connection);
            createPersonsTable(connection);
            createWorkersTable(connection);
            connection.commit();
            connection.setAutoCommit(true);

        }
    }


    public  void createLocationsTable(Connection connection) throws SQLException {
        String sqlLocationsTable = """
                CREATE TABLE IF NOT EXISTS Locations(id SERIAL PRIMARY KEY, x BIGSERIAL, y DOUBLE PRECISION, z INT);
                """;
        PreparedStatement createLocations = connection.prepareStatement(sqlLocationsTable);
        createLocations.executeUpdate();
    }

    public void createCoordinatesTable(Connection connection) throws SQLException {
        String sqlCoordinatesTable = """
                CREATE TABLE IF NOT EXISTS Coordinates(
                id SERIAL PRIMARY KEY, 
                x_coordinates INT CHECK (x <= 884),
                y_coordinates BIGINT NOT NULL
                );
                """;
        PreparedStatement createCoordinates = connection.prepareStatement(sqlCoordinatesTable);
        createCoordinates.executeUpdate();
    }

    public void  createPersonsTable(Connection connection) throws SQLException {
        String sqlPersonsTable = """
                CREATE TABLE IF NOT EXISTS Persons(id SERIAL PRIMARY KEY, weight DOUBLE PRECISION CHECK (weqight>0), 
                hairColor VARCHAR(31), nationality VARCHAR(31), location_id SERIAL REFERENCES Locations);
                """;
        PreparedStatement createPersons = connection.prepareStatement(sqlPersonsTable);
        createPersons.executeUpdate();
    }

    public void createWorkersTable (Connection connection) throws SQLException {
        String sqlWorkersTable = """
                CREATE TABLE IF NOT EXISTS Workers(id SERIAL PRIMARY KEY, name VARCHAR NOT NULL CHECK (length(name)>0), 
                coordionates_id BIGSERIAL NOT NULL REFERENCES Coordinates, creationDate DATE NOT NULL, salary FLOAT CHECK(salary>0), 
                startDate DATE NOT NULL, endDate Date, status VARCHAR(31), person_id SERIAL NOT NULL REFERENCES Persons);
                """;
        PreparedStatement createWorkers = connection.prepareStatement(sqlWorkersTable);
        createWorkers.executeUpdate();
    }

    private int insertWorker(Worker worker, Connection connection) throws SQLException {
        long coordinatesId = insertCoordinates(worker.getCoordinates(), connection);
        long personId = insertPerson(worker.getPerson(), connection);
        String sqlInsertWorker = """
                INSERT INTO Workers(name, coordintes_id, creationDate, salary, startDate, endDate, 
                status, person_id);
                """;
        PreparedStatement insertWorkers = connection.prepareStatement(sqlInsertWorker);
        insertWorkers.setString(1,worker.getName());
        insertWorkers.setLong(2,coordinatesId);
        insertWorkers.setDate(3, Date.valueOf(worker.getCreationDate()));
        insertWorkers.setFloat(4, worker.getSalary());
        insertWorkers.setDate(5, (Date) worker.getEndDate());
        insertWorkers.setString(6, worker.getStatus().toString()); //TODO
        insertWorkers.setLong(7, personId);
        ResultSet resultWorkers = insertWorkers.executeQuery();
        resultWorkers.next();
        return resultWorkers.getInt("id");
    }

    private int insertCoordinates(Coordinates coordinates, Connection connection) throws SQLException {
        String sqlInsertCoordinates = """
                INSERT INTO Coordinates(x_coordinates,y_coordinates) VALUES (?,?,?) RETURNING ig;
                """;
        PreparedStatement insertCoorindates = connection.prepareStatement(sqlInsertCoordinates);
        insertCoorindates.setLong(1, coordinates.getX());
        insertCoorindates.setInt(2, coordinates.getY());
        ResultSet resultCoordinates = insertCoorindates.executeQuery();
        resultCoordinates.next();
        return resultCoordinates.getInt("id");
    }

    private int insertPerson(Person person, Connection connection) throws SQLException {
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

    private int insertLocation(Location location, Connection connection) throws SQLException {
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

    public void deleteWorker(int workerId) throws SQLException{
        Connection connection = databaseConnectionManager.getConnection();
        try (connection){
            connection.setAutoCommit(false);
            deleteWorker(workerId, connection);
            connection.commit();
            connection.setAutoCommit(true);;
        }
    }

    private void deleteWorker(int workerId, Connection connection) throws SQLException {
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

    private void deletePersons(int personsId, Connection connection) throws SQLException {
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

    private void deleteCoordinates(int coordinatesId, Connection connection) throws SQLException {
        String sqlDeleteCoordinates = """
                DELETE FROM Coordinates WHERE id = ?;
                """;
        PreparedStatement deleteCoordinates = connection.prepareStatement(sqlDeleteCoordinates);
        deleteCoordinates.setInt(1, coordinatesId);
        deleteCoordinates.executeUpdate();
    }

    private void deleteLocation(int locationId, Connection connection) throws SQLException {
        String sqlDeleteLocation = """
                DELETE FROM Locations WHERE id = ?;
                """;
        PreparedStatement deleteLocation = connection.prepareStatement(sqlDeleteLocation);
        deleteLocation.setInt(1,locationId);
        deleteLocation.executeUpdate();
    }

    public boolean updateWorker(int id, String name, Coordinates coordinates, float salary,
                                LocalDate startDate, Date endDate, Status status,
                                Person person, Location location) throws SQLException {
        Connection connection = databaseConnectionManager.getConnection();
        try (connection) {
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
                    UPDATE Coordinates SET x_coordinates = ?, y_coordinates = ? WHERE id = ?;
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
}