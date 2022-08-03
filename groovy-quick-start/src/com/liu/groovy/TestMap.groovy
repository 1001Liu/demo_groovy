package com.liu.groovy

def map = [key1: "value1", key2: "value2", key3: "value3"]

println map

println("数据长度：" + map.size())
println(map.keySet())
println(map.values())
println("key1的值：" + map.key1)
println("key1的值：" + map.get("key1"))


//赋值
map.put("key4", "value4")

Iterator it = map.iterator()
while (it.hasNext()) {
    println "遍历map: " + it.next()
}


map.containsKey("key1") //判断map是否包含某个key

map.containsValue("values1")  //判断map是否包含某个values


Set set = map.keySet() //把 map 的key值转换为 set

println set

map.clear()  //清除map里面的内容

println map