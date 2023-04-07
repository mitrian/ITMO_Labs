package com.mitrian.lab.common.elements;
public class Coordinates {
    private Long x; //Максимальное значение поля: 884
    private Integer y; //Поле не может быть null

    private Coordinates(){}
    public static boolean checkX(int x){
        return x<=884;
    }

    public Long getX(){
        return x;
    }

    public Integer getY(){
        return y;
    }

    public void setX(Long x){this.x = x;}
    public void setY(Integer y){this.y = y;}

    public static Builder newBuilder(){
        return new Coordinates().new Builder();
    }

    public  class Builder{

        public Builder(){}

        public Builder setX(Long x){
            Coordinates.this.x = x;
            return this;
        }

        public Builder setY(Integer y){
            Coordinates.this.y = y;
            return this;
        }

        public Coordinates build(){
            return Coordinates.this;
        }

    }
    @Override
    public String toString(){
        return "(x = " + x +
                ", y = " + y + ")";
    }
}