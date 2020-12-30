package com.sandystudios.weatherapp.mapper

interface BaseMapper<E, D> {

    fun transformToDomain(type: E): D

    fun transformToDto(type: D): E // Data transfer object
}