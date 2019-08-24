package net.svendsen.oyvind.educational.planetposition.job.reader.fieldsetmapper;

import net.svendsen.oyvind.educational.planetposition.domain.Helioweb;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
@Qualifier("HGI")
public class HeliowebHelioGraphicInertialFieldSetMapper implements FieldSetMapper<Helioweb> {

    @Override
    public Helioweb mapFieldSet(FieldSet fieldSet) throws BindException {
        return Helioweb.builder()
                .year(getYear(fieldSet))
                .dayOfYear(getDayOfYear(fieldSet))
                .radiusAU(getRadius(fieldSet))
                .hg_latitude(getHGLatitude(fieldSet))
                .hgi_longitude(getHGILongitude(fieldSet))
                .build();
    }

    private long getYear(FieldSet fieldSet) {
        return Long.parseLong(fieldSet.readString("YEAR").trim());
    }

    private long getDayOfYear(FieldSet fieldSet) {
        return Long.parseLong(fieldSet.readString("DAY").trim());
    }

    private double getRadius(FieldSet fieldSet) {
        return Double.parseDouble(fieldSet.readString("RAD_AU").trim());
    }

    private double getHGLatitude(FieldSet fieldSet) {
        return Double.parseDouble(fieldSet.readString("HGI_LAT").trim());
    }

    private Double getHGILongitude(FieldSet fieldSet) {
        return Double.parseDouble(fieldSet.readString("HGI_LON").trim());
    }
}
