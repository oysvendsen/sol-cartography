import net.svendsen.oyvind.educational.planetposition.PlanetPositionJobRunner;
import org.springframework.boot.SpringApplication;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            SpringApplication.run(PlanetPositionJobRunner.class, "inputFilename=earth_hg_single_datapoint.txt", "outputFilename=earth_hg_single_datapoint.csv");
        } else {
            SpringApplication.run(PlanetPositionJobRunner.class, args);
        }
    }

}
