# RealmAndroidDemo
Realm Android Demo Project

This project explains how to initialize and use Realm database for Android platform.
Also it shows how to alter a existing table.

#Version 0
-----------

Your Model class for Book
public class Book extends RealmObject{

    @PrimaryKey
    private int id;
    private String title;
    private String author;
    
    ...getters and setters
}

Your application Class
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        //Realm Schema Version 0
        RealmConfiguration configuration = new RealmConfiguration.Builder().
                name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .build();
    }
}

Adding a New Book into database

Realm realm = Realm.getDefaultInstance();
realm.beginTransaction();
realm.insert(book);
realm.commitTransaction();

Retrieve all the books from the database

public RealmResults<Book> getBooks(){
        return realm.where(Book.class).findAll();
}

#Version 1
------------
Now we are going to add new fiedl "cost" to the Book class. Then we need to migrate the database

public class Book extends RealmObject{

    @PrimaryKey
    private int id;
    private String title;
    private String author;
    
    //newly added field
    private int cost;
    
        ...getters and setters
    
}

Then need to implement migration class

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

Then do the necesary changes in Application java file

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        //Realm Schema Version 1 (adding new field cost)
        RealmConfiguration configuration = new RealmConfiguration.Builder().
                name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(1)
                .migration(new MyMigration())
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
