package com.inktomi.cirrus.forecast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

@Element
public class Product {

    @Element(name = "title", required = false)
    public String title;

    @Element(name = "field", required = false)
    public Field field;

    @Element(name = "category", required = false)
    public Category category;

    @Element(name = "creation-date", required = false)
    public CreationDate creationDate;

    @Attribute(name = "concise-name", required = false)
    public ConciseName conciseName;

    @Attribute(name = "operational-mode", required = false)
    public OperationalMode operationalMode;

    @Attribute(name = "srsName", required = false)
    public SrsName srsName;
}
