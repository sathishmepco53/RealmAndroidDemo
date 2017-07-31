package codificador.realmdemo;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * Created by Sathish on 7/31/2017.
 */

public class MyMigration implements RealmMigration {

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema realmSchema = realm.getSchema();
        if(oldVersion == 0){
            //adding new column Cost
            RealmObjectSchema bookSchema = realmSchema.get("Book");
            bookSchema.addField("cost",int.class, FieldAttribute.REQUIRED);
            bookSchema.transform(new RealmObjectSchema.Function() {
                @Override
                public void apply(DynamicRealmObject obj) {
                    //initializing cost by 0 for existing rows
                    obj.setInt("cost",0);
                }
            });
        }
    }
}
