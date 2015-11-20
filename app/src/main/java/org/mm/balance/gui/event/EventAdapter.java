package org.mm.balance.gui.event;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.mm.balance.R;
import org.mm.balance.business.dto.Event;
import org.mm.balance.tools.CurrencyHelper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by max on 07/11/2015.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventCardView> {

    private Context mContext;
    private OnClickListener mListener;
    private List<Event> mDataSet;

    public EventAdapter(Context context, OnClickListener listener, List<Event> mDataSet) {
        this.mContext = context;
        this.mListener = listener;
        this.mDataSet = mDataSet;
    }

    @Override
    public EventCardView onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EventCardView(LayoutInflater.from(mContext).inflate(R.layout.item_event, parent, false));
    }

    @Override
    public void onBindViewHolder(EventCardView holder, int position) {

        Event event = mDataSet.get(position);

        holder.eventNameEt.setText(event.getName());
        holder.eventPeopleNbTv.setText(event.getParticipants().size());

        holder.eventTotalCostTv.setText(CurrencyHelper.formatIntoCurrencyString(event.getTotalCost()));
        holder.eventPeopleNbTv.setText(event.getTotalParticipant());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    static class EventCardView extends RecyclerView.ViewHolder {

        @Bind(R.id.item_event_image)
        protected ImageView eventIv;

        @Bind(R.id.item_event_name)
        protected TextView eventNameEt;

        @Bind(R.id.item_event_people_number)
        protected TextView eventPeopleNbTv;

        @Bind(R.id.item_event_total_cost)
        protected TextView eventTotalCostTv;

        public EventCardView(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnClickListener {
        void onEventListClicked(Event event);
    }

}
