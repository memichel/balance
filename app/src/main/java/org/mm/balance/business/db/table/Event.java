package org.mm.balance.business.db.table;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import org.mm.balance.business.db.dao.impl.EventDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@DatabaseTable(tableName = "event", daoClass = EventDao.class)
public class Event implements Parcelable, Serializable {
    private static final long serialVersionUID = 1L;

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        public Event createFromParcel(Parcel source) {
            return new Event(source);
        }

        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    // Titre des colonnes de la DB
    public final static String COL_ID = "id_event";
    public final static String COL_NAME = "name_event";
    public final static String COL_COST = "total_cost_event";
    public final static String COL_PARTICIPANT_LIST = "participant_list";
    public final static String COL_DATE = "date_event";


    @DatabaseField(columnName = COL_ID, generatedId = true)
    private int id;

    @DatabaseField(columnName = COL_NAME, canBeNull = false)
    private String name;

    @DatabaseField(columnName = COL_DATE)
    private Date dateEvent;

    @ForeignCollectionField(columnName = COL_PARTICIPANT_LIST, eager = false)
    private ForeignCollection<Participant> participants;

    // Constructeur pour ORMLite
    public Event() {
    }

    // Constructeur
    public Event(final String name1) {
        this.name = name1;
        this.dateEvent = new Date();
    }

    protected Event(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        long tmpDateEvent = in.readLong();
        this.dateEvent = tmpDateEvent == -1 ? null : new Date(tmpDateEvent);
    }

    public void addParticipant(final Participant p) {
        this.participants.add(p);
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Retourne le cout de l'ensemble des d�penses de l'�v�nement
     *
     * @return
     */
    public float getTotalCost() {
        float totalCost = 0;

        if (this.participants != null)
            for (Participant p : this.participants)
                for (Cost c : p.getCosts())
                    totalCost += c.getValue();

        return totalCost;
    }

    public ForeignCollection<Participant> getParticipants() {
        return this.participants;
    }

    /**
     * Permet de convertir la ForeignCollection en List tri�
     *
     * @return la liste des r�sidents tri�e
     */
    public List<Participant> getSortParticipants() {
        List<Participant> lstParticipant = new ArrayList<Participant>();

        if (this.participants != null) {
            for (Participant p : this.participants) {
                lstParticipant.add(p);
            }
            Collections.sort(lstParticipant);
        }
        return lstParticipant;
    }

    /**
     * Permet de trouver l'index du participant pass� en param�tre
     *
     * @param p participant recherch�
     * @return
     */
    public int getIndexParti(final Participant p) {
        boolean find = false;
        List<Participant> participants = this.getSortParticipants();
        int i = -1;
        while (!find && (i < participants.size())) {
            i++;
            if (participants.get(i).getId() == p.getId())
                find = true;
        }

        return i;
    }

    public Date getDateEvent() {
        return this.dateEvent;
    }

    public void setDateEvent(final Date dateEvent) {
        this.dateEvent = dateEvent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeLong(dateEvent != null ? dateEvent.getTime() : -1);
    }

}
