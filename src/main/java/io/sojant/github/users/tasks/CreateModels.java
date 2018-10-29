package io.sojant.github.users.tasks;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import io.sojant.github.users.model.Users;
import io.sojant.github.users.utils.Utils;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;


/**
 * Created by Sojant on 2018-10-25.
 */
public class CreateModels {

    public static void main(String[] args) {

        Map<String, String> settings = new HashMap<>();

        settings.put("connection.driver_class", Utils.property("sugar_bowl_driver_class_name"));
        settings.put("dialect", Utils.property("sugar_bowl_dialect"));
        settings.put("hibernate.connection.url", Utils.property("sugar_bowl_jdbc_url"));
        settings.put("hibernate.connection.username", Utils.property("sugar_bowl_db_user"));
        settings.put("hibernate.connection.password", Utils.property("sugar_bowl_db_password"));
        settings.put("hibernate.hbm2ddl.auto", "create");
        settings.put("show_sql", "true");

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(settings)
                .build();
        MetadataSources metadata = new MetadataSources(registry);

        metadata.addAnnotatedClass(Users.class);
        SchemaExport schemaExport = new SchemaExport();

        schemaExport.setHaltOnError(true);
        schemaExport.setFormat(true);
        schemaExport.setDelimiter(";");
        schemaExport.setOutputFile("db-schema.sql");
        schemaExport.execute( EnumSet.of(
                TargetType.DATABASE,
                TargetType.SCRIPT,
                TargetType.STDOUT) , SchemaExport.Action.BOTH, metadata.buildMetadata());

        ((StandardServiceRegistryImpl) registry).destroy();

    }





}
