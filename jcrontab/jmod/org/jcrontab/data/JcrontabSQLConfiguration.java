package org.jcrontab.data;

import org.jmod.dsl.sql.SQLConfiguration;

public class JcrontabSQLConfiguration extends SQLConfiguration {
    public boolean SQLMOD_NS_AWARE = true;
    public String SQLMOD_NS_URI = "schema.sql";
}
