package net.svendsen.oyvind.educational.planetposition.converter;

import net.svendsen.oyvind.educational.planetposition.domain.Helioweb;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
public class HeliowebFieldSetMapper implements FieldSetMapper<Helioweb> {

    @Override
    public Helioweb mapFieldSet(FieldSet fieldSet) throws BindException {
        return Helioweb.builder()
                .year(getYear(fieldSet))
                .dayOfYear(getDayOfYear(fieldSet))
                .radiusAU(getRadius(fieldSet))
                .e_latitude(getELatitude(fieldSet))
                .e_longitude(getELongitude(fieldSet))
                .hg_latitude(getHGLatitude(fieldSet))
                .hg_longitude(getHGLongitude(fieldSet))
                .hgi_longitude(getHGILongitude(fieldSet))
                .build();
    }

    private long getYear(FieldSet fieldSet) {
        return Long.parseLong(fieldSet.readString("YYYY").trim());
    }

    private long getDayOfYear(FieldSet fieldSet) {
        return Long.parseLong(fieldSet.readString("DOY").trim());
    }

    private double getRadius(FieldSet fieldSet) {
        return Double.parseDouble(fieldSet.readString("AU").trim());
    }

    private double getELatitude(FieldSet fieldSet) {
        return Double.parseDouble(fieldSet.readString("ELAT").trim());
    }

    private double getELongitude(FieldSet fieldSet) {
        return Double.parseDouble(fieldSet.readString("ELON").trim());
    }

    private double getHGLatitude(FieldSet fieldSet) {
        return Double.parseDouble(fieldSet.readString("HG_LAT").trim());
    }

    private double getHGLongitude(FieldSet fieldSet) {
        return Double.parseDouble(fieldSet.readString("HG_LON").trim());
    }

    private Double getHGILongitude(FieldSet fieldSet) {
        return Double.parseDouble(fieldSet.readString("HGI_LON").trim());
    }
}
