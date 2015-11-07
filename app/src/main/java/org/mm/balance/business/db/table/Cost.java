package org.mm.balance.business.db.table;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import org.mm.balance.business.db.dao.impl.CostDao;

import java.io.Serializable;
import java.util.Date;

@DatabaseTable(tableName = "cost", daoClass = CostDao.class)
public class Cost implements Parcelable, Serializable, Comparable<Cost> {
    private static final long serialVersionUID = 1L;

    public static final Creator<Cost> CREATOR = new Creator<Cost>() {
        public Cost createFromParcel(Parcel source) {
            return new Cost(source);
        }

        public Cost[] newArray(int size) {
            return new Cost[size];
        }
    };

    // Pour les libell�s de d�penses
    public static final String TYPE1 = "Apero";
    public static final String TYPE2 = "Nourriture";
    public static final String TYPE3 = "Autres";

    // Titre des colonnes de la DB
    public final static String COL_ID = "id_cost";
    public final static String COL_NAME = "name_cost";
    public final static String COL_VALUE = "value_cost";
    public final static String COL_TYPE = "type_cost";
    public final static String COL_DATE = "date_cost";
    public final static String COL_PARTICIPANT_ID = "participant_id";
    public final static String COL_CONTRIBUTOR_LIST = "contributor";


    @DatabaseField(columnName = COL_ID, generatedId = true)
    private int id;

    @DatabaseField(columnName = COL_NAME, canBeNull = true)
    private String name;

    @DatabaseField(columnName = COL_VALUE, canBeNull = true)
    private float value;

    @DatabaseField(columnName = COL_TYPE, canBeNull = true)
    private String type;

    @DatabaseField(columnName = COL_DATE, canBeNull = true)
    private Date date;

    @DatabaseField(columnName = COL_PARTICIPANT_ID, foreign = true)
    private Participant participant;

    @ForeignCollectionField(columnName = COL_CONTRIBUTOR_LIST, eager = false)
    private ForeignCollection<Contributor> contributors;


    // Constructeur pour ORMLite
    public Cost() {
    }

    public Cost(final String name1, final float value1, final String type1, final Participant p) {
        name = name1;
        value = value1;
        type = type1;
        participant = p;
        date = new Date();
    }

    protected Cost(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.value = in.readFloat();
        this.type = in.readString();
        long tmpDate = in.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
        this.participant = in.readParcelable(Participant.class.getClassLoader());
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(final float value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(final Participant participant) {
        this.participant = participant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public ForeignCollection<Contributor> getContributors() {
        return contributors;
    }

    public void setContributors(final ForeignCollection<Contributor> contributors) {
        this.contributors = contributors;
    }

    @Override
    public int compareTo(final Cost another) {
        return name.compareTo(another.name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeFloat(this.value);
        dest.writeString(this.type);
        dest.writeLong(date != null ? date.getTime() : -1);
        dest.writeParcelable(this.participant, 0);
    }
}
