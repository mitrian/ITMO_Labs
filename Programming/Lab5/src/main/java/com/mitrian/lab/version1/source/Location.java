package com.mitrian.lab.version1.source;

public class Location {
    private long x;
    private int y;
    private float z;

    private Location(){}

    public long getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public float getZ(){
        return z;
    }

    public static Builder newBuilder(){
        return new Location().new Builder();
    }

    public class Builder{

        private Builder(){}

        public Builder setX(long x){
            Location.this.x = x;

            return this;
        }

        public Builder setY(int y){
            Location.this.y = y;

            return this;
        }

        public Builder setZ(float z){
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