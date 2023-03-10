package com.mitrian.lab.source;

import java.time.ZonedDateTime;
import java.util.Date;

public class Worker implements Comparable<Worker> {

    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer salary; //Поле может быть null, Значение поля должно быть больше 0
    private java.time.ZonedDateTime startDate; //Поле не может быть null
    private java.util.Date endDate; //Поле может быть null
    private Status status; //Поле может быть null
    private Person person; //Поле не может быть null




    private Worker(Builder builder) {
        this.name = builder.name;
        this.coordinates = builder.coordinates;
        this.creationDate = ZonedDateTime.now();
        this.startDate = builder.startDate;
        this.person = builder.person;
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Coordinates getCoordinates(){
        return coordinates;
    }

    public ZonedDateTime getCreationDate(){
        return creationDate;
    }

    public Integer getSalary(){
        return salary;
    }

    public ZonedDateTime getStartDate(){
        return startDate;
    }

    public Date getEndDate(){
        return endDate;
    }

    public Status getStatus(){
        return status;
    }

    public Person getPerson(){
        return person;
    }


//    public static Builder newBuilder(){
//        return new Worker().new Builder();
//    }
    @Override
    public int compareTo(Worker o) {
        // todo
        return this.id.compareTo(o.id);
    }

    public static boolean checkSalary(int salary){
        return salary>0;
    }
    public static class Builder{

        private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
        private String name; //Поле не может быть null, Строка не может быть пустой
        private Coordinates coordinates; //Поле не может быть null
        private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
        private Integer salary; //Поле может быть null, Значение поля должно быть больше 0
        private java.time.ZonedDateTime startDate; //Поле не может быть null
        private java.util.Date endDate; //Поле может быть null
        private Status status; //Поле может быть null
        private Person person; //Поле не может быть null

        public Builder(String name, Coordinates coordinates,ZonedDateTime startDate, Person person){
            this.name = name;
            this.coordinates = coordinates;
            this.startDate = startDate;
            this.person = person;
        }

//        public Builder setName(String name){
//            Worker.this.name = name;
//
//            return this;
//        }

//        public Builder setCoordinates(Coordinates coordinates){
//            Worker.this.coordinates = coordinates;
//
//            return this;
//        }

        public Builder setCreationDate(ZonedDateTime now){
            this.creationDate = ZonedDateTime.now();

            return this;
        }

//        public Builder setStartDate(ZonedDateTime startDate){
//            Worker.this.startDate = startDate;
//
//            return this;
//        }

//        public Builder setPerson(Person person){
//            Worker.this.person = person;
//
//            return this;
//        }

        public Builder setSalary(int salary){
            this.salary = salary;

            return this;
        }


        public Builder setEndDate(Date endDate){
            this.endDate = endDate;

            return this;
        }


        public Builder setStatus(Status status){
            this.status = status;

            return this;
        }

        public Worker build(){
            return new  Worker(this);
        }

    }
    @Override
    public String toString(){
        return "id = " + id +
                ", name = " + name +
                ", coordinates = " + coordinates.toString() +
                ", creationDate = " + creationDate +
                ", salary = " + salary +
                ", startDate = " + startDate +
                ", endDate = " + endDate +
                ", status = " + status +
                ", person = " + person.toString();
    }
}