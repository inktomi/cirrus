package com.inktomi.cirrus.forecast;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Text;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Element
@SuppressWarnings({"unused"})
public class Parameters {

    private static final Pattern ALL_DIGITS = Pattern.compile("^\\d*$");

    @ElementList(inline = true, entry = "categories", required = false)
    public List<Categories> categories;

    @ElementList(inline = true, entry = "temperature", required = false)
    public List<Parameters.Temperature> temperature;

    @ElementList(inline = true, entry = "precipitation", required = false)
    public List<Parameters.Precipitation> precipitation;

    @ElementList(inline = true, entry = "probability-of-precipitation", required = false)
    public List<Parameters.ProbabilityOfPrecipitation> probabilityOfPrecipitation;

    @ElementList(inline = true, entry = "fire-weather", required = false)
    public List<Parameters.FireWeather> fireWeather;

    @ElementList(inline = true, entry = "convective-hazard", required = false)
    public List<Parameters.ConvectiveHazard> convectiveHazard;

    @ElementList(inline = true, entry = "climate-anomaly", required = false)
    public List<Parameters.ClimateAnomaly> climateAnomaly;

    @ElementList(inline = true, entry = "wind-speed", required = false)
    public List<Parameters.WindSpeed> windSpeed;

    @ElementList(inline = true, entry = "direction", required = false)
    public List<Parameters.Direction> direction;

    @ElementList(inline = true, entry = "cloud-amount", required = false)
    public List<Parameters.CloudAmount> cloudAmount;

    @ElementList(inline = true, entry = "humidity", required = false)
    public List<Parameters.Humidity> humidity;

    @ElementList(inline = true, entry = "weather", required = false)
    public List<Parameters.Weather> weather;

    @ElementList(inline = true, entry = "conditions-icon", required = false)
    public List<Parameters.ConditionsIcon> conditionsIcon;

    @ElementList(inline = true, entry = "hazards", required = false)
    public List<Parameters.Hazards> hazards;

    @Element(required = false)
    public Parameters.WordedForecast wordedForecast;

    @ElementList(inline = true, entry = "pressure", required = false)
    public List<Parameters.Pressure> pressure;

    @ElementList(inline = true, entry = "probabilisticCondition", required = false)
    public List<Parameters.ProbabilisticCondition> probabilisticCondition;

    @ElementList(inline = true, entry = "water-state", required = false)
    public List<Parameters.WaterState> waterState;

    @ElementList(inline = true, entry = "wave", required = false)
    public List<WaterState.Waves> waves;

    @Attribute(name = "applicable-location", required = true)
    public String applicableLocation;

    @Element
    public static class ClimateAnomaly {

        @ElementList(inline = true, entry = "weekly", required = false)
        public List<Anomaly> weekly;

        @ElementList(inline = true, entry = "monthly", required = false)
        public List<Anomaly> monthly;

        @ElementList(inline = true, entry = "seasonal", required = false)
        public List<Anomaly> seasonal;
    }

    @Element
    public static class CloudAmount {

        @Element
        public String name;

        @ElementList(inline = true, entry = "value", required = false)
        public List<PercentageValue> value;

        @ElementList(inline = true, entry = "valueWithUncertainty", required = false)
        public List<Parameters.CloudAmount.ValueWithUncertainty> valueWithUncertainty;

        @Attribute(name = "type", required = true)
        public String type;

        @Attribute(name = "units", required = false)
        public String units;

        @Attribute(name = "time-layout", required = true)
        public String timeLayout;

        @Attribute(name = "categorical-table", required = false)
        public String categoricalTable;

        @Attribute(name = "conversion-table", required = false)
        public String conversionTable;

        @Element
        public static class ValueWithUncertainty {

            @Element(name = "value", required = false)
            public PercentageValue value;

            @Element(name = "uncertainty", required = false)
            public Uncertainty uncertainty;

            @Element(name = "numberWithEquality", required = false)
            public String numberWithEquality;

            @Attribute(name = "type")
            public DataSource type;
        }

    }

    @Element
    public static class ConditionsIcon {

        @Element
        public String name;

        @ElementList(entry = "icon-link", inline = true, required = true)
        public List<String> iconLink;

        @Attribute(name = "time-layout", required = true)
        public String timeLayout;

