package com.inktomi.cirrus;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.inktomi.cirrus.forecast.DWML;
import com.inktomi.cirrus.forecast.Data;
import com.inktomi.cirrus.forecast.Parameters;
import com.inktomi.cirrus.forecast.TemperatureValue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class WeatherClient {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss");

    private RequestQueue mRequestQueue;

    public WeatherClient(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    /**
     * Retrieves the current conditions and forecast for the next n hours.
     * @param latitude the latitude for the request
     * @param longitude the longitude for the request
     */
    public void getWeatherForecast(double latitude, double longitude, int hours, Response.ErrorListener error, Response.Listener<DWML> success) {
        // Build out the URL
        StringBuilder url = new StringBuilder(URLStrings.NDFDClient);
        url.append("?").append(URLStrings.LATITIDE).append("=").append(latitude);
        url.append("&").append(URLStrings.LONGITUDE).append("=").append(longitude);

        // Get the time now. Clean it up for caching.
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        url.append("&").append(URLStrings.DATE_BEGIN).append("=").append(DATE_FORMAT.format(calendar.getTime()));

        // And the time out we wanted to get.
        calendar.add(Calendar.HOUR, hours);
        url.append("&").append(URLStrings.DATE_END).append("=").append(DATE_FORMAT.format(calendar.getTime()));

        // And we want the current conditions and forecast.
        url.append("&").append(URLStrings.PRODUCT).append("=").append("time-series");

        final String requestUrl = url.toString();

        NDFDRequest request = new NDFDRequest(
                Request.Method.GET,
                requestUrl,
                error,
                success
        );

        mRequestQueue.add(request);
    }

    /**
     * Will return the current hourly temperature from a weather response.
     *
     * @param weather the response from getWeatherForecast that you want to get the current hourly temperature from.
     */
    public TemperatureValue getCurrentTemperature(DWML weather){
        TemperatureValue temperatureValue = null;

        Data responseData = null;
        if (null != weather.data && !weather.data.isEmpty()) {
            responseData = weather.data.get(0);
        }

        Parameters parameters = null;
        if (null != responseData && null != responseData.parameters && !responseData.parameters.isEmpty() ) {
            parameters = responseData.parameters.get(0);
        }

        if( null != parameters ){
            if( null != parameters.temperature ){
                for( int i = 0; i < parameters.temperature.size(); i++ ){
                    Parameters.Temperature temp = parameters.temperature.get(i);

                    if( null == temp.type ){
                        continue;
                    }

                    if( temp.type.equals("hourly") ){
                        temperatureValue = temp.value.get(0);
                    }
                }
            }
        }

        return temperatureValue;
    }

    public TemperatureValue getFeelsLikeTemperature(DWML weather){
        TemperatureValue temperatureValue = null;

        Data responseData = null;
        if (null != weather.data && !weather.data.isEmpty()) {
            responseData = weather.data.get(0);
        }

        Parameters parameters = null;
        if (null != responseData && null != responseData.parameters && !responseData.parameters.isEmpty() ) {
            parameters = responseData.parameters.get(0);
        }

        if( null != parameters ){
            if( null != parameters.temperature ){
                for( int i = 0; i < parameters.temperature.size(); i++ ){
                    Parameters.Temperature temp = parameters.temperature.get(i);

                    if( null == temp.type ){
                        continue;
                    }

                    if( temp.type.equals("apparent") ){
                        temperatureValue = temp.value.get(0);
                    }
                }
            }
        }

        return temperatureValue;
    }

}
