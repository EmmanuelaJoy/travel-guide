
package com.moringaschool.kenyatravelguide;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KenyaSightingsClass {

    public List<KenyaSightingsClass> kenyaSightings;
    @SerializedName("xid")
    @Expose
    private String xid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("dist")
    @Expose
    private Double dist;
    @SerializedName("rate")
    @Expose
    private Integer rate;
    @SerializedName("osm")
    @Expose
    private String osm;
    @SerializedName("kinds")
    @Expose
    private String kinds;

    /**
     * No args constructor for use in serialization
     * 
     */
    public KenyaSightingsClass() {
    }

    /**
     * 
     * @param xid
     * @param rate
     * @param name
     * @param osm
     * @param dist
     * @param kinds
     */
    public KenyaSightingsClass(String xid, String name, Double dist, Integer rate, String osm, String kinds) {
        super();
        this.xid = xid;
        this.name = name;
        this.dist = dist;
        this.rate = rate;
        this.osm = osm;
        this.kinds = kinds;
        kenyaSightings.add(this);
    }

    public String getXid() {
        return xid;
    }

    public void setXid(String xid) {
        this.xid = xid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDist() {
        return dist;
    }

    public void setDist(Double dist) {
        this.dist = dist;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getOsm() {
        return osm;
    }

    public void setOsm(String osm) {
        this.osm = osm;
    }

    public String getKinds() {
        return kinds;
    }

    public void setKinds(String kinds) {
        this.kinds = kinds;
    }

}
