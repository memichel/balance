package org.mm.balance;

import android.content.Context;
import android.content.Intent;

public class MainActivity extends BaseActivity {

    public static Intent getIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }
}
