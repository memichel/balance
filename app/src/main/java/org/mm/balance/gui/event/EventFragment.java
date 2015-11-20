package org.mm.balance.gui.event;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionMenu;

import org.mm.balance.BaseFragment;
import org.mm.balance.R;
import org.mm.balance.business.dto.Event;
import org.mm.balance.business.dto.tools.EventImage;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by max on 07/11/2015.
 */
public class EventFragment extends BaseFragment implements EventAdapter.OnClickListener {

    public static final String TAG = EventFragment.class.getSimpleName();

    @Bind(R.id.custom_cardview_no_item_container)
    protected CardView mCardViewNoItem;
    @Bind(R.id.custom_cardview_no_item_image)
    protected ImageView mCardViewNoItemIv;
    @Bind(R.id.custom_cardview_no_item_text)
    protected TextView mCardViewNoItem_Tv;

    @Bind(R.id.fragment_event_recyclerview)
    protected RecyclerView mRecyclerView;

    @Bind(R.id.fragment_event_fab_menu)
    protected FloatingActionMenu mFabMenu;
    @Bind(R.id.fragment_event_fab_add)
    protected FloatingActionMenu mFabAdd;
    @Bind(R.id.fragment_event_fab_edit)
    protected FloatingActionMenu mFabEdit;

    private EventAdapter mEventAdapter;

    public static EventFragment newInstance() {
        return new EventFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        ButterKnife.bind(this, v);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fillContentView();

        return v;
    }

    private void fillContentView() {
        List<Event> events = new ArrayList<>();
        events.add(new Event("Lyon", EventImage.IMAGE1));
        events.add(new Event("Paris", EventImage.IMAGE2));

        mEventAdapter = new EventAdapter(getContext(), this, events);
        mRecyclerView.setAdapter(mEventAdapter);

        handleViewState();
    }

    private void handleViewState() {
        if (mEventAdapter != null && mEventAdapter.getItemCount() > 0) {
            mCardViewNoItem.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);

        } else {
            mCardViewNoItem.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);

        }
    }

    @Override
    protected String getFragmentTag() {
        return TAG;
    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_event;
    }

    @Override
    public void onEventListClicked(Event event) {

    }
}
