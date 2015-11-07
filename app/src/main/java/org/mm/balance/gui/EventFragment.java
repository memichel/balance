package org.mm.balance.gui;

import org.mm.balance.BaseFragment;
import org.mm.balance.R;

/**
 * Created by max on 07/11/2015.
 */
public class EventFragment extends BaseFragment {

    public static EventFragment newInstance() {
        return new EventFragment();
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_event;
    }

}
