package codificador.realmdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import io.realm.Realm;

public class NewBookActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextTitle, editTextAuthor, editTextCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);

        editTextAuthor = (EditText) findViewById(R.id.editTextAuthor);
        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        editTextCost = (EditText) findViewById(R.id.editTextCost);
        findViewById(R.id.buttonSave).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String title = editTextTitle.getText().toString();
        String author = editTextAuthor.getText().toString();
        String cost = editTextCost.getText().toString();

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setCost(Integer.parseInt(cost));
        book.setId(new RealmController().getBooks().size() + 1);
        new RealmController().addBook(book);
        finish();
    }
}
