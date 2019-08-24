package net.svendsen.oyvind.educational.planetposition.converter;

import net.svendsen.oyvind.educational.planetposition.domain.Helioweb;
import net.svendsen.oyvind.educational.planetposition.domain.PlanetPositionXYZ;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

@Component
@Profile("SE")
public class SolarEclipticImplementation extends HeliowebToPlanetPositionXYZConverter {

    @Override
    protected double calculateXCoordinate(Helioweb helioweb) {
        double x = helioweb.getRadiusAU() * cos(getPhiRadians(helioweb)) * cos(getThetaRadians(helioweb));
        return x;
    }

    @Override
    protected double calculateYCoordinate(Helioweb helioweb) {
        double y = helioweb.getRadiusAU() * cos(getPhiRadians(helioweb)) * sin(getThetaRadians(helioweb));
        return y;
    }

    @Override
    protected double calculateZCoordinate(Helioweb helioweb) {
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
