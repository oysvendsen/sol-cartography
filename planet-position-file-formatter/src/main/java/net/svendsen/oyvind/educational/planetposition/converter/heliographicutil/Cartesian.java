package net.svendsen.oyvind.educational.planetposition.converter.heliographicutil;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Cartesian {
    private double x,y,z;
    public Cartesian cross(Cartesian vector) {
        return Cartesian.builder()
                .x(this.y*vector.getZ() - this.z*vector.getY())
                .y(this.z*vector.getX() - this.x*vector.getZ())
                .z(this.x*vector.getY() - this.y*vector.getX())
                .build();
    }
    public double dot(Cartesian vector) {
        return x*vector.getX() + y*vector.getY() + z*vector.getZ();
    }
    public Cartesian add(Cartesian add) {
        x += add.getX();
        y += add.getY();
        z += add.getZ();
        return this;
    }
    public Cartesian multiply(double mult) {
        x *= mult;
        y *= mult;
        z *= mult;
        return this;
    }

    public static Cartesian XVector() {
        return Cartesian.builder()
                .x(1.0)
                .y(0.0)
                .z(0.0)
                .build();
    }

    public static Cartesian ZVector() {
        return Cartesian.builder()
                .x(0.0)
                .y(0.0)
                .z(1.0)
                .build();
    }


}
