package org.root.csv;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public class CsvTest {
    public static void main(String[] args) throws IOException {
        File file = File.createTempFile("sample",".scv");
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        csvMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        csvMapper.findAndRegisterModules();

        CsvSchema csvSchema = csvMapper.schemaFor(LocationDto.class)
                .withHeader()
                .withColumnSeparator(',');
        System.out.println(file.getAbsolutePath());

        //.writeValues(new OutputStreamWriter(out, StandardCharsets.UTF_8))
//        ObjectWriter writer = csvMapper.writerFor(LocationDto.class).with(csvSchema);
//        writer.writeValues(file).writeAll(Arrays.asList(
//                new LocationDto(
//               "413515", "Ahmedpur", "MH", "India", "+91", true),
//                new LocationDto(
//                        "123456", "Ahmedpur", "MH", "India", "+92", true)
//        )).close();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get( "/tmp/sample4685341289294507041.scv" ), StandardCharsets.UTF_8)) {
            MappingIterator<Map<String, String>> iterator = csvMapper.readerFor(Map.class)
                    //MappingIterator<LocationDto> iterator = csvMapper.readerFor(LocationDto.class)
                    .with(csvSchema)
                    .readValues(reader); //.readValues(new InputStreamReader(bytes.openStream(),StandardCharsets.UTF_8))

            while (iterator.hasNext()) {
                System.out.println(
                        iterator.next().entrySet().stream().map(e -> e.getKey() + ":" + e.getValue()).collect(Collectors.joining( ", " )));
                //LocationDto book = iterator.next();
                //System.out.printf( "code: %s, cityName: %s, regionName: %s, enabled: %s%n" , book.getCode(), book.getCityName(), book.getRegionName(), book.isEnabled());
            }
        }
    }
}
