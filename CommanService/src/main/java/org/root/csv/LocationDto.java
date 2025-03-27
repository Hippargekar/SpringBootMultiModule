package org.root.csv;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;
import java.time.Instant;

@JsonPropertyOrder(
        {"code", "enabled", "city_name", "region_name", "country_code", "country_name"}
)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationDto {
    private Long id;
    private String code;

    @JsonProperty("city_name")
    private String cityName;

    @JsonProperty("region_name")
    private String regionName;

    @JsonProperty("country_name")
    private String countryName;

    @JsonProperty("country_code")
    private String countryCode;

    private boolean enabled;

    private Status status; // Example of Enum

    private BigDecimal latitude; // Example of BigDecimal

    public enum Status {
        ACTIVE, INACTIVE, PENDING
    }

    private Instant currentDate;

    public LocationDto() {
    }

    public LocationDto(Long id, String code, String cityName, String regionName, String countryName, String countryCode, boolean enabled, Status status, BigDecimal latitude, Instant currentDate) {
        this.code = code;
        this.id = id;
        this.cityName = cityName;
        this.regionName = regionName;
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.enabled = enabled;
        this.status =status;
        this.latitude = latitude;
        this.currentDate = currentDate;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Instant getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Instant currentDate) {
        this.currentDate = currentDate;
    }
}

