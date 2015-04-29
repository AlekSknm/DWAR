package com.miage.fralml;

import com.miage.fralml.PMF;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JDOCursorHelper;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

@Api(name = "infoendpoint", namespace = @ApiNamespace(ownerDomain = "miage.com", ownerName = "miage.com", packagePath = "fralml"))
public class InfoEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listInfo")
	public CollectionResponse<Info> listInfo(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		PersistenceManager mgr = null;
		Cursor cursor = null;
		List<Info> execute = null;

		try {
			mgr = getPersistenceManager();
			Query query = mgr.newQuery(Info.class);
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				HashMap<String, Object> extensionMap = new HashMap<String, Object>();
				extensionMap.put(JDOCursorHelper.CURSOR_EXTENSION, cursor);
				query.setExtensions(extensionMap);
			}

			if (limit != null) {
				query.setRange(0, limit);
			}

			execute = (List<Info>) query.execute();
			cursor = JDOCursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Info obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Info> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getInfo")
	public Info getInfo(@Named("id") Long id) {
		PersistenceManager mgr = getPersistenceManager();
		Info info = null;
		try {
			info = mgr.getObjectById(Info.class, id);
		} finally {
			mgr.close();
		}
		return info;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param info the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertInfo")
	public Info insertInfo(Info info) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			if (info.getId() != null) {
				if(containsInfo(info)) {
					throw new EntityExistsException("Object already exists");
				}
			}
			mgr.makePersistent(info);
		} finally {
			mgr.close();
		}
		return info;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param info the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateInfo")
	public Info updateInfo(Info info) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			if (!containsInfo(info)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.makePersistent(info);
		} finally {
			mgr.close();
		}
		return info;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeInfo")
	public void removeInfo(@Named("id") Long id) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			Info info = mgr.getObjectById(Info.class, id);
			mgr.deletePersistent(info);
		} finally {
			mgr.close();
		}
	}

	private boolean containsInfo(Info info) {
		PersistenceManager mgr = getPersistenceManager();
		boolean contains = true;
		try {
			mgr.getObjectById(Info.class, info.getId());
		} catch (javax.jdo.JDOObjectNotFoundException ex) {
			contains = false;
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static PersistenceManager getPersistenceManager() {
		return PMF.get().getPersistenceManager();
	}

}
