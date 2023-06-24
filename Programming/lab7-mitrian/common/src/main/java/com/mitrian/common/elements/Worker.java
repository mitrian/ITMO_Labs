package com.mitrian.common.elements;

import com.mitrian.common.elements.initializer.IdCollection;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Worker implements Comparable<Worker>, Serializable {

    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float salary; //Поле может быть null, Значение поля должно быть больше 0
    private LocalDate startDate; //Поле не может быть null
    private Date endDate; //Поле может быть null
    private Status status; //Поле может быть null
    private Person person; //Поле не может быть null

    private int userId;




    private Worker(Builder builder) {
        this.id = IdCollection.createWorkerId();
        this.name = builder.name;
        this.coordinates = builder.coordinates;
        this.creationDate = LocalDate.now();
        this.startDate = builder.startDate;
        this.person = builder.person;
        this.salary = builder.salary;
        this.status = builder.status;

    }

    public Integer getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Coordinates getCoordinates(){
        return coordinates;
    }

    public LocalDate getCreationDate(){
        return creationDate;
    }

    public int getUserId(){
        return this.userId;
    }

    public Float getSalary(){
        return salary;
    }

    public LocalDate getStartDate(){
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

    public void setId(Integer id){ this.id = id;
    IdCollection.idCollection.add(id);}
    public void setName(String name){ this.name = name;}
    public void setCoordinates(Coordinates coordinates){ this.coordinates = coordinates;}

   public void setCreationDate() {
        this.creationDate = LocalDate.now();
    }

    public void setSalary(Float salary){

        this.salary = salary;
    }
    public void setStartDate(LocalDate startDate){ this.startDate = startDate;}
    public void setEndDate(Date endDate) {this.endDate = endDate;}
    public void setStatus(Status status){ this.status = status;}
    public void setPerson(Person person){ this.person = person;}


    @Override
    public int compareTo(Worker o) {
        return this.name.compareTo(o.name);
    }

    public static class Builder{

        private String name; //Поле не может быть null, Строка не может быть пустой
        private Coordinates coordinates; //Поле не может быть null
        private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
        private Float salary; //Поле может быть null, Значение поля должно быть больше 0
        private LocalDate startDate; //Поле не может быть null
        private Date endDate; //Поле может быть null
        private Status status; //Поле может быть null
        private Person person; //Поле не может быть null

        private int userId;

        public Builder(String name, Coordinates coordinates, LocalDate startDate, Person person){
            this.name = name;
            this.coordinates = coordinates;
            this.startDate = startDate;
            this.person = person;
        }

        public Builder setCreationDate(){
            this.creationDate = creationDate;
            return this;
        }

        public Builder setUserId(){
            this.userId = userId;
            return this;
        }


        public Builder setFileCreationDate(LocalDate creationDate){
            this.creationDate = creationDate;
            return this;
        }


        public Builder setSalary(Float salary){
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

        public int getUserId(){
            return this.userId;
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
                ", person = " + person.toString()+"";
    }
}