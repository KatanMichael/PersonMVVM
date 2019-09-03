package com.michael.personmvvm.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.michael.personmvvm.db.PersonRepository
import com.michael.personmvvm.interfaces.RequestListener

class PersonViewModel(application: Application) : AndroidViewModel(application)
{
    private var repository : PersonRepository = PersonRepository(application)

    private var allPeople : LiveData<List<Person>> = repository.getallPeople()

    private var lastPerson : MutableLiveData<String>? = MutableLiveData<String> ()

    public fun getLastPerson() : LiveData<String>?
    {
        return lastPerson
    }

    public fun setLastPerson(name: String)
    {
        lastPerson!!.value = name
    }

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

    fun getAllPeopleAboveAge(age: Int, requestListener: RequestListener)
    {
        return repository.getAllPeopleAboveAge(age, object : RequestListener{
            override fun <T> onComplete(t: T)
            {
                requestListener.onComplete(t)
            }


        })
    }
}