package com.jonisaa.sugarsample.activity;

import android.widget.TextView;

import com.jonisaa.sugarsample.R;
import com.jonisaa.sugarsample.activity.base.BaseAppCompatActivity;
import com.jonisaa.sugarsample.controller.PersonController;
import com.jonisaa.sugarsample.model.Person;

import butterknife.Bind;

public class MainActivity extends BaseAppCompatActivity {

    @Bind(R.id.text_view)
    TextView mTextView;

    private PersonController controller = new PersonController();
    private Long id;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void setupUserInterface() {

    }

    @Override
    public void setListeners() {

    }

    @Override
    public void initialize() {
        Person person = new Person("Mariano", "Loisotto", "23", "Florencio Varela");
        id = controller.insert(person);
    }

    @Override
    public void populateViews() {
        Person person = controller.get(id);
        if (null != person) {
            final String personString = "Me llamo "
                    + person.getName() + " "
                    + person.getLastName() + ", y tengo "
                    + person.getAge() + " años. Vivo en la ciudad de "
                    + person.getLocation();

            mTextView.setText(personString);
        }
    }

    @Override
    public void deleteListeners() {
        controller.deleteAll();
    }
}
