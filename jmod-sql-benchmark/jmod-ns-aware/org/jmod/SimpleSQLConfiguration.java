package org.jmod;

import org.jmod.dsl.sql.SQLConfiguration;


public class SimpleSQLConfiguration extends SQLConfiguration {
    public boolean SQLMOD_NS_AWARE = true;
    public String SQLMOD_NS_URI = "examples/jmod-ns-aware/schema.sql";
}