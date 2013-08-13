package com.inktomi.cirrus;

import android.util.Log;

import com.inktomi.cirrus.forecast.DWML;
import com.inktomi.cirrus.forecast.Data;
import com.inktomi.cirrus.forecast.Icon;
import com.inktomi.cirrus.forecast.Parameters;
import com.inktomi.cirrus.forecast.TemperatureValue;
import com.inktomi.cirrus.forecast.TimeLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A helper class which makes it easier to get information from the data returned
 * from the NDFD api.
 *
 * Created by mruno on 8/12/13.
 */
public class WeatherUtils {
    private static final String TAG = WeatherUtils.class.getName();
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss");

    private static Map<String, TemperatureValue> HOURLY_TEMPERATURE = null;

    private static final Comparator<TimeLayout> TIME_LAYOUT_COMPARATOR = new Comparator<TimeLayout>(){

        @Override
        public int compare(TimeLayout lhs, TimeLayout rhs) {
            return lhs.layoutKey.compareTo(rhs.layoutKey);
        }
    };

    /**
     * This class caches a lot of the work it does forever. When you update your forecast, you
     * can use this method to clear the cached values.
     */
    public static void notifyDatasetChanged(){
        HOURLY_TEMPERATURE.clear();
    }

    /**
     * Formats a date for use as a map key, or to compare to dates from the NDFD.
     * @param input the date to format
     * @return the formatted string.
     */
    public static String formatDate( Date input ){
        return DATE_FORMAT.format(input);
    }

    /**
     * Will return the current hourly temperature from a weather response.
     *
     * @param weather the response from getWeatherForecast that you want to get the current hourly temperature from.
     */
    public static TemperatureValue getForecastMaximumTemperature(DWML weather, Date when){
        Data responseData = null;
        if (null != weather.data && !weather.data.isEmpty()) {

            // We need to pull the forecast data out.
            for( int i = 0; i < weather.data.size(); i++ ){
                Data trialSet = weather.data.get(i);

                if( null != trialSet.type && trialSet.type.equals("forecast") ){
                    responseData = trialSet;
                }
            }
        }

        Parameters parameters = null;
        if (null != responseData && null != responseData.parameters && !responseData.parameters.isEmpty() ) {
            parameters = responseData.parameters.get(0);
        }

        Parameters.Temperature temp = null;
        if( null != parameters ){
            if( null != parameters.temperature ){
                for( int i = 0; i < parameters.temperature.size(); i++ ){
                    Parameters.Temperature trial = parameters.temperature.get(i);

                    if( null == trial.type ){
                        continue;
                    }

                    if( trial.type.equals("maximum") ){
                        temp = trial;
                    }
                }
            }
        }

        // What temperature index should we look for?
        String timeKey = temp.timeLayout;

        // Find the layout to use.
        int forecastIndex = -1;
        if( null != responseData.timeLayout && !responseData.timeLayout.isEmpty() ){
            Collections.sort(responseData.timeLayout, TIME_LAYOUT_COMPARATOR);

            TimeLayout predicate = new TimeLayout();
            predicate.layoutKey = timeKey;

            int layoutPosition = Collections.binarySearch(responseData.timeLayout, predicate, TIME_LAYOUT_COMPARATOR);

            if( layoutPosition > -1 ){
                TimeLayout timeLayout = responseData.timeLayout.get(layoutPosition);

                forecastIndex = timeLayout.getIndexForTime(when);
            }
        }

        if( forecastIndex > -1 ){
            return temp.value.get(forecastIndex);
        }

        return null;
    }

    public static TemperatureValue getCurrentTemperature(DWML weather){
        Data responseData = null;
        if (null != weather.data && !weather.data.isEmpty()) {

            // We need to pull the forecast data out.
            for( int i = 0; i < weather.data.size(); i++ ){
                Data trialSet = weather.data.get(i);

                if( null != trialSet.type && trialSet.type.equals("current observations") ){
                    responseData = trialSet;
                }
            }
        }

        Parameters parameters = null;
        if (null != responseData && null != responseData.parameters && !responseData.parameters.isEmpty() ) {
            parameters = responseData.parameters.get(0);
        }

        Parameters.Temperature temp = null;
        if( null != parameters ){
            if( null != parameters.temperature ){
                for( int i = 0; i < parameters.temperature.size(); i++ ){
                    Parameters.Temperature trial = parameters.temperature.get(i);

                    if( null == trial.type ){
                        continue;
                    }

                    if( trial.type.equals("apparent") ){
                        temp = trial;
                    }
                }
            }
        }

        // No need for time layouts here!
        return temp.value.get(0);
    }

