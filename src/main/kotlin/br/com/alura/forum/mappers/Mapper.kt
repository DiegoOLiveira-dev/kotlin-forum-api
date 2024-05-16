package br.com.alura.forum.mappers

interface Mapper<T, U> {
    fun map(t: T): U
}
