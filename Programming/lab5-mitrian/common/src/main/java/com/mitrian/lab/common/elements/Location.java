package com.mitrian.lab.common.elements;

public class Location {
    private Long x;
    private double y;
    private int z;

    private Location(){}

    public Long getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public int getZ(){
        return z;
    }

    public void setX(long x){ this.x = x;}
    public void setY(double y){this.y = y;}
    public void setF(int z){ this.z = z;}

    public static Builder newBuilder(){
        return new Location().new Builder();
    }

    public class Builder{

        private Builder(){}

        public Builder setX(Long x){
            Location.this.x = x;

            return this;
        }

        public Builder setY(double y){
            Location.this.y = y;

            return this;
        }

        public Builder setZ(int z){
            Location.this.z = z;
            return this;
        }

        public Location build(){
            return Location.this;
        }
    }

    @Override
    public String toString(){
        return "(x = " + x +
                ", y = " + y +
                ", z = " + z + ")";

    }
}