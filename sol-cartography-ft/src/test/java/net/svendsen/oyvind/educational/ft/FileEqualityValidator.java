package net.svendsen.oyvind.educational.ft;

import lombok.Builder;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

public class FileEqualityValidator {

    private static final double RESOLUTION_THRESHOLD = 0.05;

    private Resource file;

    private List<String> fileContent              = new ArrayList<>();

    private Resource     expectedFile;

    private List<String> expectedFileContent      = new ArrayList<>();

    @Builder
    private FileEqualityValidator(Resource file, Resource expectedFile) {
        this.file = file;
        this.expectedFile = expectedFile;
        try {
            readFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isEqual() {
        if (fileContent.size() != expectedFileContent.size()) { return Boolean.FALSE; }
        return IntStream.range(0, fileContent.size())
                    .allMatch(i -> {
                        String[] content = fileContent.get(i).split(",");
                        String[] expectedContent = expectedFileContent.get(i).split(",");
                        if ((content.length != expectedContent.length) && content.length > 2) { return Boolean.FALSE; }
                        return IntStream.range(0,2)
                                .allMatch(j -> Long.parseLong(content[j]) == Long.parseLong(expectedContent[j]))
                            &&
                                IntStream.range(2,content.length)
                                        .allMatch(j -> {
                                            double value = Double.parseDouble(content[j]);
                                            double expectedValue = Double.parseDouble(expectedContent[j]);
                                            return Math.abs(value - expectedValue) < RESOLUTION_THRESHOLD;
                                        });
                    });
    }

    private void readFiles() throws IOException {
        fileContent.addAll(Files.lines(file.getFile().toPath(), ISO_8859_1)
                .collect(Collectors.toList()));
        expectedFileContent.addAll(Files.lines(expectedFile.getFile().toPath(), ISO_8859_1)
                .collect(Collectors.toList()));
    }

}
