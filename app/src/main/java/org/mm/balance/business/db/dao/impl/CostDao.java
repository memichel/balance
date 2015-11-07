package org.mm.balance.business.db.dao.impl;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import org.mm.balance.business.db.dao.interf.ICostDao;
import org.mm.balance.business.db.table.Cost;

import java.sql.SQLException;

public class CostDao extends BaseDaoImpl<Cost, Integer> implements ICostDao {

	// this constructor must be defined
    public CostDao(final ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Cost.class);
    }

}
