package org.mm.balance.business.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import org.mm.balance.business.db.dao.impl.ContributorDao;
import org.mm.balance.business.db.dao.impl.CostDao;
import org.mm.balance.business.db.dao.impl.EventDao;
import org.mm.balance.business.db.dao.impl.ParticipantDao;
import org.mm.balance.business.db.table.Contributor;
import org.mm.balance.business.db.table.Cost;
import org.mm.balance.business.db.table.Event;
import org.mm.balance.business.db.table.Participant;

public class DBHelper extends OrmLiteSqliteOpenHelper{

	private static final String DATABASE_NAME = "dbBonCompte.db";
	private static final int DATABASE_VERSION = 4;

	// Les Dao que l'on utilise pour la gestion des requ�tes sur les tables
	private EventDao eventDao = null;
	private ParticipantDao participantDao = null;
	private CostDao costDao = null;
	private ContributorDao contributorDao = null;

	private static DBHelper helper = null;

	public DBHelper(final Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Retourne l'instance de la BDD si elle est pr�sente, sinon, elle est cr��e
	public static DBHelper getHelper(final Context ctx){
		if (helper == null)
			return helper = new DBHelper(ctx);

		return helper;
	}

	@Override
	public void onCreate(final SQLiteDatabase arg0, final ConnectionSource arg1) {
		try {
			Log.i(this.getClass().getName(), "Tables created in" + DATABASE_NAME);
			TableUtils.createTable(connectionSource, Event.class);
			TableUtils.createTable(connectionSource, Participant.class);
			TableUtils.createTable(connectionSource, Cost.class);
			TableUtils.createTable(connectionSource, Contributor.class);

		} catch (final SQLException e) {
			Log.e(DBHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}


	@Override
	public void onUpgrade(final SQLiteDatabase db, final ConnectionSource connectionSource, final int arg2, final int arg3) {
		try {
			Log.i(this.getClass().getName(), "Modification DB" + DATABASE_NAME);
			TableUtils.dropTable(connectionSource, Event.class, true);
			TableUtils.dropTable(connectionSource, Participant.class, true);
			TableUtils.dropTable(connectionSource, Cost.class, true);
			TableUtils.dropTable(connectionSource, Contributor.class, true);
			this.onCreate(db, connectionSource);

		} catch (final SQLException e) {
			Log.e(DBHelper.class.getName(), "Can't drop database", e);
			throw new RuntimeException(e);
		}
	}


	public EventDao getEventDao() throws SQLException {
		Log.i(this.getClass().getName(), "BDD Event");
		if (null == eventDao)
			eventDao = this.getDao(Event.class);
		return eventDao;
	}

	public ParticipantDao getParticipantDao() throws SQLException {
		Log.i(this.getClass().getName(), "BDD Participant");
		if (null == participantDao)
			participantDao = this.getDao(Participant.class);
		return participantDao;
	}

	public CostDao getCostDao() throws SQLException {
		Log.i(this.getClass().getName(), "BDD Cost");
		if (null == costDao)
			costDao = this.getDao(Cost.class);
		return costDao;
	}

	public ContributorDao getContributorDao() throws SQLException {
		Log.i(this.getClass().getName(), "BDD CONTRIBUTOR");
		if (null == contributorDao)
			contributorDao = this.getDao(Contributor.class);
		return contributorDao;
	}

	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
		Log.i(this.getClass().getName(), "BDD closed");
		participantDao = null;
		costDao = null;
		eventDao = null;
		contributorDao = null;
	}
}

