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
public abstract class HeliowebToPlanetPositionXYZConverter {

    public PlanetPositionXYZ convert(Helioweb helioweb) {
        return PlanetPositionXYZ.builder()
                .year(helioweb.getYear())
                .dayOfYear(helioweb.getDayOfYear())
                .xAU(calculateXCoordinate(helioweb))
                .yAU(calculateYCoordinate(helioweb))
                .ZAU(calculateZCoordinate(helioweb))
                .build();
    }

    protected abstract double calculateXCoordinate(Helioweb helioweb);
    protected abstract double calculateYCoordinate(Helioweb helioweb);
    protected abstract double calculateZCoordinate(Helioweb helioweb);

}
