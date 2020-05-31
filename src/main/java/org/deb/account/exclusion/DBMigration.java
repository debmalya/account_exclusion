package org.deb.account.exclusion;

import org.flywaydb.core.Flyway;

public class DBMigration {
  public static void main(String[] args) {
    // Create the Flyway instance and point it to the database
    Flyway flyway = Flyway.configure().dataSource("jdbc:h2:file:./target/accounts", "sa", null).load();

    // Start the migration
    flyway.migrate();
  }
}
