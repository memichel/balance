package org.mm.balance.business.db.table;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import org.mm.balance.business.db.dao.impl.ParticipantDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DatabaseTable(tableName = "participant", daoClass = ParticipantDao.class)
public class Participant implements Parcelable, Serializable, Comparable<Participant>{
	private static final long serialVersionUID = 1L;

	// Titre des colonnes de la DB
	public final static String COL_ID = "id_part";
	public final static String COL_NAME = "name_part";
	public final static String COL_COST = "total_cost_part";
	public final static String COL_EVENT_ID = "event_id_part";
	public final static String COL_COST_LIST = "cost_list";
	public final static String COL_CONTRIBUTOR_LIST = "contributes";

	public final static String COL_PHONES_NUMBER = "phone_number";
	public final static String COL_MAILS = "mail";

	@DatabaseField(columnName = COL_ID, generatedId = true)
	private int id;

	@DatabaseField(columnName = COL_NAME, canBeNull = false)
	private String name;

	@DatabaseField(columnName = COL_PHONES_NUMBER)
	private String phoneNumber;

	@DatabaseField(columnName = COL_MAILS)
	private String mail;

	@DatabaseField(columnName = COL_EVENT_ID, foreign = true)
	private Event event;

	@ForeignCollectionField(columnName = COL_COST_LIST, eager = false)
	private ForeignCollection<Cost> costs;

	@ForeignCollectionField(columnName = COL_CONTRIBUTOR_LIST, eager = false)
	private ForeignCollection<Contributor> contributes;

	// Pour les remboursements des d�penses
	private Map<Participant, Float> paiementMap = new HashMap<Participant, Float>();
	private float totalReceivedCost = 0;
	private float tmpTotalReceivedCost = 0;

	// Constructeur pour ORMLite
	public Participant(){}

	// Constructeur
	public Participant(final String name1, final Event event){
		name = name1;
		this.event = event;
	}

	public Participant(final Parcel source) {
		id = source.readInt();
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

	public float getTotalCost() {
		float totalCost = 0;

		if (costs != null)
			for (final Cost c : costs)
				totalCost += c.getValue();

		return totalCost;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(final Event event) {
		this.event = event;
	}

	public ForeignCollection<Cost> getCosts() {
		return costs;
	}

	/**
	 * Permet de convertir la ForeignCollection en List tri�
	 * @return la liste des d�penses du r�sident
	 */
	public List<Cost> getSortCosts(){
		final List<Cost> lstCost = new ArrayList<Cost>();
		for (final Cost p: costs)
			lstCost.add(p);

		return lstCost;
	}

	public float getTotalReceivedCost() {
		return totalReceivedCost;
	}

	/**
	 * Permet d'appliquer ce que doit re�evoir le participant (sur les variables totalReceivedCost, tmpTotalRecevedCost
	 * Peut �tre n�gatif si le participant n'a pas eu de d�pense.
	 *
	 * @param c co�t (total des d�penses du participants) - (les d�penses que chaque participant doit).
	 */
	public void setTotalReceivedCost(final float c) {
		totalReceivedCost = c;
		tmpTotalReceivedCost = c;
	}

	public float getTmpTotalReceivedCost() {
		return tmpTotalReceivedCost;
	}

	public void setTmpTotalReceivedCost(final float tmpTotalReceivedCost) {
		this.tmpTotalReceivedCost = tmpTotalReceivedCost;
	}

	public Map<Participant, Float> getPaiementMap() {
		return paiementMap;
	}

	public void setPaiementMap(final Map<Participant, Float> paiementMap) {
		this.paiementMap = paiementMap;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(final String mail) {
		this.mail = mail;
	}

	/**
	 * Perte d'ajouter un paiement ou une r�ception + MAJ du tmpTotalReceivedCost pour en d�duire le reste.
	 *
	 * @param p Participant qui donne ou re�oit
	 * @param value >0 r�ception, <0 don.
	 */
	public void addParticipantPayment(final Participant p, final float value) {
		paiementMap.put(p, value);

		tmpTotalReceivedCost += value;
	}

	// ************ M�thode n�cessaire � cette classe pour la parc�lisation ************
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(final Parcel dest, final int flags) {
		dest.writeInt(id);
	}

	public static final Creator<Participant> CREATOR = new Creator<Participant>() {
		@Override
		public Participant createFromParcel(final Parcel source)
		{
			return new Participant(source);
		}

		@Override
		public Participant[] newArray(final int size)
		{
			return new Participant[size];
		}
	};

	@Override
	public int compareTo(final Participant another) {
		return name.compareTo(another.name);
	}

	@Override
	public String toString(){
		return name;
	}

}
