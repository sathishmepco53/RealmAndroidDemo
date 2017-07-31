package codificador.realmdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import io.realm.RealmResults;

/**
 * Created by Sathish on 7/30/2017.
 */

public class BookAdapter extends BaseAdapter {

    RealmResults<Book> bookList;
    Context context;
    LayoutInflater layoutInflater;

    public BookAdapter(Context context){
        this.context =context;
        layoutInflater = LayoutInflater.from(context);

    }

    public void setList(RealmResults<Book> list){
        bookList = list;
    }

    @Override
    public int getCount() {
        if(bookList == null)
            return 0;
        return bookList.size();
    }

    @Override
    public Book getItem(int i) {
        return bookList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            view = layoutInflater.inflate(R.layout.book_list_row,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.textViewTitle = view.findViewById(R.id.textViewTitle);
            viewHolder.textViewAuthor = view.findViewById(R.id.textViewAuthor);
            viewHolder.textViewCost = view.findViewById(R.id.textViewCost);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Book book = bookList.get(i);
        System.out.println(book.toString());
        viewHolder.textViewTitle.setText(book.getTitle());
        viewHolder.textViewAuthor.setText(book.getAuthor());
        viewHolder.textViewCost.setText("Rs "+book.getCost()+"");
        return view;
    }

    class ViewHolder{
        TextView textViewTitle, textViewAuthor, textViewCost;
        public ViewHolder(){

        }
    }
}
