package net.svendsen.oyvind.educational.planetposition.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlanetPositionXYZ {
    private long year;
    private long dayOfYear;

    private double xAU;
    private double yAU;
    private double ZAU;
}
