package com.liu.groovy


// 第一种
/*for (def i = 0; i < 5 ; i++) {
    println("第${i}次循环")
}*/

//第二种
for (i in 0..5){
    println("第${i}次循环")
}

4.times {
    println it
}


def name = ''
def result = name?: "abc"

println(result)

try {
    println 5 / 0
} catch (anything) {
    println(anything)
}

def s1 = "5"
//String 转成 int
def s2 = s1 as int

//String 转成 double
def s3 = s2 as double

