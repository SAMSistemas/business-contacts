package com.jonisaa.sugarsample.activity;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jonisaa.sugarsample.adapter.PersonAdapter;
import com.jonisaa.sugarsample.R;
import com.jonisaa.sugarsample.activity.base.BaseAppCompatActivity;
import com.jonisaa.sugarsample.component.ControllerComponent;
import com.jonisaa.sugarsample.component.DaggerControllerComponent;
import com.jonisaa.sugarsample.controller.base.Controller;
import com.jonisaa.sugarsample.model.Person;

import java.util.List;

import butterknife.Bind;

/**
 * @author jonatan.salas
 */
public class MainActivity extends BaseAppCompatActivity {

    @Bind(R.id.listView)
    ListView mListView;

    private PersonAdapter mPersonAdapter;
    private Controller<Person> mPersonController;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void setupUserInterface() {
        mListView.setDividerHeight(1);
        mListView.setEnabled(true);
        mListView.setClickable(true);
    }

    @Override
    public void setListeners() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Person person = (Person) parent.getAdapter().getItem(position);
                Snackbar.make(view, person.toString(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initialize() {
        final ControllerComponent component = DaggerControllerComponent.builder().build();
        mPersonController = component.providePersonController();

        insertInBackground();
        final List<Person> persons = mPersonController.listAll();
        mPersonAdapter = new PersonAdapter(getApplicationContext(), persons);
    }

    @Override
    public void populateViews() {
        mListView.setAdapter(mPersonAdapter);
        mPersonAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteListeners() {
        mPersonController.deleteAll();
    }

    private void insertInBackground(){
        final Thread personThread = new Thread(new Runnable() {
            @Override
            public void run() {
                mPersonController.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
                mPersonController.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
                mPersonController.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
                mPersonController.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
                mPersonController.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
                mPersonController.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
                mPersonController.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
                mPersonController.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
                mPersonController.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
                mPersonController.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
                mPersonController.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
                mPersonController.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
                mPersonController.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
                mPersonController.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
                mPersonController.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
                mPersonController.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
                mPersonController.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
                mPersonController.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
                mPersonController.insert(new Person("Mariano", "Loisotto", "23", "Florencio Varela"));
            }
        });

        personThread.start();
    }
}
