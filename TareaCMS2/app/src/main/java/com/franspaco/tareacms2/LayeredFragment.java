package com.franspaco.tareacms2;

import android.support.v4.app.Fragment;

public class LayeredFragment extends Fragment {
    protected int layer = 0;

    public boolean onBaseLayer(){
        return layer == 0;
    }

    public void resetLayer(){
        layer = 0;
    }
}
