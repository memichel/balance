package org.mm.balance.business.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import org.mm.balance.business.db.dao.interf.IEventDao;
import org.mm.balance.business.db.table.Event;

import java.sql.SQLException;

public class EventDao extends BaseDaoImpl<Event, Integer> implements IEventDao {

	public EventDao(final ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Event.class);
	}

}
