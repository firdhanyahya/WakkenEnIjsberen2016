package com.example.firdhan.wakkenenijsberen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Robin T on 9-12-2016.
 */

public class HowToPlay extends FragmentActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.screen_slide);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        // De minumum schaal in float points
        private static final float MIN_SCALE = 0.85f;
        // Het minimum in vervaging.
        private static final float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(View view, float position) {
            // Krijg de pagina lengte
            int pageWidth = view.getWidth();
            // De pagina breedte
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Oneindigheid,-1)
                // Deze pagina is ver buiten het scherm naar links.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Wijzigt de standaard dia overgang transitie, zodat de pagina ook krimpt.
                float schaalFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMarge = pageHeight * (1 - schaalFactor) / 2;
                float horzMarge = pageWidth * (1 - schaalFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMarge - vertMarge / 2);
                } else {
                    view.setTranslationX(-horzMarge + vertMarge / 2);
                }

                // Verklein de fragment naar beneden (tussen de minimum schaal en 1)
                view.setScaleX(schaalFactor);
                view.setScaleY(schaalFactor);

                // Vervaag de fragment naar de relatieve grootte.
                view.setAlpha(MIN_ALPHA +
                        (schaalFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Oneindigheid]
                // Dit scherm is ver weg van de pagina aan de rechterkant.
                view.setAlpha(0);
            }
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return new WelkomFragment();
                case 1: return new Intro();
                case 2: return new Stap1();
                case 3: return new Stap2();
                case 4: return new Stap3();
                case 5: return new Stap4();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 6;
        }
    }
}
