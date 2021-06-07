
package com.moringaschool.kenyatravelguide;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TouristFacilitiesModelClass {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("rate")
    @Expose
    private Integer rate;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("latitude")
    @Expose
    private Double latitude;

    /**
     * No args constructor for use in serialization
     *
     */
    public TouristFacilitiesModelClass() {
    }

    /**
     *
     * @param rate
     * @param kind
     * @param latitude
     * @param name
     * @param id
     * @param longitude
     */
    public TouristFacilitiesModelClass(String id, String name, Integer rate, String kind, Double longitude, Double latitude) {
        super();
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.kind = kind;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

}
