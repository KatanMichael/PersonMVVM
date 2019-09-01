package com.michael.personmvvm.interfaces

interface RequestListener
{
    fun<T> onComplete(t: T)
}