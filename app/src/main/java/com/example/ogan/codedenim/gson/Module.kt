package com.example.ogan.codedenim.gson

data class Module (

    val ModuleId: Int?,
    val CourseId: Int?,
    val ModuleName: String?,
    val ModuleDescription: String?,
    val ExpectedTime: Int?,
    val Course: Course?,
    val Topics: List<Topic>?
)
