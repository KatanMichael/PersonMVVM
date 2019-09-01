package com.michael.personmvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.michael.personmvvm.interfaces.PersonDAO
import com.michael.personmvvm.model.Person


@Database (entities = [Person::class], version = 1)

abstract class PersonDatabase : RoomDatabase()
{
    abstract fun personDao():PersonDAO

    companion object
    {
        private var instance : PersonDatabase? =  null

        fun getInstance(context: Context): PersonDatabase?
        {
            if(instance == null)
            {
                synchronized(PersonDatabase::class.java)
                {
                    instance = Room.databaseBuilder(
                        context,
                        PersonDatabase::class.java,
                        "person_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance

        }


    }

}