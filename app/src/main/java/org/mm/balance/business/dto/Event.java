package org.mm.balance.business.dto;

import org.mm.balance.business.dto.tools.EventImage;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Event {

    private String name;
    private Date dateEvent;
    private EventImage eventImage;

    private List<Participant> participants;
    private List<Cost> costs;

    public Event(String name, EventImage eventImage) {
        this.name = name;
        this.eventImage = eventImage;
    }

    public void addParticipant(Participant participant) {
        this.participants.add(participant);
    }

    public void addCost(Cost cost) {
        this.costs.add(cost);
    }

    public int getTotalParticipant() {
        return participants.size();
    }

    public long getTotalCost() {
        long total = 0;

        for (Cost cost: costs) {
            total += cost.getAmount();
        }

        return total;
    }
}
