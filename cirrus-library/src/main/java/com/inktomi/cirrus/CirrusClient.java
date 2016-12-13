package com.inktomi.cirrus;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.inktomi.cirrus.forecast.WeatherResponse;

public class CirrusClient {

    private RequestQueue mRequestQueue;

    public CirrusClient(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    /**
     * Retrieves the current conditions and forecast for the next n hours.
     * @param latitude the latitude for the request
     * @param longitude the longitude for the request
     */
    public void getWeatherForecast(double latitude, double longitude, Response.ErrorListener error, Response.Listener<WeatherResponse> success) {
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
//        url.append("?whichClient=NDFDgen&product=glance&Unit=e&maxt=maxt&mint=mint&wx=wx&icons=icons&wwa=wwa");
        url.append("&").append(URLStrings.LATITIDE).append("=").append(latitude);
        url.append("&").append(URLStrings.LONGITUDE).append("=").append(longitude);

//        // Start now, go to the end of the day for data
//        Calendar today = new GregorianCalendar();
//        url.append("&begin=").append(DATE_FORMAT.format(today.getTime()));
//
//        today.add(Calendar.DAY_OF_YEAR, 1);
//        today.set(Calendar.HOUR_OF_DAY, 0);
//        today.set(Calendar.MINUTE, 0);
//        today.set(Calendar.SECOND, 0);
//
//        url.append("&end=").append(DATE_FORMAT.format(today.getTime()));

        return url.toString();
    }
}
