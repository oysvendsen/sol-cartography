package net.svendsen.oyvind.educational.planetposition.converter;

import net.svendsen.oyvind.educational.planetposition.domain.PlanetPositionXYZ;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.stereotype.Component;

@Component
public class PlanetPositionToCsvConverter implements LineAggregator<PlanetPositionXYZ> {

    public String convert(PlanetPositionXYZ planetPositionXYZ) {
        return String.join(",", toArray(planetPositionXYZ));
    }

    private String[] toArray(PlanetPositionXYZ planetPositionXYZ) {
        return new String[]{
                ((Long) planetPositionXYZ.getYear()).toString(),
                ((Long) planetPositionXYZ.getDayOfYear()).toString(),
                ((Double) planetPositionXYZ.getXAU()).toString(),
                ((Double) planetPositionXYZ.getYAU()).toString(),
                ((Double) planetPositionXYZ.getZAU()).toString(),
        };
    }

    @Override
    public String aggregate(PlanetPositionXYZ item) {
        String convert = convert(item);
        return convert;
    }
}
