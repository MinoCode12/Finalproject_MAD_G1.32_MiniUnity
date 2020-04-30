package com.example.feedbackpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class aboutus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        simulateDayNight(/*DAY*/0);
        Element adsElement = new Element();
        adsElement.setTitle("Advertise with us");



        View Aboutus = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.healthcare)
                .addItem(new Element().setTitle("Version 1.0"))
                .addItem(adsElement)
                .setDescription("Mini Unity app is a Emergency Contact Application. So you can contact Any life thread you have use this app and get help immediately. " +
                        "SLIIT MAD Group Year 2 Sem2 01.1 Natural Selection Group. All right reserved.")
                .addGroup("Connect with us")
                .addEmail("gayan982822513.lk@gmail.com")
                .addFacebook("Mini Unity")
                .addTwitter("Mini Unity")
                .addPlayStore("Mini Unity")
                .addGitHub("Gayan Kavinda")
                .addYoutube("Mini Unity")
                .addItem(copyright())
                .create();
        setContentView(R.layout.activity_aboutus);
    }

    private void simulateDayNight(int currentsetting) {
        int Day = 0;
        int NIGHT = 1;
        int FOLLOW_SYSTEM = 3;
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (currentsetting == Day && currentNightMode != Configuration.UI_MODE_NIGHT_NO) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else if (currentsetting == NIGHT && currentNightMode != Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else if (currentsetting == FOLLOW_SYSTEM) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }
    }

    private Element copyright() {
        Element copyRightsElement = new Element();
        final String copyrights = String.format("Copyright %d by Gayan Kavinda", Calendar.getInstance().get(Calendar.YEAR));
        copyRightsElement.setTitle(copyrights);
        copyRightsElement.setIcon(R.mipmap.ic_launcher);
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(aboutus.this, copyrights, Toast.LENGTH_SHORT);
            }
        });
        return copyRightsElement;
    }
}
