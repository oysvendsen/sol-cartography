package net.svendsen.oyvind.educational.planetposition.converter;

import net.svendsen.oyvind.educational.planetposition.domain.Helioweb;
import net.svendsen.oyvind.educational.planetposition.domain.PlanetPositionXYZ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeliowebToPlanetPositionXYZConverterTest {

    private static long DEFAULT_YEAR = 2000L;
    private static long DEFAULT_DAY = 200L;

    private SolarEclipticImplementation heliowebConverter;

    @BeforeEach
    void setUp() {
        heliowebConverter = new SolarEclipticImplementation();
    }

    @ParameterizedTest
    @MethodSource("provideCartesianSphericalPairs")
    void convert_givenSphericalCoordinates_returnsCartesionCoordinates(Cartesian cartesian, Spherical spherical) {
        Helioweb helioweb = Helioweb.builder()
                .year(DEFAULT_YEAR)
                .dayOfYear(DEFAULT_DAY)
                .radiusAU(spherical.getR())
                .e_latitude(spherical.getPhi())
                .e_longitude(spherical.getTheta())
                .build();
        PlanetPositionXYZ planetPositionXYZ = heliowebConverter.convert(helioweb);
        Cartesian result = new Cartesian(planetPositionXYZ.getXAU(), planetPositionXYZ.getYAU(), planetPositionXYZ.getZAU());
        assertEquals(cartesian, result, "From " + spherical + " the resulting cartesian was calculated to be " + result + " but should be " + cartesian);
    }

    private static Stream<Arguments> provideCartesianSphericalPairs() {
        return Stream.of(
                Arguments.of(new Cartesian(1,0,0), new Spherical(1,0,0)),
                Arguments.of(new Cartesian(0,1,0), new Spherical(1,90,0)),
                Arguments.of(new Cartesian(0,0,1), new Spherical(1,0,90)),
                Arguments.of(new Cartesian(0,0,-1), new Spherical(1,0,-90)),
                Arguments.of(new Cartesian(0,0,0), new Spherical(0,0,0)),
                Arguments.of(new Cartesian(-1,0,0), new Spherical(1,180,0)),
                Arguments.of(new Cartesian(0,-1,0), new Spherical(1,270,0))
        );
    }

}