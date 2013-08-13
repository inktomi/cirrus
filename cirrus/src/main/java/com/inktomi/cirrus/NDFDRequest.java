package com.inktomi.cirrus;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.inktomi.cirrus.forecast.DWML;
import com.inktomi.cirrus.forecast.Error;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.ElementException;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.transform.Matcher;
import org.simpleframework.xml.transform.Transform;

import java.util.Date;

/**
 * Parses the XML that we get from the NDFD
 */
public class NDFDRequest extends Request<DWML> {
    private static final String TAG = NDFDRequest.class.getName();

    private Serializer mSerializer = new Persister(new Matcher() {
        public Transform match(Class type) throws Exception {
            if (type.isEnum()) {
                return new EnumTransform(type);
            }

            if( type == Date.class ){
                return new DateTransform();
            }

            return null;
        }
    });

    private final Response.Listener<DWML> mListener;

    public NDFDRequest(int method, String url, Response.ErrorListener errorListener, Response.Listener<DWML> mListener) {
        super(method, url, errorListener);
        this.mListener = mListener;
    }

    @Override
    protected Response<DWML> parseNetworkResponse(NetworkResponse response) {
        DWML rval;
        try {
            rval = mSerializer.read(com.inktomi.cirrus.forecast.DWML.class, new String(response.data));
        } catch (ElementException e){
            Log.e(TAG, "Failed to read DWML format. Trying error class.", e);
            try {
                Error errorResponse = mSerializer.read(Error.class, new String(response.data));

                return Response.error(new VolleyError(errorResponse.message.body));
            } catch (Exception e1) {
                Log.e(TAG, "Failed to read DWML format.", e);
                return Response.error(new VolleyError(e.getMessage(), e));
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to read DWML format.", e);
            return Response.error(new VolleyError(e.getMessage(), e));
        }

        return Response.success(rval, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(DWML response) {
        mListener.onResponse(response);
    }
}
