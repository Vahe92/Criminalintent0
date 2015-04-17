package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;import com.bignerdranch.android.criminalintent.CrimeListFragment;import com.bignerdranch.android.criminalintent.SingleFragmentActivity;import java.lang.Override;

/**
 * Created by Vahe on 4/5/2015.
 */
public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
