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

public class CirrusClient {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm:ss");

    private RequestQueue mRequestQueue;

    public CirrusClient(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    /**
     * Retrieves the current conditions and forecast for the next n hours.
     * @param latitude the latitude for the request
     * @param longitude the longitude for the request
     */
    public void getWeatherForecast(double latitude, double longitude, Response.ErrorListener error, Response.Listener<DWML> success) {
        final String requestUrl = getWeatherForecastUrl(latitude, longitude);

        NDFDRequest request = new NDFDRequest(
                Request.Method.GET,
                requestUrl,
                error,
                success
        );

        mRequestQueue.add(request);
    }

    private String getWeatherForecastUrl(Double latitude, Double longitude){
        // Build out the URL
        StringBuilder url = new StringBuilder(URLStrings.NDFDClient);
        url.append("?").append(URLStrings.LATITIDE).append("=").append(latitude);
        url.append("&").append(URLStrings.LONGITUDE).append("=").append(longitude);

        // And we want the current conditions and forecast.
        url.append("&").append("numDays").append("=").append("1");

        return url.toString();
    }
}
