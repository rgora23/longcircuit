package com.nineinchmales.longcircuit.Activies;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;

import com.nineinchmales.longcircuit.Activities.GameActivity;
import com.nineinchmales.longcircuit.Activities.MainActivity;
import com.nineinchmales.longcircuit.BuildConfig;
import com.nineinchmales.longcircuit.R;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)

public class MainActivityTest {
    @Test
    public void clickingPlayButton_shouldLaunchGameActivity() throws Exception {
        Activity mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
        Button playButton = (Button) mainActivity.findViewById(R.id.play_button);
        playButton.performClick();
        assertActivityStarted(mainActivity, GameActivity.class);
    }

    // TODO make this assert not be shitty
    private void assertActivityStarted(Activity activity, Class<? extends Activity> clazz) {
        Intent expectedIntent = new Intent(activity, clazz);
        assertEquals(true, shadowOf(activity).getNextStartedActivity().filterEquals(expectedIntent));
    }
}