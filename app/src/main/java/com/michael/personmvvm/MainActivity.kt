package com.michael.personmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.michael.personmvvm.interfaces.RequestListener
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

        personViewModel!!.getLastPerson()?.observe(this, Observer<String>
        {
            lastPersonNameTv.text = it.toString()
        })

        enterPersonBtn.setOnClickListener {
            val name = enterPersonNameET.text.toString()
            val age =
                try {
                    enterPersonAgeET.text.toString().toInt()
                }catch (e: NumberFormatException)
                {
                    0
                }



            val tempPerson = Person(name,age)

            personViewModel!!.insertPerson(tempPerson)
            personViewModel!!.setLastPerson(tempPerson.name)
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

        searchByAgeBtn.setOnClickListener{

            var age  = searchByAgeEt.text.toString().toInt()
            val allPeopleAboveAge = personViewModel?.getAllPeopleAboveAge(age,
                object: RequestListener{
                    override fun <T> onComplete(t: T)
                    {
                        val allPeople = t as List<Person>

                        if(allPeople != null)
                        {
                           for(p in allPeople)
                           {
                               Log.d("PersonList",p.toString())
                           }
                        }else
                        {
                            Log.d("PersonList","allPeople is null")
                        }
                    }


                })



        }

    }
}
