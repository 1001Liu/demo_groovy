package com.liu.groovy


//变量定义：List变量由[]定义，其元素可以是任何对象
def aList = [5,'string',false]


println(aList)

println aList[0]  //获取第1个数据

println aList[1]  //获取第2个数据

println aList[2]  //获取第3个数据

println aList[3]  //获取第4个数据

println( "集合长度：" + aList.size())

//赋值

aList[5] = 100  //给第10个值赋值

aList<<10 //在 aList 里面添加数据

aList.add(11)
println aList

println "集合长度：" + aList.size()
