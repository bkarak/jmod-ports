package org.jmod;

import org.jmod.dsl.sql.SQLConfiguration;


public class SimpleSQLConfiguration extends SQLConfiguration {
    protected boolean SQLMOD_NS_AWARE = true;
    protected String SQLMOD_NS_URI = "jmod-ns-aware/schema.sql";
}