    public static TemperatureValue getForecastMinimumTemperature(DWML weather, Date when){
        Data responseData = null;
        if (null != weather.data && !weather.data.isEmpty()) {

            // We need to pull the forecast data out.
            for( int i = 0; i < weather.data.size(); i++ ){
                Data trialSet = weather.data.get(i);

                if( null != trialSet.type && trialSet.type.equals("forecast") ){
                    responseData = trialSet;
                }
            }
        }

        Parameters parameters = null;
        if (null != responseData && null != responseData.parameters && !responseData.parameters.isEmpty() ) {
            parameters = responseData.parameters.get(0);
        }

        Parameters.Temperature temp = null;
        if( null != parameters ){
            if( null != parameters.temperature ){
                for( int i = 0; i < parameters.temperature.size(); i++ ){
                    Parameters.Temperature trial = parameters.temperature.get(i);

                    if( null == trial.type ){
                        continue;
                    }

                    if( trial.type.equals("minimum") ){
                        temp = trial;
                    }
                }
            }
        }

        // What temperature index should we look for?
        String timeKey = temp.timeLayout;

        // Find the layout to use.
        int forecastIndex = -1;
        if( null != responseData.timeLayout && !responseData.timeLayout.isEmpty() ){
            Collections.sort(responseData.timeLayout, TIME_LAYOUT_COMPARATOR);

            TimeLayout predicate = new TimeLayout();
            predicate.layoutKey = timeKey;

            int layoutPosition = Collections.binarySearch(responseData.timeLayout, predicate, TIME_LAYOUT_COMPARATOR);

            if( layoutPosition > -1 ){
                TimeLayout timeLayout = responseData.timeLayout.get(layoutPosition);

                forecastIndex = timeLayout.getIndexForTime(when);
            }
        }

        if( forecastIndex > -1 ){
            return temp.value.get(forecastIndex);
        }

        return null;
    }

    public static String getForecastWeatherConditions(DWML response, Date when){
        Data responseData = null;
        if (null != response.data && !response.data.isEmpty()) {

            // We need to pull the forecast data out.
            for( int i = 0; i < response.data.size(); i++ ){
                Data trialSet = response.data.get(i);

                if( null != trialSet.type && trialSet.type.equals("forecast") ){
                    responseData = trialSet;
                }
            }
        }

        return getWeatherConditions(when, responseData);
    }

    public static String getCurrentWeatherConditions(DWML response){
        Data responseData = null;
        if (null != response.data && !response.data.isEmpty()) {

            // We need to pull the forecast data out.
            for( int i = 0; i < response.data.size(); i++ ){
                Data trialSet = response.data.get(i);

                if( null != trialSet.type && trialSet.type.equals("current observations") ){
                    responseData = trialSet;
                }
            }
        }

        return getWeatherConditions(null, responseData);
    }

    private static String getWeatherConditions(Date when, Data responseData) {
        Parameters parameters = null;
        if (null != responseData && null != responseData.parameters && !responseData.parameters.isEmpty() ) {
            parameters = responseData.parameters.get(0);
        }

        Parameters.Weather weather = null;

        if( null != parameters ){
            List<Parameters.Weather> weatherList = parameters.weather;

            if( null != weatherList && !weatherList.isEmpty() ){
                weather = weatherList.get(0);
            }
        }

        if( null != weather ){
            // What temperature index should we look for?
            String timeKey = weather.timeLayout;

            // Find the layout to use.
            int forecastIndex = -1;
            if( null != responseData.timeLayout && !responseData.timeLayout.isEmpty() ){
                Collections.sort(responseData.timeLayout, TIME_LAYOUT_COMPARATOR);

                TimeLayout predicate = new TimeLayout();
                predicate.layoutKey = timeKey;

                int layoutPosition = Collections.binarySearch(responseData.timeLayout, predicate, TIME_LAYOUT_COMPARATOR);

                if( layoutPosition > -1 ){
                    TimeLayout timeLayout = responseData.timeLayout.get(layoutPosition);

                    if( null == when ){
                        forecastIndex = 0;
                    } else {
                        forecastIndex = timeLayout.getIndexForTime(when);
                    }
                }
            }

            Parameters.Weather.WeatherConditions conditions = null;
            if( forecastIndex > -1 ){
                // Get the weather conditions out.
                conditions = weather.weatherConditions.get(forecastIndex);
            }

            if( null != conditions ){
                return conditions.weatherSummary;
            }
        }

        return null;
    }
}
