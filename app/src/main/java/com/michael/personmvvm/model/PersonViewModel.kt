package com.michael.personmvvm.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.michael.personmvvm.db.PersonRepository

class PersonViewModel(application: Application) : AndroidViewModel(application)
{
    private var repository : PersonRepository = PersonRepository(application)

    private var allPeople : LiveData<List<Person>> = repository.getallPeople()

    fun insertPerson(person: Person)
    {
        repository.insertNewPerson(person)
    }

    fun deleteAllPeople()
    {
        repository.deleteAllPeople()
    }

    fun getAllPeople(): LiveData<List<Person>>
    {
        return allPeople
    }

    fun getAllPeopleAboveAge(age: Int): List<Person>?
    {
        return repository.getAllPeopleAboveAge(age)
    }
}