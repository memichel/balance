package org.mm.balance.business.db.table;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.mm.balance.business.db.dao.impl.ContributorDao;

import java.io.Serializable;

/**
 * Cette classe contient les informations sur ce que doit payer ou recevoir un participant sur un co�t.
 *
 * @author Max
 */
@DatabaseTable(tableName = "contributor", daoClass = ContributorDao.class)
public class Contributor implements Parcelable, Serializable {
    private static final long serialVersionUID = 1L;

    public static final Creator<Contributor> CREATOR = new Creator<Contributor>() {
        public Contributor createFromParcel(Parcel source) {
            return new Contributor(source);
        }

        public Contributor[] newArray(int size) {
            return new Contributor[size];
        }
    };

    // Titre des colonnes de la DB
    public final static String COL_ID = "id";
    public final static String COL_PERCENTAGE = "percentage";
    public final static String COL_REIMBOURSEMENT = "reimboursement";
    public final static String COL_PART_ID = "part_id";
    public final static String COL_COST_ID = "cost_id";

    @DatabaseField(columnName = COL_ID, generatedId = true)
    private int id;

    @DatabaseField(columnName = COL_PERCENTAGE)
    private float percentage;

    @DatabaseField(columnName = COL_REIMBOURSEMENT)
    private float reimboursement;

    @DatabaseField(columnName = COL_PART_ID, foreign = true)
    private Participant part;

    @DatabaseField(columnName = COL_COST_ID, foreign = true)
    private Cost cost;

    public Contributor() {
    }

    public Contributor(final Participant part, final Cost cost) {
        this.part = part;
        this.cost = cost;
        percentage = 100f;
    }

    protected Contributor(Parcel in) {
        this.id = in.readInt();
        this.percentage = in.readFloat();
        this.reimboursement = in.readFloat();
        this.part = in.readParcelable(Participant.class.getClassLoader());
        this.cost = in.readParcelable(Cost.class.getClassLoader());
    }

    public Participant getPart() {
        return part;
    }

    public void setPart(final Participant part) {
        this.part = part;
    }

    public Cost getCost() {
        return cost;
    }

    public void setCost(final Cost cost) {
        this.cost = cost;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(final float percentage) {
        this.percentage = percentage;
    }

    public float getReimboursement() {
        return reimboursement;
    }

    public void setReimboursement(final float reimboursement) {
        this.reimboursement = reimboursement;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeFloat(this.percentage);
        dest.writeFloat(this.reimboursement);
        dest.writeParcelable(this.part, 0);
        dest.writeParcelable(this.cost, 0);
    }
}
