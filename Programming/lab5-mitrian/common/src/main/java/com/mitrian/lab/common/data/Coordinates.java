package com.mitrian.lab.common.data;
public class Coordinates {
    private long x; //Максимальное значение поля: 884
    private Long y; //Поле не может быть null

    private Coordinates(){}
    public static boolean checkX(int x){
        return x<=884;
    }

    public long getX(){
        return x;
    }

    public Long getY(){
        return y;
    }

    public void setX(int x){this.x = x;}
    public void setY(Long y){this.y = y;}

    public static Builder newBuilder(){
        return new Coordinates().new Builder();
    }

    public  class Builder{

        public Builder(){}

        public Builder setX(long x){
            Coordinates.this.x = x;
            return this;
        }

        public Builder setY(Long y){
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