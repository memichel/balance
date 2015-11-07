package org.mm.balance.gui;

import org.mm.balance.BaseFragment;
import org.mm.balance.R;

/**
 * Created by max on 07/11/2015.
 */
public class SettingFragment extends BaseFragment {

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_setting;
    }
}
