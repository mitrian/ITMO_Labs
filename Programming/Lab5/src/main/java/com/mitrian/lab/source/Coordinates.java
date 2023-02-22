package com.mitrian.lab.source;
public class Coordinates {
    private int x; //Максимальное значение поля: 884
    private Long y; //Поле не может быть null

    private Coordinates(){}
    public static boolean checkX(int x){
        return x<=884;
    }

    public int getX(){
        return x;
    }

    public Long getY(){
        return y;
    }

    public static Builder newBuilder(){
        return new Coordinates().new Builder();
    }

    public class Builder{

        private Builder(){}

        public Builder setX(int x){
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
}