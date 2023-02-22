package com.mitrian.lab.source;

public class Location {
    private int x;
    private long y;
    private float z;

    private Location(){}

    public int getX(){
        return x;
    }

    public long getY(){
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

        public Builder setX(int x){
            Location.this.x = x;

            return this;
        }

        public Builder setY(long y){
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
}