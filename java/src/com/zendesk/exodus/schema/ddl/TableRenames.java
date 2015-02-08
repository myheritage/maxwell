package com.zendesk.exodus.schema.ddl;

import java.util.ArrayList;

import com.zendesk.exodus.schema.Schema;

public class TableRenames extends SchemaChange {
	private final ArrayList<TableAlter> alters;

	public TableRenames() {
		this.alters = new ArrayList<TableAlter>();
	}

	public void addAlter(String oldDatabase, String oldTableName, String newDatabase, String newTableName) {
		TableAlter a = new TableAlter(oldDatabase, oldTableName);
		a.newDatabase = newDatabase;
		a.newTableName = newTableName;
		alters.add(a);
	}

	@Override
	Schema apply(Schema schema) throws SchemaSyncError {
		for ( TableAlter a : alters ) {
			schema = a.apply(schema);
		}
		return schema;
	}

}
