package com.michael.personmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.michael.personmvvm.model.Person
import com.michael.personmvvm.model.PersonViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import kotlin.math.log

class MainActivity : AppCompatActivity()
{

    var personViewModel : PersonViewModel? = null

    var listOfPeople = ArrayList<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        personViewModel = ViewModelProviders.of(this)
            .get(PersonViewModel::class.java)


        personViewModel!!.getAllPeople().observe(this, Observer<List<Person>>
        {

            listOfPeople.clear()
            for(p in it)
            {
                listOfPeople.add(p)
            }
        })

        enterPersonBtn.setOnClickListener {
            val name = enterPersonNameET.text.toString()
            val age = enterPersonAgeET.text.toString().toInt()

            val tempPerson = Person(name,age)

            personViewModel!!.insertPerson(tempPerson)

            enterPersonNameET.setText("")
            enterPersonAgeET.setText("")
        }

        logAllPoepleBtn.setOnClickListener{

            for(p in listOfPeople)
            {
                Log.d("PersonList",p.toString())
            }

            removeAllPeopleBtn.setOnClickListener{

                personViewModel!!.deleteAllPeople()
            }



        }
    }
}
