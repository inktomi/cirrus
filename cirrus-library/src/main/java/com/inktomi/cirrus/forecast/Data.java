package com.inktomi.cirrus.forecast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Text;

import java.util.List;

@Element
public class Data {

    @ElementList(type = Location.class, required = false, inline = true)
    public List<Location> location;

    @ElementList(type = MoreWeatherInformation.class, required = false, inline = true)
    public List<MoreWeatherInformation> moreWeatherInformation;

    @ElementList(type = TimeLayout.class, entry = "time-layout", required = true, inline = true)
    public List<TimeLayout> timeLayout;

    @ElementList(type = Parameters.class, required = true, inline = true)
    public List<Parameters> parameters;

    @Attribute(name = "type", required = false)
    public String type;

    @Element(name = "nearby-observations", required = false)
    public String nearbyObservations;

    @Element(name = "past-observations", required = false)
    public String pastObservations;
}