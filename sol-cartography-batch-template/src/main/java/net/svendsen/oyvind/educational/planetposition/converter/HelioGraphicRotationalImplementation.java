package net.svendsen.oyvind.educational.planetposition.converter;

import net.svendsen.oyvind.educational.planetposition.converter.heliographicutil.Cartesian;
import net.svendsen.oyvind.educational.planetposition.converter.heliographicutil.Polar;
import net.svendsen.oyvind.educational.planetposition.domain.Helioweb;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;

import static java.lang.Math.toRadians;
import static net.svendsen.oyvind.educational.planetposition.converter.HelioGraphicInertialImplementation.HELIOGRAPHIC_ROTATION_DELTATIME;
import static net.svendsen.oyvind.educational.planetposition.converter.HelioGraphicInertialImplementation.HELIOGRAPHIC_ROTATION_IN_ECLIPTIC_DEGREES;
import static net.svendsen.oyvind.educational.planetposition.converter.HelioGraphicInertialImplementation.HELIOGRAPHIC_TILT_IN_ECLIPLTIC_DEGREES;
import static net.svendsen.oyvind.educational.planetposition.converter.HelioGraphicInertialImplementation.rotate;

@Component
@Profile("HG")
public class HelioGraphicRotationalImplementation extends HeliowebToPlanetPositionXYZConverter {

    static final LocalDate                 INITIAL_SOLAR_EQUATOR_ROTATION_DATE   = LocalDate.of(1854, 1, 1);
    static final double                    INITIAL_SOLAR_EQUATOR_ROTATION_PERIOD = 25.38;
    static final Function<LocalDate, Long> SOLAR_EQUATOR_ROTATION_DELTATIME      = (date) -> ChronoUnit.DAYS.between(INITIAL_SOLAR_EQUATOR_ROTATION_DATE, date);
    static final Function<Long, Double>    SOLAR_EQUATOR_ROTATION_DEGREES        = (deltaTimeDays) -> {
        double deltaTime = BigDecimal.valueOf(deltaTimeDays).remainder(BigDecimal.valueOf(INITIAL_SOLAR_EQUATOR_ROTATION_PERIOD)).doubleValue();
        return 360 * deltaTime / INITIAL_SOLAR_EQUATOR_ROTATION_PERIOD;
    };

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
        LocalDate date = LocalDate.ofYearDay( ( (Long) helioweb.getYear()).intValue(), ( (Long) helioweb.getDayOfYear()).intValue());
        double deltaTimeHeliographic = HELIOGRAPHIC_ROTATION_DELTATIME.apply(date);
        long deltaTimeSolarEquatorial = SOLAR_EQUATOR_ROTATION_DELTATIME.apply(date);

        Cartesian cartesian = Polar.fromHelioweb(helioweb).rotate(toRadians(SOLAR_EQUATOR_ROTATION_DEGREES.apply(deltaTimeSolarEquatorial))).toCartesian();
        Cartesian rotatedAlongXAxis = rotate(cartesian, Cartesian.XVector(), toRadians(HELIOGRAPHIC_TILT_IN_ECLIPLTIC_DEGREES));
        return rotate(rotatedAlongXAxis, Cartesian.ZVector(), toRadians(HELIOGRAPHIC_ROTATION_IN_ECLIPTIC_DEGREES.apply(deltaTimeHeliographic)));
    }

}
