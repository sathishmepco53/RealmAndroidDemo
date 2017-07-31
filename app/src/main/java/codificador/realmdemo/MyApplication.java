package codificador.realmdemo;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Sathish on 7/30/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        //Realm Schema Version 0
        /*RealmConfiguration configuration = new RealmConfiguration.Builder().
                name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .build();*/

        //Realm Schema Version 1 (adding new field cost)
        RealmConfiguration configuration = new RealmConfiguration.Builder().
                name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(1)
                .migration(new MyMigration())
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
