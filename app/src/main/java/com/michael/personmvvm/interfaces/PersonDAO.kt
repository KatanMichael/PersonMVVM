package com.michael.personmvvm.interfaces

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
    fun getAllPeople
}