        @Attribute(name = "type", required = true)
        public String type;
    }

    @Element
    public static class ConvectiveHazard {

        @Element(required = false)
        public Parameters.ConvectiveHazard.Outlook outlook;

        @ElementList(entry = "severe-component", required = false, inline = true)
        public List<Parameters.ConvectiveHazard.SevereComponent> severeComponent;

        @Element
        public static class Outlook {

            @Element
            public String name;

            @ElementList(entry = "value", required = false, inline = true)
            public List<String> value;

            @Attribute(name = "time-layout", required = true)
            public String timeLayout;

            @Attribute(name = "categorical-table", required = false)
            public String categoricalTable;

            @Attribute(name = "conversion-table", required = false)
            public String conversionTable;
        }

        public static class SevereComponent {

            @Element
            public String name;

            @ElementList(entry = "value", required = false, inline = true)
            public List<Parameters.ConvectiveHazard.SevereComponent.Value> value;

            @Attribute(name = "type", required = true)
            public String type;

            @Attribute(name = "units", required = false)
            public String units;

            @Attribute(name = "time-layout", required = true)
            public String timeLayout;

            @Attribute(name = "categorical-table", required = false)
            public String categoricalTable;

            @Attribute(name = "conversion-table", required = false)
            public String conversionTable;

            @Element
            public static class Value {

                @Text
                public BigInteger value;

                @Attribute(name = "upper-range", required = false)
                public Integer upperRange;

                @Attribute(name = "lower-range", required = false)
                public Integer lowerRange;
            }

        }

    }

    @Element
    public static class Direction {

        @Element(required = false)
        public String name;

        @ElementList(inline = true, entry = "value", required = false)
        public List<WindDirectionValue> value;

        @ElementList(inline = true, entry = "valueWithUncertainty", required = false)
        public List<Parameters.Direction.ValueWithUncertainty> valueWithUncertainty;

        @Attribute(name = "type", required = true)
        public String type;

        @Attribute(name = "units", required = false)
        public String units;

        @Attribute(name = "time-layout", required = true)
        public String timeLayout;

        @Attribute(name = "categorical-table", required = false)
        public String categoricalTable;

        @Attribute(name = "conversion-table", required = false)
        public String conversionTable;

        @Element
        public static class ValueWithUncertainty {

            @Element(name = "value", required = false)
            public WindDirectionValue value;

            @Element(name = "uncertainty", required = false)
            public Uncertainty uncertainty;

            @Element(name = "numberWithEquality", required = false)
            public String numberWithEquality;

            @Attribute(name = "type")
            public DataSource type;
        }

    }

    @Element
    public static class FireWeather {

        @Element(required = false)
        public String name;

        @ElementList(entry = "value", inline = true, required = false)
        public List<String> value;

        @Attribute(name = "type", required = true)
        public String type;

        @Attribute(name = "time-layout", required = true)
        public String timeLayout;

        @Attribute(name = "categorical-table", required = false)
        public String categoricalTable;

        @Attribute(name = "conversion-table", required = false)
        public String conversionTable;
    }

    @Element
    public static class Hazards {

        @Element(name = "name", required = true, type = String.class)
        public String name;

        @ElementList(entry = "hazard-conditions", inline = true, required = true)
        public List<Parameters.Hazards.HazardConditions> hazardConditions;

        @Attribute(name = "time-layout", required = true)
        public String timeLayout;

        @Element
        public static class HazardConditions {

            @ElementList(entry = "hazard", required = false, inline = true)
            public List<Parameters.Hazards.HazardConditions.Hazard> hazard;

            @Element
            public static class Hazard {

                @Element(name = "hazardTextURL", required = false)
                public String hazardTextURL;

                @Element(name = "hazardIcon", required = false)
                public String hazardIcon;

                @Attribute(name = "hazardCode", required = false)
                public String hazardCode;

                @Attribute(name = "phenomena", required = false)
                public String phenomena;

                @Attribute(name = "significance", required = false)
                public String significance;

                @Attribute(name = "hazardType", required = false)
                public String hazardType;

                @Attribute(name = "eventTrackingNumber", required = false)
                public BigInteger eventTrackingNumber;

                @Attribute(name = "headline", required = false)
                public String headline;

            }
        }
    }

    @Element
    public static class Humidity {

