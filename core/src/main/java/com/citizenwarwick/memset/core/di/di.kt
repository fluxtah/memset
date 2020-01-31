package com.citizenwarwick.memset.core.di

import android.content.Context
import com.citizenwarwick.memset.core.MemoryCardRepository
import com.citizenwarwick.memset.core.model.adapters.ColorAdapter
import com.citizenwarwick.memset.core.model.adapters.MemoryCardElementAdapter
import com.citizenwarwick.memset.core.model.adapters.MemoryCardElementModelListAdapter
import com.citizenwarwick.memset.data.MemsetDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

/**
 * Poor mans DI
 */
fun bootstrap(context: Context) {
    dependencies = mutableMapOf()

    Moshi::class single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(ColorAdapter())
            .add(MemoryCardElementAdapter())
            .add(MemoryCardElementModelListAdapter())
            .build()
    }

    MemsetDatabase::class single { MemsetDatabase.create(context.applicationContext) }
    MemoryCardRepository::class single { MemoryCardRepository() }
}

lateinit var dependencies: MutableMap<KClass<*>, () -> Any>

inline infix fun <reified T : Any> KClass<*>.by(noinline factory: () -> T) {
    dependencies[this] = factory
}

inline infix fun <reified T : Any> KClass<*>.single(noinline factory: () -> T) {
    val single = factory()
    by { single }
}

inline fun <reified T> get(): T = dependencies[T::class]!!.invoke() as T

inline fun <reified T> inject(): Injector<T> =
    Injector(dependencies[T::class])

class Injector<T>(private val factory: (() -> Any)?) : ReadOnlyProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return factory!!.invoke() as T
    }
}
