package net.svendsen.oyvind.educational.planetposition.converter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
class Cartesian {
    private static double WIGGLE_ROOM = 1e-10;
    private double x,y,z;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cartesian) {
            return (compareX((Cartesian) obj)) && (compareY((Cartesian) obj)) && (compareZ((Cartesian) obj));
        } else {
            return super.equals(obj);
        }
    }

    private boolean compareX(Cartesian other) {
        return Math.abs(x - other.getX()) < WIGGLE_ROOM;
    }
    private boolean compareY(Cartesian other) {
        return Math.abs(y - other.getY()) < WIGGLE_ROOM;
    }
    private boolean compareZ(Cartesian other) {
        return Math.abs(z - other.getZ()) < WIGGLE_ROOM;
    }
}
