package com.inktomi.cirrus.forecast;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * Lets us see what the error was without crashing.
 *
 * Created by mruno on 8/13/13.
 */
@Root
public class Error {

    @Element(name = "h2")
    public H2 title;

    @Element(name = "pre")
    public Pre message;

    public static class Pre {
        @Text
        public String body;
    }

    public static class H2 {
        @Text
        public String body;
    }
}
