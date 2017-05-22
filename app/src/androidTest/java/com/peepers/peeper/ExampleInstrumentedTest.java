package com.peepers.peeper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.peepers.peeper", appContext.getPackageName());
    }

    @Rule
    public ActivityTestRule<SiteListActivity> mActivityRule = new ActivityTestRule<>(SiteListActivity.class);

    @Test
    public void testIsConnected() throws Exception {
        Context context = mActivityRule.getActivity().getBaseContext();
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean connected = cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
