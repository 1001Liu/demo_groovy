package com.liu.groovy

import groovy.transform.stc.ClosureParams
import groovy.transform.stc.FirstParam
import groovy.transform.stc.MapEntryOrKeyValue


def test1 = { println "这是一个闭包" }
test1.call()


def test2 = { param -> println "${param} :这是一个闭包" }
test2.call("liuxin")


def test3 = { println "${it} :这是一个闭包" }
test3.call("张三")


def str1 = "Hello"
def clos = { param -> println "${str1} ${param}" }
clos.call("World")

str1 = "Welcome"
clos.call("World")

def static display(clo) {
    clo.call("方法参数")
}

display(clos)


def lst = [11, 12, 13, 14]
lst.each { println it }
/**
 public static <T> List<T> each(List<T> self, @ClosureParams(FirstParam.FirstGenericType.class) Closure closure) {
 return (List)each((Iterable)self, closure);
 }*/

/**
 * public static <K, V> Map<K, V> each(Map<K, V> self, @ClosureParams(MapEntryOrKeyValue.class) Closure<?> closure) {
 Iterator var2 = self.entrySet().iterator();

 while(var2.hasNext()) {
 Map.Entry<K, V> entry = (Map.Entry)var2.next();
 callClosureForMapEntry(closure, entry);
 }

 return self;
 }
 */
def mp = [key1: "value1", key2: "value2", key3: "value3"]
mp.each { println it }
mp.each { println "${it.key} maps to: ${it.value}" }

println("find:${lst.find { it > 12 }}")
println("findAll:${lst.findAll { it > 12 }}")
println("any:${lst.any { it > 12 }}")
println("every:${lst.every { it > 12 }}")
println("collect:${lst.collect { it + 10 }}")


def str = "这是一个测试字符串"
str.each {println(it)}
str = str.replace("一个","两个")
println str