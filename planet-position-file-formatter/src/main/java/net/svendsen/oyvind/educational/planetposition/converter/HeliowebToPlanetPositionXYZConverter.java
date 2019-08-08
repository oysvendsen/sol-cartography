package net.svendsen.oyvind.educational.planetposition.converter;

import net.svendsen.oyvind.educational.planetposition.domain.Helioweb;
import net.svendsen.oyvind.educational.planetposition.domain.PlanetPositionXYZ;
import org.springframework.stereotype.Component;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

@Component
public class HeliowebToPlanetPositionXYZConverter {

    public PlanetPositionXYZ convert(Helioweb helioweb) {
        return PlanetPositionXYZ.builder()
                .year(helioweb.getYear())
                .dayOfYear(helioweb.getDayOfYear())
                .xAU(calculateXCoordinate(helioweb))
                .yAU(calculateYCoordinate(helioweb))
                .ZAU(calculateZCoordinate(helioweb))
                .build();
    }

    private static double calculateXCoordinate(Helioweb helioweb) {
        double x = helioweb.getRadiusAU() * cos(getPhiRadians(helioweb)) * cos(getThetaRadians(helioweb));
        return x;
    }

    private static double calculateYCoordinate(Helioweb helioweb) {
        double y = helioweb.getRadiusAU() * cos(getPhiRadians(helioweb)) * sin(getThetaRadians(helioweb));
        return y;
    }

    private static double calculateZCoordinate(Helioweb helioweb) {
        double z = helioweb.getRadiusAU() * sin(getPhiRadians(helioweb));
        return z;
    }

    private static double getPhiRadians(Helioweb helioweb) {
        return toRadians(helioweb.getE_latitude());
    }
    private static double getThetaRadians(Helioweb helioweb) {
        return toRadians(helioweb.getE_longitude());
    }

}