        @Element(required = false)
        public String name;

        @ElementList(entry = "value", required = false, inline = true)
        public List<Parameters.Humidity.Value> value;

        @Attribute(name = "type", required = true)
        public String type;

        @Attribute(name = "units", required = false)
        public String units;

        @Attribute(name = "time-layout", required = true)
        public String timeLayout;

        @Attribute(name = "categorical-table", required = false)
        public String categoricalTable;

        @Attribute(name = "conversion-table", required = false)
        public String conversionTable;

        @Element
        public static class Value {
            private static final Pattern ALL_DIGITS = Pattern.compile("^\\d*$");

            public BigInteger value;

            @Attribute(name = "upper-range", required = false)
            public Integer upperRange;

            @Attribute(name = "lower-range", required = false)
            public Integer lowerRange;

            @Text(required = false)
            public void setValue(String value){
                Matcher allDigitMatcher = ALL_DIGITS.matcher(value);

                if( allDigitMatcher.matches() ){
                    this.value = new BigInteger(value);
                }
            }

            @Text(required = false)
            public String getValue() {
                return value.toString();
            }
        }
    }

    @Element
    public static class Precipitation {

        @Element(required = false)
        public String name;

        @ElementList(entry = "value", inline = true, required = false)
        public List<DecimalVal> value;

        @ElementList(entry = "valueList", inline = true, required = false)
        public List<ValueList> valueList;

        @ElementList(entry = "valueWithUncertainty", inline = true, required = false)
        public List<Parameters.Precipitation.ValueWithUncertainty> valueWithUncertainty;

        @Attribute(name = "type", required = true)
        public String type;

        @Attribute(name = "units", required = false)
        public String units;

        @Attribute(name = "likelihoodUnits", required = false)
        public LikelihoodUnits likelihoodUnits;

        @Attribute(name = "time-layout", required = true)
        public String timeLayout;

        @Attribute(name = "categorical-table", required = false)
        public String categoricalTable;

        @Attribute(name = "conversion-table", required = false)
        public String conversionTable;

        @Attribute(name = "probability-type", required = false)
        public Probability probabilityType;

        @Element
        public static class ValueWithUncertainty {

            @Element(required = true)
            public DecimalVal value;

            @Element(name = "uncertainty", required = false)
            public Uncertainty uncertainty;

            @Element(name = "numberWithEquality", required = false)
            public String numberWithEquality;

            @Attribute(name = "type", required = true)
            public DataSource type;
        }
    }

    @Element
    public static class Pressure {

        @Element(required = false)
        public String name;

        @ElementList(entry = "value", required = false, inline = true)
        public List<DecimalVal> value;

        @Attribute(name = "type", required = true)
        public String type;

        @Attribute(name = "units", required = false)
        public String units;

        @Attribute(name = "time-layout", required = true)
        public String timeLayout;

        @Attribute(name = "categorical-table", required = false)
        public String categoricalTable;

        @Attribute(name = "conversion-table", required = false)
        public String conversionTable;

    }

    @Element
    public static class ProbabilisticCondition {
        @Element(required = false)
        public String name;

        @ElementList(entry = "value", required = false, inline = true)
        public List<PercentageValue> value;

        @ElementList(entry = "valueForRange", required = false, inline = true)
        public List<ValueForRange> valueForRange;

        @Attribute(name = "type", required = true)
        public String type;

        @Attribute(name = "units", required = false)
        public String units;

        @Attribute(name = "likelihoodUnits", required = false)
        public LikelihoodUnits likelihoodUnits;

        @Attribute(name = "time-layout", required = true)
        public String timeLayout;

        @Attribute(name = "categorical-table", required = false)
        public String categoricalTable;

        @Attribute(name = "conversion-table", required = false)
        public String conversionTable;

        @Attribute(name = "applicable-categories", required = false)
        public String applicableCategories;

        @Attribute(name = "probability-type", required = false)
        public Probability probabilityType;
    }

    @Element
    public static class ProbabilityOfPrecipitation {

        @Element(required = false)
        public String name;

        @ElementList(entry = "value", inline = true, required = false)
        public List<PercentageValue> value;

        @ElementList(entry = "valueList", inline = true, required = false)
        public List<ValueList> valueList;

