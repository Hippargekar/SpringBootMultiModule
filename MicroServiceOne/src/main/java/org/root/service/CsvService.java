package org.root.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.root.csv.LocationDto;
import org.springframework.stereotype.Service;
import org.root.utils.CommonUtils;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class CsvService {

    private List<LocationDto> data = Arrays.asList(new LocationDto(10l, "123456", "Ahmedpur", "MH", "India", "+92", true, LocationDto.Status.ACTIVE, new BigDecimal("37.79"), Instant.now()));

    // Custom Format with System Default Time Zone
    private DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            .withZone(ZoneId.systemDefault());

    private String[] HEADERS = new String[]{"Id", "Code", "Enabled", "Latitude", "Status", "CurrentDate"};

    public void writeCsv(OutputStreamWriter outputStreamWriter){
        //}, Pageable pageable) {
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(HEADERS);
        // format = CSVFormat.DEFAULT.withHeader(fileHeader) //.withFirstRecordAsHeader()
        // .withIgnoreHeaderCase().withTrim().withSkipHeaderRecord();

        //Create the CSVFormat object with "\n" as a record delimiter
//        CSVFormat csvFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

        try (CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(outputStreamWriter), csvFormat)) {
//            for (LocationEntity loc : locationRepository.findAll(pageable).getContent()) {
            // Iterate over the data and write each record
            for (LocationDto loc : data) {
                Object[] csvData = new Object[]{
                        String.valueOf(loc.getId()),
                        loc.getCode(),
                        Boolean.toString(loc.isEnabled()), //String.valueOf(tutorial.isPublished())
                        CommonUtils.formatBigDecimal(loc.getLatitude()),
                        loc.getStatus() != null ? loc.getStatus().name() : "", // Handle Enum
                        customFormatter.format(loc.getCurrentDate())
                };
                csvPrinter.printRecord(csvData);
            }
            csvPrinter.flush();
        } catch (Exception ex) {
            throw new RuntimeException("Error writing CSV", ex);
        }
    }

}
