package net.svendsen.oyvind.educational.planetposition.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Helioweb {

    private long year;
    private long dayOfYear;
    private double radiusAU;
    private double e_latitude;
    private double e_longitude;
    private double hg_latitude;
    private double hg_longitude;
    private double hgi_longitude;

}
