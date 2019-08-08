package net.svendsen.oyvind.educational.planetposition.converter;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
class Spherical {
    private double r,theta,phi;
}
