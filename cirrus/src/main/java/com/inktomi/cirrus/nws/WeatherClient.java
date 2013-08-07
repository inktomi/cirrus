package com.inktomi.cirrus.nws;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.inktomi.cirrus.nws.forecast.DWML;

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
     * Retrieves the current conditions and forecast for the next hour.
     * @param latitude the latitude for the request
     * @param longitude the longitude for the request
     */
    public void getWeatherForecast(double latitude, double longitude, Response.ErrorListener error, Response.Listener<DWML> success) {
        // Build out the URL
        StringBuilder url = new StringBuilder(URLStrings.NDFDClient);
        url.append("?").append(URLStrings.LATITIDE).append("=").append(latitude);
        url.append("&").append(URLStrings.LONGITUDE).append("=").append(longitude);

        // Get the time now.
        GregorianCalendar calendar = new GregorianCalendar();
        url.append("&").append(URLStrings.DATE_BEGIN).append("=").append(DATE_FORMAT.format(calendar.getTime()));

        // And one hour from now.
        calendar.add(Calendar.HOUR, 1);
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
}
