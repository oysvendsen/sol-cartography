package net.svendsen.oyvind.educational.planetposition.converter.heliographicutil;

import lombok.Builder;
import lombok.Getter;
import net.svendsen.oyvind.educational.planetposition.domain.Helioweb;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

@Getter
@Builder
public class Polar {
    private double r, lat, lon;
    public Polar rotate(double radians) {
        lon += radians;
        return this;
    }
    public Cartesian toCartesian() {
        return Cartesian.builder()
                .x(r*cos(lat)*cos(lon))
                .y(r*cos(lat)*sin(lon))
                .z(r*sin(lat))
                .build();
    }
    public static Polar fromHelioweb(Helioweb helioweb) {
        return Polar.builder()
                .r(helioweb.getRadiusAU())
                .lat(toRadians(helioweb.getHg_latitude()))
                .lon(toRadians(helioweb.getHgi_longitude()))
                .build();
    }
}