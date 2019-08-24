package net.svendsen.oyvind.educational.planetposition.converter;

import net.svendsen.oyvind.educational.planetposition.converter.heliographicutil.Cartesian;
import net.svendsen.oyvind.educational.planetposition.converter.heliographicutil.Polar;
import net.svendsen.oyvind.educational.planetposition.domain.Helioweb;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

@Component
@Profile("HGI")
public class HelioGraphicInertialImplementation extends HeliowebToPlanetPositionXYZConverter {

    static final double                      HELIOGRAPHIC_TILT_IN_ECLIPLTIC_DEGREES    = 7.25;
    static final double                      INITIAL_HELIOGRAPHIC_ROTATION_DEGREES     = 74.367;
    static final double                      HELIOGRAPHIC_ROTATION_CENTENNIAL_VELOCITY = 1.4;
    static final LocalDate                   INITIAL_HELIOGRAPHIC_ROTATION_DATE        = LocalDate.of(1900, 1, 1);
    static final long                        DAYS_IN_CENTURY                           = 100 * 365 + 25 - 1;
    static final Function<LocalDate, Double> HELIOGRAPHIC_ROTATION_DELTATIME           = (date) -> ChronoUnit.DAYS.between(INITIAL_HELIOGRAPHIC_ROTATION_DATE, date)
            / ((double) DAYS_IN_CENTURY);
    static final Function<Double, Double>    HELIOGRAPHIC_ROTATION_IN_ECLIPTIC_DEGREES = (deltaTimeCenturies) -> INITIAL_HELIOGRAPHIC_ROTATION_DEGREES + HELIOGRAPHIC_ROTATION_CENTENNIAL_VELOCITY*deltaTimeCenturies;

    @Override
    protected double calculateXCoordinate(Helioweb helioweb) {
        return rotateFromHelioGraphicToCartesian(helioweb).getX();
    }

    @Override
    protected double calculateYCoordinate(Helioweb helioweb) {
        return rotateFromHelioGraphicToCartesian(helioweb).getY();
    }

    @Override
    protected double calculateZCoordinate(Helioweb helioweb) {
        return rotateFromHelioGraphicToCartesian(helioweb).getZ();
    }

    private Cartesian rotateFromHelioGraphicToCartesian(Helioweb helioweb) {
        LocalDate date = LocalDate.ofYearDay(Long.valueOf(helioweb.getYear()).intValue(), Long.valueOf(helioweb.getDayOfYear()).intValue());
        Double deltaTime = HELIOGRAPHIC_ROTATION_DELTATIME.apply(date);
        Cartesian cartesian = Polar.fromHelioweb(helioweb).toCartesian();
        Cartesian rotatedAlongXAxis = rotate(cartesian, Cartesian.XVector(), toRadians(HELIOGRAPHIC_TILT_IN_ECLIPLTIC_DEGREES));
        Cartesian rotatedAlongZAxis = rotate(rotatedAlongXAxis, Cartesian.ZVector(), toRadians(HELIOGRAPHIC_ROTATION_IN_ECLIPTIC_DEGREES.apply(deltaTime)));
        return rotatedAlongZAxis;
    }

    public static Cartesian rotate(Cartesian point, Cartesian aboutVector, double radians) {
        double ka = aboutVector.dot(point);
        Cartesian kxa = aboutVector.cross(point);
        Cartesian comp1 = point.multiply(cos(radians));
        Cartesian comp2 = kxa.multiply(sin(radians));
        Cartesian comp3 = aboutVector.multiply(ka * (1 - cos(radians)));
        return comp1.add(comp2).add(comp3);
    }

}
