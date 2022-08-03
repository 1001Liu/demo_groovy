package com.liu.groovy



def range = 1..5

println(range)
//[1, 2, 3, 4, 5]

range.size()  //长度

range.iterator() //迭代器

println "获取标号为1的元素:${range.get(1) }" //获取标号为1的元素

println range.contains( 5) //是否包含元素5
println("第一个数据: "+range.from) //第一个数据
//第一个数据: 1

println("最后一个数据: "+range.to)   //最后一个数据
//最后一个数据: 5

println range.last() //最后一个元素

println range.first() //di一个元素


