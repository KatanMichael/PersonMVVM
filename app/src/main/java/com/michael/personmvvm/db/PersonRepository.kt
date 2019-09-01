package com.michael.personmvvm.db

import android.app.Application
import androidx.lifecycle.LiveData
import com.michael.personmvvm.interfaces.PersonDAO
import com.michael.personmvvm.model.Person
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PersonRepository(application: Application)
{
    private var personDao: PersonDAO

    private var allPeople: LiveData<List<Person>>

    init {
        val database : PersonDatabase = PersonDatabase
            .getInstance(application.applicationContext)!!

        personDao = database.personDao()

        allPeople = personDao.getAllPeople()
    }


    fun insertNewPerson(person : Person)
    {
       GlobalScope.launch {
           personDao.insertPerson(person)
       }
    }

    fun deleteAllPeople()
    {
        GlobalScope.launch {
            personDao.deleteAllPeople()
        }
    }


    fun getallPeople() : LiveData<List<Person>>
    {
        return allPeople;
    }

    fun getAllPeopleAboveAge(age: Int) : List<Person>?
    {
        return personDao.getAllPeopleAboveAge(age)


    }
}