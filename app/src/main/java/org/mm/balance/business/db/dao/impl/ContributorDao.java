package org.mm.balance.business.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import org.mm.balance.business.db.dao.interf.IContributorDao;
import org.mm.balance.business.db.table.Contributor;

import java.sql.SQLException;

public class ContributorDao extends BaseDaoImpl<Contributor, Integer> implements IContributorDao {

	public ContributorDao(final ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Contributor.class);
	}
}
