package com.kalher.henu.progressbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kalher.henu.progressbar.Fragment.MainFragment;
import com.kalher.henu.progressbar.GlobalConstants.FragmentTags;

public class MainActivity extends AppCompatActivity implements MainFragment.MainFragmentInteractionListener{

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setMainFragment();
    }

    @Override
    public void onBackPressed() {
        if(mFragmentManager != null && mFragmentManager.getBackStackEntryCount() > 0){
            mFragmentManager.popBackStack();
        }else {
            super.onBackPressed();
        }
    }

    public void setMainFragment(){
        MainFragment mainFragment = new MainFragment();
        if(mFragmentManager == null){
            mFragmentManager = getSupportFragmentManager();
        }
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.setCustomAnimations(R.animator.transition_from_right_to_left, R.animator.transition_from_left_to_right);
        mFragmentTransaction.replace(R.id.main_fragment_container, mainFragment, FragmentTags.FragmentMain);
        mFragmentTransaction.commit();
        // don't add mainFragment to backstack otherwise on first BackPress it will remove it at first and then show blank activity screen and then on second BackPress it will exit from app.
    }

    @Override
    public void showFragment(Fragment fragment, String fragTag){
        if(mFragmentManager == null){
            mFragmentManager = getSupportFragmentManager();
        }
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.setCustomAnimations(R.animator.transition_from_right_to_left, R.animator.transition_from_left_to_right);
        mFragmentTransaction.replace(R.id.main_fragment_container, fragment, fragTag);
        mFragmentTransaction.addToBackStack(fragTag);
        mFragmentTransaction.commit();
    }

}
/*
* Add - useful if want to show several fragments simultaneously, screen is occupied by several fragments.
* Replace - useful if want to show only one fragment at a time usually used when fragment occupy whole screen.
* **/
