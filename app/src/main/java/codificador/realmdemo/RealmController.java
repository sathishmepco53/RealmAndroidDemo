package codificador.realmdemo;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Sathish on 7/30/2017.
 */

public class RealmController {

    Realm realm;

    public RealmController(){
        realm = Realm.getDefaultInstance();
    }

    public void refresh(){
        realm.refresh();
    }

    public void addBook(Book book){
        realm.beginTransaction();
        realm.insert(book);
        realm.commitTransaction();
    }

    public RealmResults<Book> getBooks(){
        return realm.where(Book.class).findAll();
    }

    public Book getBook(String id){
        return realm.where(Book.class).equalTo("id",id).findFirst();
    }

}
