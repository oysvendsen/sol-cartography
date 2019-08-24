package net.svendsen.oyvind.educational.planetposition.converter;

import net.svendsen.oyvind.educational.planetposition.domain.Helioweb;
import net.svendsen.oyvind.educational.planetposition.domain.PlanetPositionXYZ;
import org.springframework.stereotype.Component;

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
