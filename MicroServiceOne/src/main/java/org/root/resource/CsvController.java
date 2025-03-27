package org.root.resource;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.commons.io.FileUtils;
import org.root.csv.LocationDto;
import org.root.service.CsvService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class CsvController {
    private final CsvService csvService;

    public CsvController(CsvService csvService) {
        this.csvService = csvService;
    }

    @GetMapping("/download/sample-csv")
    public ResponseEntity<byte[]> downloadSampleCsv() throws IOException {
        String fileName = "sample.scv";
        File file = File.createTempFile("sample",".scv");
        CsvMapper csvMapper = new CsvMapper();
        csvMapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        csvMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        csvMapper.findAndRegisterModules();

        CsvSchema csvSchema = csvMapper.schemaFor(LocationDto.class)
                .withHeader()
                .withColumnSeparator(',');
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectWriter writer = csvMapper.writerFor(LocationDto.class).with(csvSchema);
        writer.writeValues(file).writeAll(new ArrayList<>()).close();
//        writer.writeValues(outputStream).writeAll(Arrays.asList(new LocationDto(
//                "413515", "Ahmedpur", "MH", "India", "+91", true)));
//        byte[] bytes = outputStream.toByteArray();
        byte[] bytes = FileUtils.readFileToByteArray(file);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(bytes.length);
        headers.setContentDispositionFormData("attachment", fileName);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    @GetMapping("/download")
    public ResponseEntity<StreamingResponseBody> downloadCsv(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "100") int size) {
        //Pageable pageable = PageRequest.of(page, size);
        StreamingResponseBody responseBody = outputStream -> {
            try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream)) {
                csvService.writeCsv(outputStreamWriter);
                //, pageable);
            } catch (Exception ex) {
                ex.printStackTrace(); // Handle the exception appropriately
            }
        };
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=location_output.csv");
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
        return new ResponseEntity<>(responseBody, headers, HttpStatus.OK);
    }
}
