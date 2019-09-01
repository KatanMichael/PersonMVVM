package com.michael.personmvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_table")
data class Person(var name: String,
                  var age: Int,
                  @PrimaryKey(autoGenerate = true) var id: Int = 0)
{


}