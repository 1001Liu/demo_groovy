package com.liu.groovy

trait C {
    void displayC() {
        println("Display C")
    }
}

trait A extends C{
    String name

    void display() {
        this.name = "A"
        println("Display A")
    }
}

class B implements A, C {

}

B b = new B()
b.display()
b.displayC()
println b.name