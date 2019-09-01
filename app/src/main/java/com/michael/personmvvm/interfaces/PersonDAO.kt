package com.michael.personmvvm.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.michael.personmvvm.model.Person

@Dao
interface PersonDAO
{

    @Insert
    fun insertPerson(person: Person)

    @Query("DELETE FROM person_table")
    fun deleteAllPeople()

    @Query("SELECT * FROM person_table")
    fun getAllPeople() : LiveData<List<Person>>

    @Query("SELECT * FROM person_table WHERE age > :inputAge")
    fun getAllPeopleAboveAge(inputAge: Int) : List<Person>

}