        @Attribute(name = "type", required = true)
        public String type;

        @Attribute(name = "units", required = false)
        public String units;

        @Attribute(name = "likelihoodUnits", required = false)
        public LikelihoodUnits likelihoodUnits;

        @Attribute(name = "time-layout", required = true)
        public String timeLayout;

        @Attribute(name = "categorical-table", required = false)
        public String categoricalTable;

        @Attribute(name = "conversion-table", required = false)
        public String conversionTable;

        @Attribute(name = "applicable-categories", required = false)
        public String applicableCategories;

        @Attribute(name = "probability-type", required = false)
        public Probability probabilityType;
    }

    @Element
    public static class Temperature {
        @Element(required = false)
        public String name;

        @ElementList(type = TemperatureValue.class, inline = true, entry = "value", required = false)
        public List<TemperatureValue> value;

        @ElementList(inline = true, required = false, entry = "valueList")
        public List<ValueList> valueList;

        @ElementList(inline = true, required = false, entry = "valueWithUncertainty")
        public List<Parameters.Temperature.ValueWithUncertainty> valueWithUncertainty;

        @Attribute(name = "type", required = true)
        public String type;

        @Attribute(name = "units", required = false)
        public String units;

        @Attribute(name = "likelihoodUnits", required = false)
        public LikelihoodUnits likelihoodUnits;

        @Attribute(name = "time-layout", required = true)
        public String timeLayout;

        @Attribute(name = "categorical-table", required = false)
        public String categoricalTable;

        @Attribute(name = "conversion-table", required = false)
        public String conversionTable;

        @Attribute(name = "applicable-categories", required = false)
        public String applicableCategories;

        @Attribute(name = "probability-type", required = false)
        public Probability probabilityType;

        @Element
        public static class ValueWithUncertainty {

            @Element(name = "value", required = false)
            public TemperatureValue value;

            @Element(name = "uncertainty", required = false)
            public Uncertainty uncertainty;

            @Element(name = "numberWithEquality", required = false)
            public String numberWithEquality;

            @Attribute(name = "type", required = true)
            public DataSource type;
        }
    }

    @Element
    public static class WaterState {

        @ElementList(entry = "waves", inline = true, required = false)
        public List<Parameters.WaterState.Waves> waves;

        @ElementList(entry = "swell", inline = true, required = false)
        public List<Parameters.WaterState.Swell> swell;

        @ElementList(entry = "seas", inline = true, required = false)
        public List<Parameters.WaterState.Seas> seas;

        @Element(name = "ice-coverage", required = false)
        public List<BigInteger> iceCoverage;

        @Attribute(name = "time-layout", required = true)
        public String timeLayout;

        @Element
        public static class Seas {

            @Element(required = false)
            public String name;

            @ElementList(entry = "value", required = true)
            public List<BigInteger> value;

            @Attribute(name = "type", required = true)
            public String type;

            @Attribute(name = "units", required = false)
            public String units;

            @Attribute(name = "categorical-table", required = false)
            public String categoricalTable;

            @Attribute(name = "conversion-table", required = false)
            public String conversionTable;
        }

        @Element
        public static class Swell {

            @Element(required = false)
            public String name;

            @Element(name = "value", required = false)
            public String value;

            @Element
            public Parameters.WaterState.Swell.Direction direction;

            @Attribute(name = "type", required = true)
            public String type;

            @Attribute(name = "units", required = false)
            public String units;

            @Attribute(name = "categorical-table", required = false)
            public String categoricalTable;

            @Attribute(name = "conversion-table", required = false)
            public String conversionTable;

            @Attribute(name = "period", required = false)
            public BigInteger period;

            @Attribute(name = "steepness", required = false)
            public BigInteger steepness;

            @Element
            public static class Direction {
                @Text
                public BigInteger value;

                @Attribute(name = "upper-range", required = false)
                public Integer upperRange;

                @Attribute(name = "lower-range", required = false)
                public Integer lowerRange;
            }
        }

        @Element
        public static class Waves {
            @Element(required = false)
            public String name;

            public List<BigInteger> value;

            @Attribute(required = false, name = "time-layout")
            public String timeLayout;

            @Attribute(name = "type", required = true)
            public String type;

            @Attribute(name = "units", required = false)
            public String units;

