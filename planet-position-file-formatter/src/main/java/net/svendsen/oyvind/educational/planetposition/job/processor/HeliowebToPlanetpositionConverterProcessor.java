package net.svendsen.oyvind.educational.planetposition.job.processor;

import net.svendsen.oyvind.educational.planetposition.converter.HeliowebToPlanetPositionXYZConverter;
import net.svendsen.oyvind.educational.planetposition.domain.PlanetPositionXYZ;
import net.svendsen.oyvind.educational.planetposition.domain.Helioweb;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class HeliowebToPlanetpositionConverterProcessor implements ItemProcessor<Helioweb, PlanetPositionXYZ> {

    private HeliowebToPlanetPositionXYZConverter heliowebConverter;

    public HeliowebToPlanetpositionConverterProcessor(HeliowebToPlanetPositionXYZConverter heliowebConverter) {
        this.heliowebConverter = heliowebConverter;
    }

    @Override
    public PlanetPositionXYZ process(Helioweb helioweb) {
        return heliowebConverter.convert(helioweb);
    }
}
