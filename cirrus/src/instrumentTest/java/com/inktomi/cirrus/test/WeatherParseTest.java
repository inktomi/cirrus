package com.inktomi.cirrus.test;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import com.inktomi.cirrus.DateTransform;
import com.inktomi.cirrus.EnumTransform;
import com.inktomi.cirrus.CirrusClient;
import com.inktomi.cirrus.forecast.DWML;
import com.inktomi.cirrus.forecast.TemperatureValue;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.transform.Matcher;
import org.simpleframework.xml.transform.Transform;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class WeatherParseTest extends AndroidTestCase {
    private static final String TAG = WeatherParseTest.class.getName();
    private CirrusClient mCirrusClient;

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

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mCirrusClient = new CirrusClient(getContext());

        assertNotNull(mCirrusClient);
    }

    @LargeTest
    public void testLasVegas() throws Exception {
        String url = getTestUrl(36.0271214, -115.0906307);

        doTest(url);
    }

    @LargeTest
    public void testAuburn() throws Exception {
        String url = getTestUrl(32.67, -85.44);

        doTest(url);
    }

    @LargeTest
    public void testBirmingham() throws Exception {
        String url = getTestUrl(33.57, -86.75);

        doTest(url);
    }

    @LargeTest
    public void testAnchorage() throws Exception {
        String url = getTestUrl(61.17, -150.02);

        doTest(url);
    }

    @LargeTest
    public void testBarrow() throws Exception {
        String url = getTestUrl(71.30, -156.78);

        doTest(url);
    }

    @LargeTest
    public void testDeadhorse() throws Exception {
        String url = getTestUrl(70.20, -148.47);

        doTest(url);
    }

    @LargeTest
    public void testNome() throws Exception {
        String url = getTestUrl(64.50, -165.43);

        doTest(url);
    }

    @LargeTest
    public void testFlagstaff() throws Exception {
        String url = getTestUrl(35.13, -111.67);

        doTest(url);
    }

    @LargeTest
    public void testPhoenix() throws Exception {
        String url = getTestUrl(33.43, -112.02);

        doTest(url);
    }

    @LargeTest
    public void testFayetteville() throws Exception {
        String url = getTestUrl(36.00, -94.17);

        doTest(url);
    }

    @LargeTest
    public void testLittleRock() throws Exception {
        String url = getTestUrl(35.22, -90.65);

        doTest(url);
    }

    @LargeTest
    public void testArcata() throws Exception {
        String url = getTestUrl(40.98, -124.10);

        doTest(url);
    }

    @LargeTest
    public void testBakersfield() throws Exception {
        String url = getTestUrl(35.43, -119.05);

        doTest(url);
    }

    @LargeTest
    public void testBigBear() throws Exception {
        String url = getTestUrl(34.27, -116.68);

        doTest(url);
    }

    @LargeTest
    public void testBurbank() throws Exception {
        String url = getTestUrl(34.20, -118.37);

        doTest(url);
    }

    @LargeTest
    public void testChino() throws Exception {
        String url = getTestUrl(33.97, -117.63);

        doTest(url);
    }

    @LargeTest
    public void testEureka() throws Exception {
        String url = getTestUrl(41.33, -124.28);

        doTest(url);
    }

    @LargeTest
    public void testFresno() throws Exception {
        String url = getTestUrl(36.77, -119.72);

        doTest(url);
    }

    @LargeTest
    public void testLongBeach() throws Exception {
        String url = getTestUrl(33.82, -118.15);

        doTest(url);
    }

    @LargeTest
    public void testLosAngeles() throws Exception {
        String url = getTestUrl(33.93, -118.40);

        doTest(url);
    }

    @LargeTest
    public void testSacramento() throws Exception {
        String url = getTestUrl(38.70, -121.60);

        doTest(url);
    }

    @LargeTest
    public void testSanDiego() throws Exception {
        String url = getTestUrl(32.82, -117.13);

        doTest(url);
    }

    @LargeTest
    public void testAspen() throws Exception {
        String url = getTestUrl(39.22, -106.87);

        doTest(url);
    }

    @LargeTest
    public void testDenver() throws Exception {
        String url = getTestUrl(39.75, -104.87);

        doTest(url);
    }

    @LargeTest
         public void testBridgeport() throws Exception {
        String url = getTestUrl(41.17, -73.13);

        doTest(url);
    }

    @LargeTest
    public void testDover() throws Exception {
        String url = getTestUrl(39.13, -75.47);

        doTest(url);
    }

    @LargeTest
    public void testWashingtonDC() throws Exception {
        String url = getTestUrl(38.95, -77.46);

        doTest(url);
    }

    @LargeTest
    public void testDaytonaBeach() throws Exception {
        String url = getTestUrl(29.18, -81.05);

        doTest(url);
    }

    @LargeTest
    public void testJacksonville() throws Exception {
        String url = getTestUrl(30.33, -81.52);

        doTest(url);
    }

    @LargeTest
    public void testKeyWest() throws Exception {
        String url = getTestUrl(24.55, -81.75);

        doTest(url);
    }

    @LargeTest
    public void testAtlanta() throws Exception {
        String url = getTestUrl(33.65, -84.42);

        doTest(url);
    }

    @LargeTest
    public void testHilo() throws Exception {
        String url = getTestUrl(19.72, -155.07);

        doTest(url);
    }

    @LargeTest
    public void testMaui() throws Exception {
        String url = getTestUrl(20.97, -156.83);

        doTest(url);
    }

    @LargeTest
    public void testBoise() throws Exception {
        String url = getTestUrl(43.57, -116.22);

        doTest(url);
    }

    @LargeTest
    public void testChicago() throws Exception {
        String url = getTestUrl(41.90, -87.65);

        doTest(url);
    }

    @LargeTest
    public void testMarion() throws Exception {
        String url = getTestUrl(37.757, -89.00);

        doTest(url);
    }

    @LargeTest
    public void testGary() throws Exception {
        String url = getTestUrl(41.62, -87.42);

        doTest(url);
    }

    @LargeTest
    public void testCedarRapids() throws Exception {
        String url = getTestUrl(41.88, -91.70);

        doTest(url);
    }

    @LargeTest
    public void testWaterloo() throws Exception {
        String url = getTestUrl(42.55, -92.40);

        doTest(url);
    }

    @LargeTest
    public void testDodgeCity() throws Exception {
        String url = getTestUrl(37.77, -99.97);

        doTest(url);
    }

    @LargeTest
    public void testTopeka() throws Exception {
        String url = getTestUrl(39.07, -95.62);

        doTest(url);
    }

    @LargeTest
    public void testLouisville() throws Exception {
        String url = getTestUrl(38.23, -85.67);

        doTest(url);
    }

    @LargeTest
    public void testFortKnox() throws Exception {
        String url = getTestUrl(37.90, -85.97);

        doTest(url);
    }

    @LargeTest
    public void testBatonRouge() throws Exception {
        String url = getTestUrl(30.53, -91.15);

        doTest(url);
    }

    @LargeTest
    public void testGrandIsle() throws Exception {
        String url = getTestUrl(29.18, -90.07);

        doTest(url);
    }

    @LargeTest
    public void testNewOrleans() throws Exception {
        String url = getTestUrl(29.98, -90.25);

        doTest(url);
    }

    @LargeTest
    public void testBangor() throws Exception {
        String url = getTestUrl(44.80, -68.82);

        doTest(url);
    }

    @LargeTest
    public void testPortland() throws Exception {
        String url = getTestUrl(43.65, -70.32);

        doTest(url);
    }

    @LargeTest
    public void testBaltimore() throws Exception {
        String url = getTestUrl(39.18, -76.67);

        doTest(url);
    }

    @LargeTest
    public void testBedford() throws Exception {
        String url = getTestUrl(42.47, -71.28);

        doTest(url);
    }

    @LargeTest
    public void testBoston() throws Exception {
        String url = getTestUrl(42.37, -71.03);

        doTest(url);
    }

    @LargeTest
    public void testAnnArbor() throws Exception {
        String url = getTestUrl(42.22, -83.75);

        doTest(url);
    }

    @LargeTest
    public void testDetroit() throws Exception {
        String url = getTestUrl(42.42, -83.02);

        doTest(url);
    }

    @LargeTest
    public void testFlint() throws Exception {
        String url = getTestUrl(42.97, -83.75);

        doTest(url);
    }

    @LargeTest
    public void testLansing() throws Exception {
        String url = getTestUrl(42.77, -84.60);

        doTest(url);
    }

    @LargeTest
    public void testDuluth() throws Exception {
        String url = getTestUrl(46.83, -92.18);

        doTest(url);
    }

    @LargeTest
    public void testGrandRapids() throws Exception {
        String url = getTestUrl(47.22, -93.52);

        doTest(url);
    }

    @LargeTest
    public void testRochester() throws Exception {
        String url = getTestUrl(43.92, -92.50);

        doTest(url);
    }

    @LargeTest
    public void testMcComb() throws Exception {
        String url = getTestUrl(31.18, -90.47);

        doTest(url);
    }

    @LargeTest
    public void testKansasCity() throws Exception {
        String url = getTestUrl(39.12, -94.60);

        doTest(url);
    }


    @LargeTest
    public void testSpringfield() throws Exception {
        String url = getTestUrl(37.23, -93.38);

        doTest(url);
    }

    @LargeTest
    public void testStLouis() throws Exception {
        String url = getTestUrl(38.75, -90.37);

        doTest(url);
    }

    @LargeTest
    public void testBillings() throws Exception {
        String url = getTestUrl(45.80, -108.53);

        doTest(url);
    }

    @LargeTest
    public void testBillon() throws Exception {
        String url = getTestUrl(45.25, -112.55);

        doTest(url);
    }

    @LargeTest
    public void testHelena() throws Exception {
        String url = getTestUrl(46.60, -112.00);

        doTest(url);
    }

    @LargeTest
    public void testMissoula() throws Exception {
        String url = getTestUrl(46.92, -114.08);

        doTest(url);
    }

    @LargeTest
    public void testAlliance() throws Exception {
        String url = getTestUrl(42.05, -102.80);

        doTest(url);
    }

    @LargeTest
    public void testFallsCity() throws Exception {
        String url = getTestUrl(40.07, -95.58);

        doTest(url);
    }

    @LargeTest
    public void testNorthOmaha() throws Exception {
        String url = getTestUrl(41.37, -96.02);

        doTest(url);
    }

    @LargeTest
    public void testBattleMountian() throws Exception {
        String url = getTestUrl(40.62, -116.87);

        doTest(url);
    }

    @LargeTest
    public void testElko() throws Exception {
        String url = getTestUrl(40.83, -115.78);

        doTest(url);
    }

    @LargeTest
    public void testMercury() throws Exception {
        String url = getTestUrl(36.62, -116.02);

        doTest(url);
    }

    @LargeTest
    public void testTonopah() throws Exception {
        String url = getTestUrl(38.07, -117.08);

        doTest(url);
    }

    @LargeTest
    public void testConcord() throws Exception {
        String url = getTestUrl(43.20, -71.50);

        doTest(url);
    }

    @LargeTest
    public void testAtlanticCity() throws Exception {
        String url = getTestUrl(39.45, -74.57);

        doTest(url);
    }

    @LargeTest
    public void testTrenton() throws Exception {
        String url = getTestUrl(40.28, -74.82);

        doTest(url);
    }

    @LargeTest
    public void testCarlsbad() throws Exception {
        String url = getTestUrl(32.33, -104.27);

        doTest(url);
    }

    @LargeTest
    public void testRoswell() throws Exception {
        String url = getTestUrl(33.30, -104.53);

        doTest(url);
    }

    @LargeTest
    public void testAlbany() throws Exception {
        String url = getTestUrl(42.75, -73.80);

        doTest(url);
    }

    @LargeTest
    public void testIthaca() throws Exception {
        String url = getTestUrl(42.48, -76.47);

        doTest(url);
    }

    @LargeTest
    public void testNewYork() throws Exception {
        String url = getTestUrl(40.65, -73.78);

        doTest(url);
    }

    @LargeTest
    public void testAsheville() throws Exception {
        String url = getTestUrl(35.43, -82.55);

        doTest(url);
    }

    @LargeTest
    public void testFortBragg() throws Exception {
        String url = getTestUrl(35.13, -78.93);

        doTest(url);
    }

    @LargeTest
    public void testDickenson() throws Exception {
        String url = getTestUrl(46.78, -102.80);

        doTest(url);
    }

    @LargeTest
    public void testLidgerwood() throws Exception {
        String url = getTestUrl(46.10, -97.15);

        doTest(url);
    }

    @LargeTest
    public void testCanton() throws Exception {
        String url = getTestUrl(40.92, -81.43);

        doTest(url);
    }

    @LargeTest
    public void testToledo() throws Exception {
        String url = getTestUrl(41.60, -83.80);

        doTest(url);
    }

    @LargeTest
    public void testArdmore() throws Exception {
        String url = getTestUrl(34.30, -97.02);

        doTest(url);
    }

    @LargeTest
    public void testLawton() throws Exception {
        String url = getTestUrl(34.57, -98.42);

        doTest(url);
    }

    @LargeTest
    public void testPage() throws Exception {
        String url = getTestUrl(34.68, -94.62);

        doTest(url);
    }

    @LargeTest
    public void testTulsa() throws Exception {
        String url = getTestUrl(36.20, -95.90);

        doTest(url);
    }

    @LargeTest
    public void testBaker() throws Exception {
        String url = getTestUrl(44.83, -117.82);

        doTest(url);
    }

    @LargeTest
    public void testNewport() throws Exception {
        String url = getTestUrl(44.63, -124.05);

        doTest(url);
    }

    @LargeTest
    public void testAltoona() throws Exception {
        String url = getTestUrl(40.30, -78.32);

        doTest(url);
    }

    @LargeTest
    public void testDubois() throws Exception {
        String url = getTestUrl(41.18, -78.90);

        doTest(url);
    }

    @LargeTest
    public void testPittsburgh() throws Exception {
        String url = getTestUrl(40.50, -80.22);

        doTest(url);
    }

    @LargeTest
    public void testStateCollege() throws Exception {
        String url = getTestUrl(40.85, -77.83);

        doTest(url);
    }

    @LargeTest
    public void testBlockIsland() throws Exception {
        String url = getTestUrl(41.17, -71.58);

        doTest(url);
    }

    @LargeTest
    public void testCharleston() throws Exception {
        String url = getTestUrl(32.90, -80.03);

        doTest(url);
    }

    @LargeTest
    public void testMyrtleBeach() throws Exception {
        String url = getTestUrl(33.68, -78.93);

        doTest(url);
    }

    @LargeTest
    public void testAberdeen() throws Exception {
        String url = getTestUrl(45.45, -98.43);

        doTest(url);
    }

    @LargeTest
    public void testEllsworth() throws Exception {
        String url = getTestUrl(44.15, -103.10);

        doTest(url);
    }

    @LargeTest
    public void testPhilip() throws Exception {
        String url = getTestUrl(44.05, -101.60);

        doTest(url);
    }

    @LargeTest
    public void testBristol() throws Exception {
        String url = getTestUrl(36.48, -82.40);

        doTest(url);
    }

    @LargeTest
    public void testKnoxville() throws Exception {
        String url = getTestUrl(35.82, -83.98);

        doTest(url);
    }

    @LargeTest
    public void testAmarillo() throws Exception {
        String url = getTestUrl(35.23, -101.70);

        doTest(url);
    }

    @LargeTest
    public void testChildress() throws Exception {
        String url = getTestUrl(34.43, -100.28);

        doTest(url);
    }

    @LargeTest
    public void testDalhart() throws Exception {
        String url = getTestUrl(36.02, -102.55);

        doTest(url);
    }

    @LargeTest
    public void testHouston() throws Exception {
        String url = getTestUrl(29.97, -95.36);

        doTest(url);
    }

    @LargeTest
    public void testLufkin() throws Exception {
        String url = getTestUrl(31.23, -94.75);

        doTest(url);
    }

    @LargeTest
    public void testSanAntonio() throws Exception {
        String url = getTestUrl(29.33, -98.47);

        doTest(url);
    }

    @LargeTest
    public void testWink() throws Exception {
        String url = getTestUrl(31.78, -103.20);

        doTest(url);
    }

    @LargeTest
    public void testDelta() throws Exception {
        String url = getTestUrl(39.33, -112.58);

        doTest(url);
    }

    @LargeTest
    public void testLogan() throws Exception {
        String url = getTestUrl(41.78, -111.85);

        doTest(url);
    }

    @LargeTest
    public void testBurlington() throws Exception {
        String url = getTestUrl(44.47, -73.15);

        doTest(url);
    }

    @LargeTest
    public void testLangley() throws Exception {
        String url = getTestUrl(37.08, -76.37);

        doTest(url);
    }

    @LargeTest
    public void testHanford() throws Exception {
        String url = getTestUrl(46.57, -119.60);

        doTest(url);
    }

    @LargeTest
    public void testSpokane() throws Exception {
        String url = getTestUrl(47.63, -117.53);

        doTest(url);
    }

    @LargeTest
    public void testBeckley() throws Exception {
        String url = getTestUrl(37.78, -81.12);

        doTest(url);
    }

    @LargeTest
    public void testWheeling() throws Exception {
        String url = getTestUrl(40.18, -80.65);

        doTest(url);
    }

    @LargeTest
    public void testAppleton() throws Exception {
        String url = getTestUrl(44.25, -88.52);

        doTest(url);
    }

    @LargeTest
    public void testCasper() throws Exception {
        String url = getTestUrl(42.92, -106.47);

        doTest(url);
    }

    @LargeTest
    public void testYellowstone() throws Exception {
        String url = getTestUrl(44.55, -110.42);

        doTest(url);
    }

    private void doTest(String url) throws Exception {
        assertNotNull(url);

        DWML response = downloadUrl(url);

        assertNotNull("DWML response was null for url: " + url, response);
        assertNotNull("Data was null for url: " + url, response.data);
        assertTrue("Data did not have any elements for url: " + url, response.data.size() > 0);
        assertNotNull("Data did not have any time layouts in it for url: " + url, response.data.get(0).timeLayout);
        assertNotNull("Data did not have any parameters in it for url: " + url, response.data.get(0).parameters);
    }

    private String getTestUrl(double latitude, double longitude) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Class<CirrusClient> weatherClient = CirrusClient.class;
        Method method = weatherClient.getDeclaredMethod("getWeatherForecastUrl", Double.class, Double.class);
        method.setAccessible(true);
        return (String) method.invoke(mCirrusClient, latitude, longitude);
    }

    private DWML downloadUrl(String myurl) throws Exception {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            Log.d(TAG, "Connecting to URL: " + myurl);

            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(30000 /* milliseconds */);
            conn.setConnectTimeout(60000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            is = conn.getInputStream();

            return mSerializer.read(DWML.class, is);

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
}
