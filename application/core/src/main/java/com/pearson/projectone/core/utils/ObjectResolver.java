package com.pearson.projectone.core.utils;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by uphilji on 4/18/17.
 */
public class ObjectResolver implements ObjectIdResolver {
	/** The items. */
	private Map<ObjectIdGenerator.IdKey, Object> items;

	/**
	 * Instantiates a new persist object id resolver.
	 */
	public ObjectResolver() {
	}

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.annotation.ObjectIdResolver#bindItem(com.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey, java.lang.Object)
	 */
	@Override
	public void bindItem(final ObjectIdGenerator.IdKey id, final Object ob) {
		if (items == null) {
			items = new HashMap<ObjectIdGenerator.IdKey, Object>();
		}
		items.put(id, ob);
	}

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.annotation.ObjectIdResolver#resolveId(com.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey)
	 */
	@Override
	public Object resolveId(final ObjectIdGenerator.IdKey id) {
		return (items == null) ? null : items.get(id);
	}

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.annotation.ObjectIdResolver#canUseFor(com.fasterxml.jackson.annotation.ObjectIdResolver)
	 */
	@Override
	public boolean canUseFor(final ObjectIdResolver resolverType) {
		return resolverType.getClass() == getClass();
	}

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.annotation.ObjectIdResolver#newForDeserialization(java.lang.Object)
	 */
	@Override
	public ObjectIdResolver newForDeserialization(final Object context) {
		// 19-Dec-2014, tatu: Important: must re-create without existing
		// mapping; otherwise bindings leak
		// (and worse, cause unnecessary memory retention)
		return new ObjectResolver();
	}

	/**
	 * Gets the items.
	 *
	 * @return the items
	 */
	public Map<ObjectIdGenerator.IdKey, Object> getItems() {
		return items;
	}

	/**
	 * Sets the items.
	 *
	 * @param items the items
	 */
	public void setItems(final Map<ObjectIdGenerator.IdKey, Object> items) {
		this.items = items;
	}
}
