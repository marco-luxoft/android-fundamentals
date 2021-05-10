package com.luxoft.films.db

import androidx.room.*

@Dao
interface PersonDao {
    @Query("SELECT * FROM person_entity")
    suspend fun getAll(): List<PersonEntity>

    @Query("SELECT * FROM person_entity WHERE name LIKE :name")
    suspend fun findByName(name: String): PersonEntity

    @Insert
    suspend fun insertAll(vararg people: PersonEntity)

    @Delete
    suspend fun delete(person: PersonEntity)

    @Update
    suspend fun updatePeople(vararg people: PersonEntity)

    /*
        @Transaction
        open suspend fun setPeopleTransaction(person: Person) {
            delete(person)
            insertAll(person)
        }
    */
}