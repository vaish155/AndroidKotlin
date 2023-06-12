package com.example.mvvmquotes.data

class FakeDatabase private constructor() {

    // All the DAOs go here!
    var quoteDao = FakeQuoteDao()
        private set

    companion object{
        // @Volatile - Writes to this property are immediately visible to other threads
        @Volatile private var instance : FakeDatabase? = null

        fun getInstance() = // singleton
            // Already instantiated? - return the instance
            // Otherwise instantiate in a thread-safe manner
            instance ?: synchronized(this){
            // If it's still not instantiated, finally create an object
            // also set the "instance" property to be the currently created one
            instance ?: FakeDatabase().also { instance = it }
        }
    }
}