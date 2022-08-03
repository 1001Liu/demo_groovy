package com.liu.groovy

// 这是单行注释
/**
 * 这是多行注释
 */

def s1 = '这是单引号测试'
println(s1)

def name = 'liuxin'
def s2 = "$name 这是双引号测试"
println(s2)


def s3 = '''这是三引号测试
a
b
c
c
'''
println(s3)

def a = 1 //定义一个整形

def b = "字符串" //定义一个字符串

def double c = 1.0  //定义一个 double 类型，也可以指定变量类型，当指定变量类型def可以省略

// 无参函数
def fun1() {

}

// 有参函数
def fun2(def1, def2) {

}
//返回值
String fun3() {
    "返回值"
}
println fun3()