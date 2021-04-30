package com.luxoft.films.dto

 class Pet {
     var name : String? = null
     get() {
         print(field)
         return field
     }

     set(value) {
         print(value)
         field = value
     }
 }