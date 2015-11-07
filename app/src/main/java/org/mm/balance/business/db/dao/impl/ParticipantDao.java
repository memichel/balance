package org.mm.balance.business.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import org.mm.balance.business.db.dao.interf.IParticipantDao;
import org.mm.balance.business.db.table.Participant;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParticipantDao extends BaseDaoImpl<Participant, Integer> implements IParticipantDao {

	// this constructor must be defined
	public ParticipantDao(final ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Participant.class);
	}


	/**
	 * Cette m�thode permet de tester si le participant est pr�sent sur l'�v�nement.
	 * @param participant
	 * @return true s'il n'est pas pr�sent false sinon
	 * @throws SQLException
	 */
	public boolean isntAlreadyAdd(final Participant participant) throws SQLException{

		// Map pour tester si le participant n'est pas d�j� pr�sent sur l'�v�nement
		final Map<String, Object> testMap = new HashMap<String, Object>();
		testMap.put(Participant.COL_NAME, participant.getName());
		testMap.put(Participant.COL_EVENT_ID, participant.getEvent());

		if (queryForFieldValues(testMap).size() == 0)
			return true;
		else
			return false;
	}


	public Participant getParticipantNameEvent(final Participant participant) throws SQLException{

		// Map pour tester si le participant n'est pas d�j� pr�sent sur l'�v�nement
		final Map<String, Object> testMap = new HashMap<String, Object>();
		testMap.put(Participant.COL_NAME, participant.getName());
		testMap.put(Participant.COL_EVENT_ID, participant.getEvent());

		final List<Participant> lst = queryForFieldValues(testMap);

		if (lst.size() == 1)
			return lst.get(0);

		return null;
	}


}
