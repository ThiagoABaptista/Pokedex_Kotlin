package com.example.pokedex_kotlin.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokedex_kotlin.model.entities.Pokemon
import androidx.room.Room
import androidx.room.TypeConverters

@Database(entities = [Pokemon::class], version = 1)
@TypeConverters(Converters::class)
abstract class PokemonRoomDatabase : RoomDatabase(){
    companion object{
        @Volatile
        private var INSTANCE: PokemonRoomDatabase? = null
        fun getDatabase(context: Context): PokemonRoomDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PokemonRoomDatabase::class.java,
                    "pokemon_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance

                instance
            }
        }

    }
}