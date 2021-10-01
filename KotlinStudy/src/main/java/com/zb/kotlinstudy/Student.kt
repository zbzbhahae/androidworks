package com.zb.kotlinstudy

class Student(_name : String , _age:Int) {
    var name = _name
    var age = _age


    constructor():this("zb", 29)

    override fun toString(): String {
        return "Student(name='$name', age=$age)"
    }


}