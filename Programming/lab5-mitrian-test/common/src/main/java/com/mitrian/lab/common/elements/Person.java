package com.mitrian.lab.common.elements;

import java.io.Serializable;

public class Person implements Serializable {
    private Double weight; //Поле не может быть null, Значение поля должно быть больше 0
    private Color hairColor; //Поле не может быть null
    private Country nationality; //Поле может быть null
    private Location location; //Поле может быть null


    private Person(Builder builder){
        this.weight = builder.weight;
        this.hairColor = builder.hairColor;
        this.nationality = builder.nationality;
        this.location = builder.location;
    }


    public Double getWeight(){
        return weight;
    }

    public Color getHairColor(){
        return hairColor;
    }

    public Country getNationality(){
        return nationality;
    }

    public Location getLocation(){
        return location;
    }

    public void setWeight(Double weight){ this.weight = weight;}
    public void setHairColor(Color hairColor){ this.hairColor = hairColor;}
    public void setNationality(Country nationality){ this.nationality = nationality;}
    public void setLocation(Location location){ this.location = location;}

    public static class Builder{

        private Double weight; //Поле не может быть null, Значение поля должно быть больше 0
        private Color hairColor; //Поле не может быть null
        private Country nationality; //Поле может быть null
        private Location location; //Поле может быть null
        public Builder(Double weight, Color hairColor){
            this.weight = weight;
            this.hairColor = hairColor;
        }


        public Builder setNationality(Country nationality){
            this.nationality = nationality;

            return this;
        }

        public Builder setLocation(Location location){
            this.location = location;

            return this;
        }

        public Person build(){
            return new Person(this);
        }
    }

    @Override
    public String toString(){
        return "(weight = " + weight +
                ", hairColor = " + hairColor +
                ", nationality = " + nationality +
                ", location = " + location + ")";
    }
}