            @Attribute(name = "categorical-table", required = false)
            public String categoricalTable;

            @Attribute(name = "conversion-table", required = false)
            public String conversionTable;

            @Attribute(name = "period", required = false)
            public BigInteger period;

            @Attribute(name = "steepness", required = false)
            public BigInteger steepness;

            @ElementList(entry = "value", required = true, inline = true)
            public void setValue(List<String> values){
                value = new ArrayList<BigInteger>(values.size());

                for (int i = 0, valuesSize = values.size(); i < valuesSize; i++) {
                    String value = values.get(i);
                    Matcher digits = ALL_DIGITS.matcher(value);

                    if (digits.matches()) {
                        this.value.add(new BigInteger(value));
                    }
                }
            }

            @ElementList(entry = "value", required = true, inline = true)
            public List<String> getValue(){
                List<String> rval = new ArrayList<String>();

                if( null == value ){
                    return null;
                }

                List<BigInteger> value1 = this.value;
                for (int i = 0, value1Size = value1.size(); i < value1Size; i++) {
                    BigInteger value = value1.get(i);
                    rval.add(value.toString());
                }

                return rval;
            }
        }
    }

    @Element
    public static class Weather {
        @Element(name = "name", required = true, type = String.class)
        public String name;

        @ElementList(entry = "weather-conditions", required = true, type = Parameters.Weather.WeatherConditions.class, inline = true)
        public List<Parameters.Weather.WeatherConditions> weatherConditions;

        public List<Object> nameAndWeatherConditions;

        @Attribute(name = "time-layout", required = true)
        public String timeLayout;

        @Element
        public static class WeatherConditions {

            @ElementList(inline = true, required = false, entry = "value")
            public List<Parameters.Weather.WeatherConditions.Value> value;

            @Attribute(name = "categorical-table", required = false)
            public String categoricalTable;

            @Attribute(name = "conversion-table", required = false)
            public String conversionTable;

            @Attribute(name = "weather-summary", required = false)
            public String weatherSummary;

            @Element
            public static class Value {
                @Element(name = "visibility", required = false)
                public Parameters.Weather.WeatherConditions.Value.Visibility visibility;

                @Attribute(name = "coverage", required = false)
                public String coverage;

                @Attribute(name = "weather-type", required = false)
                public String weatherType;

                @Attribute(name = "intensity", required = false)
                public String intensity;

                @Attribute(name = "additive", required = false)
                public String additive;

                @Attribute(name = "qualifier", required = false)
                public String qualifier;

                @Element
                public static class Visibility {

                    @Text(required = false)
                    public String value;

                    @Attribute(name = "nil", empty = "false", required = false)
                    public String nil;

                    @Attribute(name = "units", required = false)
                    public String units;
                }
            }
        }
    }


    @Element
    public static class WindSpeed {
        @Element(required = false)
        public String name;

        @ElementList(inline = true, entry = "value", required = false)
        public List<WindSpeedValue> value;

        @ElementList(type = Parameters.WindSpeed.ValueWithUncertainty.class, entry = "value-with-uncertainty", inline = true, required = false)
        public List<Parameters.WindSpeed.ValueWithUncertainty> valueWithUncertainty;

        @Attribute(name = "type", required = true)
        public String type;

        @Attribute(name = "units", required = false)
        public String units;

        @Attribute(name = "time-layout", required = true)
        public String timeLayout;

        @Attribute(name = "categorical-table", required = false)
        public String categoricalTable;

        @Attribute(name = "conversion-table", required = false)
        public String conversionTable;

        @Element
        public static class ValueWithUncertainty {

            @Element(name = "value", required = false)
            public WindSpeedValue value;

            @Element(name = "uncertainty", required = false)
            public Uncertainty uncertainty;

            @Element(name = "numberWithEquality", required = false)
            public String numberWithEquality;

            @Attribute(name = "type", required = true)
            public DataSource type;
        }
    }


    @Element
    public static class WordedForecast {

        @Element(required = false)
        public String name;

        @ElementList(entry = "text", inline = true, required = false)
        public List<String> text;

        @Attribute(name = "time-layout", required = true)
        public String timeLayout;

        @Attribute(name = "dataSource")
        public String dataSource;

        @Attribute(name = "wordGenerator")
        public String wordGenerator;
    }

}
