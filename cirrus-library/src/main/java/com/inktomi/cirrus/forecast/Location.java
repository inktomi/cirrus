package com.inktomi.cirrus.forecast;

import org.simpleframework.xml.Element;

@Element
public class Location {

    @Element(name = "location-key", required = true)
    public String locationKey;

    @Element(required = false)
    public String description;

    @Element(required = false)
    public Point point;

    @Element(name = "nws-zone", required = false)
    public NwsZone nwsZone;

    @Element(required = false)
    public Area area;

    @Element(required = false)
    public City city;

    @Element(name = "area-description", required = false)
    public String areaDescription;

    @Element(required = false)
    public Height height;

    @Element(required = false)
    public Level level;

    @Element(required = false)
    public Layer layer;
}
