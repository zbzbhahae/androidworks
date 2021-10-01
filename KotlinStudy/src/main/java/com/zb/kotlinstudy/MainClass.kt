package com.zb.kotlinstudy

import java.io.File
import java.lang.IllegalArgumentException
import kotlin.math.roundToInt


const val VERSION_CODE = 5
fun main() {
    testMeiju()
    //TODO

}

fun p(any: Any) {
    println(any)
}
fun p(any: Int) {
    println(any)
}
fun p(any: String) {
    println(any)
}

//判断表达式 else if 形式都建议when
fun panduan() {
    val age = 16;
    if(age in 0..12) {
        println("小学生")
    } else if (age in 13..15) {
        println("初中生")
    } else if (age in 16..18) {
        println("高中生")
    } else {
        println("不是人")
    }

    val school = "小学"
    val level : Any = when(school) {
        "小学" -> "真小"
        "中学" -> "不小"
        "大学" -> "挺大"
        else -> {
            println("未知")
        }
    }
    println(level)
}

//字符串模板
fun muban() {
    val name = "张博"
    val age = 29
    val birthday = "1992.9.1"
    println("$name $age $birthday")
    println("这年龄 ${if(age < 25) "真小" else "真大"}")
}

//函数
private fun funName(a:Int, b:String) : String {
    return "age is $a and name is $b"
}

private fun funNameWithDefault(a:Int=29, b:String="张博") : String {
//    println(funNameWithDefault(b = "张三"))  //传递时可以指定给哪个参数传递值
    return "age is $a and name is $b"
}

fun noReturnFun() {
//    p(noReturnFun())  //没有返回值类型 Unit
}

//fun noReturnFunNothing() : Nothing {
//}

//反引号作用，在java和kotlin互相操作时 避免函数名(与关键字等)冲突
fun `**~fgdf**`() {
}

//匿名函数
fun niminghanshu() {
    val str = "fgdsghfudishgdfklshk"
    var a = str.count{letter ->
        letter == 's'
    }
    p(a)
}

//匿名函数变量
fun niminghanshubianliang() {
    val aFunVar : ()->String
    aFunVar = {
        val str = "aaaa"
        "$str"
    }
    val result = aFunVar()
    p(result)
}

fun niminghanshubianliang2() {
    val aFunVar : (Int, String)->String
    aFunVar = { age,name->
        val str = "aaaa"
        "$str  $name  $age"
    }
    val result = aFunVar(29, "张博")
    p(result)
}
//当匿名函数类型参数只有一个时 可以用it指代 不用写出来
fun niminghanshuIT() {
    val aFunVal : (Int)->String = {
        "$it "
    }
}

//匿名函数返回值可以类型推断
fun niminghanshuLeixingtuiduan() {
    val aFunVal = {
        "  匿名推断返回值 "
    }
    p(aFunVal())
}

//匿名函数返回值可以类型推断
fun  niminghanshuLeixingtuiduanYoucanshu() {
    val aFunVal = { age:Int, name:String ->
        "  匿名推断返回值 有参数版本 $age  $name"
    }
    p(aFunVal(29, "张博"))
}

//匿名函数作为参数 打印函数结果时需要｛｝包裹起来
//调用时 如果方法最后一个参数是函数类型 可以写成下面的形式来调用
/*niminghanshuZuocanshu("张博") {
    "   29"
})*/
//inline 内联 函数 可以减少lamda内存开销 但是会造成编码体积变大
inline fun niminghanshuZuocanshu(name:String , aFun : ()->String) {
    p(" name:$name, age:${aFun()}")
}

//将函数当参数传递
fun funAsVal() : String {
    return "29 "
}
//调用上面方法时 ::表示参数是个具体名称函数
fun useFunAsVal() {
    niminghanshuZuocanshu("张博", ::funAsVal)
}

//函数类型作为返回值 调用时p(funAsReturn()("world"))
//匿名函数能修改并引用定义在自己作用域之外的变量，
// 匿名函数引用着定义自身的函数里的变量
fun funAsReturn() : (String)->String {
    val info = " the infomation is null"
    return {"hello str is $it  $info"}
}

//安全调用操作符 ?表示可空 如果空 其方法不会调用
//可以链式调用  xxx?.xxx()?.xxxx()
fun safeUse() {
    var a:String? = null

    print(a?.capitalize() + "ddd    ")

    //!!.  非空断言 如果是空 也会去调用方法 然后抛出异常
    //如不为空 则正常运行
//    a!!.capitalize()
    a = a?.let {
        p("123")
        //非空白字符串
        if(it.isNotBlank()) {
            it.capitalize()
        } else {
            "default"
        }
    }
    println(a)

    //空合并操作符 varxxx ? : "default"
    //如果左边是空 就用右边的值
    //配合let一起使用来代替if / else
    //报错了。。kotlin语法调整了估计
//    val b = "default"
//    a = a?.let { it.capitalize() } ? : "default"

    if(null == a) {
        throw UnknowException()
    }

}

//自定义异常
class UnknowException() : IllegalArgumentException("操作不当")

//系统自带异常 抛出异常 信息为匿名方法返回值
fun checkVars() {
    val a : String ?
    a = null
    checkNotNull(a, {
        p("为什么会是异常呢")
        "a is null"
    })
}

const val MESSAGE = "hello,world"
fun useSubString() {
    var index = 5;
    var str = MESSAGE.substring(0..5)
    str = MESSAGE.substring(0 until index)
    p(str)
}

//拆分字符串 返回集合
fun useSplit() {
    var a = MESSAGE.split(",")

    //解构语法 一个表达式给多个变量同时赋值。。如果list中元素不够 会抛出index越界异常
    var (b, c) = MESSAGE.split(",")
    p(a)
    p(" $b  $c ")
}

//replace
fun useReplace() {
    val message = "This is a module written by kotlin"
    val result = message.replace(Regex("[abcde]")) {
        when(it.value) {
            "a"->"1"
            "b"->"2"
            "c"->"3"
            "d"->"4"
            "e"->"5"
            else->it.value
        }
    }
    p(result)
}
//!!!!!!!!!!!!!注意！！！！！！！！！！！！
//kotlin中 == 用来对比字符串匹配 ===用来对比字符串变量是否是同一引用
//更注意：：：
//kotlin存储字符串使用常量池 同样内容的字符串指向同一引用(复用)
//Int类似Integer  内部有一个默认设置为128大小的缓存池
//而且a:Int 表示基础类型int  而a:Int? 表示Integer是对象
fun matchStr() {
    val a = "info"
    val b = "info"
    val c = 128;
    val d = 128;
    var e:Int? = 129;
    var f:Int? = 129;

    p(a==b)
    p(a===b)
    p(c==d)
    p(c===d)
    p(e==f)
    p(e===f)
}

fun strForeach() {
    val message = "afgddasf"
    message.forEach {
        println("$it **")
    }
}

//数字的安全转换函数
fun numberSafeConvert() {
    val num = "1.1".toInt()//抛出异常 字符串转int
    val num1 = "1.1".toIntOrNull()//返回空
    "1.1".toDoubleOrNull()
    1.432432.toInt()//损失精度
    1.43253.roundToInt()//四舍五入
    var str = "%.2f".format(1.4324)//2位小数 后面四舍五入 返回字符串 1.43
}

//标准库函数
// apply能让每个配置函数都作用于接受者 省略变量名
fun useApply() {
    var file = File("E:/test.txt")?.apply {
        setReadable(true)
        setWritable(true)
        setExecutable(true)
    }
}

//let函数能使变量作用域lambda表达式，it关键字引用它
//let 会返回lambda的最后一样
fun useLet() {
    var fileLength = File("E:/test.txt")?.let {
        it.setReadable(true)
        it.totalSpace //返回最后一行 文件长度 而apply只能返回调用者自己
    // 但是apply能使用调用者自身方法进行配置
    }
}

//run函数  类似apply 但是返回lambda的执行结果
//apply和let的结合
fun useRun() {
    var flag = File("E:/test.txt")?.run {
        canRead()
    }

    var flag2 = "xxxx".run {
        ::testRun
        ::testRun
        ::testRun
        " "
    }
}

fun testRun() : Boolean {
    return  false;
}

//with 和run一样 不过对象传入方法不同 run是对象调用 with是传入对象
fun useWith() {
    var flag = with("abc", {
        length>=10
        " '"
    })
    p(flag)
}

//与apply类似 但是函数中不是对象上下文，而是对象的引用it 函数中返回对象本身
fun useAlso() {
    var a = "abc".also {
        it.length
        "  a"
    }
    p(a)  //打印出对象本身
}

//takeif  如果lambda表达式达成 则返回原对象 否则返回空
fun useTakeif() {
    var flag = "aaa".takeIf {
        it.length > 5
    }?.substring(0..1)
    println(flag?.toString())
}

//takeUnless  与takeif 条件判断相反
fun useTakeUnless() {
    var flag = "aaa".takeUnless {
        it.length > 5
    }?.substring(0..1)

    println(flag?.toString())
}

//List
fun useList() {
    val list = listOf(" A ", " B ", " C ")
    list[0]
//    p(list[4]) //索引越界
      //安全方法 越界会返回空  或者getOrElse 没有返回给定默认值
    println(list.getOrNull(5))
    list.listIterator().forEach {
        p(it)
    }
    list.forEach {
        p(it)
    }
}
//可变列表
fun useMutableList() {
    val list = mutableListOf("A", "B", "CCD")
    list.add("C")
    list.add("D")
    list.add("E")
    list.add("E")
    list.add("E")
    list.add("E")

    list.removeAt(0)

    list += "F"
    list -= "E" //移除一个
    list.removeIf {
        it == "CCD"
    }

    list.forEach {
        p(it)
    }

    val stableList = list.toList()

    for (s in list) {
        //遍历
    }

    list.forEachIndexed() { index, item->
        p("$index--->$item")
    }

    val (a, b, _, c) = list //给a b c赋值 跳过的地方用_代替
}

fun useSet() {
    val set = setOf("A", "B", "C", "C")

    p(set.elementAtOrElse(5, {"$it default"}))
    val mSet = set.toMutableSet()
    mSet.add("E")
    mSet.add("E")
    mSet.add("E")
    mSet.add("F")
    mSet += "D"

    mSet.forEach {
        p("   $it === E ?  ${it === "E"}    ")
    }
    //list转set转list 去重
    val originList = listOf("A", "B", "B", "C")
    var list = originList.toSet().toList();
    p(list)

    //快捷函数 去重  内部实现也是toMutableSet。toList
    list = originList.distinct();
    p(list)


}

fun iDontKnowSet() {
    /**
     * MuTableSet 与MutableList相似
     * 定义：MuTableSet<类型>或mutableSetOf(元素1，元素2，....,元素n)
     */

    val city1 = setOf("阜南","贵州","湖北","重庆")
    val city2 = setOf("重庆","四川","云南")
    val city3 = setOf("重庆","陕西","甘肃")

    //set换换成MuTableSet toMutableSet
    var  city = city1.union(city2).union(city3).toMutableSet()

    //往末尾添加元素
    city.add("北京")
    println(city)
    //添加另一个集合
    val newsStations = mutableListOf("西藏","湖南")
    city.addAll(newsStations)
    println(city)
    //移除一个元素
    city.remove("西藏")
    println(city)
    //移除另一个集合
    city.removeAll(newsStations)
    println(city)

    //输出：
    /*[阜南, 贵州, 湖北, 重庆, 四川, 云南, 陕西, 甘肃, 北京]
    [阜南, 贵州, 湖北, 重庆, 四川, 云南, 陕西, 甘肃, 北京, 西藏, 湖南]
    [阜南, 贵州, 湖北, 重庆, 四川, 云南, 陕西, 甘肃, 北京, 湖南]
    [阜南, 贵州, 湖北, 重庆, 四川, 云南, 陕西, 甘肃, 北京]*/
}

fun useMap() {
    val map1 = mapOf("A" to 1, "B" to 2)
    val map2 = mapOf(Pair("A", 1), Pair("B", 2))
    p(map1)
    map1.get("A")
    map1.getOrDefault("A", 0)
    map1.getOrElse("A", {0})
    map1.getValue("A")
    println(map1.get("A"))
    map1.forEach {
        p("key->${it.key}  value->${it.value}")
    }
    map1.forEach { (k, v) ->
        p("key->$k  value->$v")
    }

    val map = map1.toMutableMap()
    map += "C" to 3
    map -= "A"
    p(map)
    map.getOrPut("A"){1}
    p(map)
}

fun testClass() {
    var f = FirstBean("张博", 29 , "西安");
    f.name = "真张博"
    var f2 = FirstBean("张博")
    var f3 = FirstBean(13)
    println(f)
    println(f2)
    p(f3)
    var f4 = FirstBean("张博", city = "西安")
    p(f4)

}

fun testClassInit() {
    var a = ClassInitOrder("张博", 18)
    var a1 = ClassInitOrder("zb")
}

fun laterInitVar() {
    var a = FirstBean()
    a.info = "你今年会找到好工作，走向人生巅峰，家人也会平平安安。"
    a.showInfo()
    Thread.sleep(3000)
    a.showLazyInfo()
}

//类的继承 只有open关键字标注的类才能继承 方法重新同样，
// 只有父类中open的方法能重写
fun testExtend() {
    var a = Child()
    a.load()
    var b : ExtendClass = Child()
    b.load()
    a is ExtendClass //相当于a instanceOf ExtendClass
    //父类引用指向子类对象调用子类方法 强转 a as Child

    ((a as Child)).release()
    a.release()//智能类型转换 转换过一次 后面不用再转。。但是新版kotlin好像连第一次都不用转
    //所有类的超类是Any 而不是Object
    var any = Any()
    p(any) //Any在不用平台下有不同实现 其实现内容不在类中
}

//object修饰的类 只能创建一次 也就是单例
//类名也是创建出的唯一对象名字
fun testObject() {
    ObjectClass.showInfo()
    ObjectClass.showInfo()
    ObjectClass.showInfo()
}

/**
 * 和java的匿名内部类有点相似
 * 获得一个open的类的实例 修改其中方法
 *
 */
fun testObjectClass() {
    val a = object : FirstBean() {
        override fun toString(): String {
            return super.toString().plus("    from object create");
        }
    }
    p(a.toString())
}

/**
 * 如果你想将某个对象的初始化和一个雷的实例捆绑在一起，可以考虑使用伴生对象
 * companion作为修饰符   你可以在一个类里声明一个伴生对象，一个类里只能有一个半生对象
 * 只有调用类的伴生对象或者创建类的实例对象 伴生对象才会实例化 并且只有一份
 * 相当于java中的static方法、属性
 * **/
fun testObjectAny() {
    BanShengDuiXiang.showInfo()
}

/**
 * 如果一个类只对另外一个类有用，那么可以将这个类写在一起
 */
fun testQianTaoLei() {
    var a = QianTaoLei.Inner()
    a.showInfo()
    QianTaoLei.showInnerInfo()
}

/**
 * 数据类  data修饰 提供了自动生成的tostring
 * 重写了equals、hashcode和copy函数实现
 * **/
fun testDataClass() {
    var a = Coordinate(1, 1)
    var b = Coordinate(-1, -1)
    var c = Coordinate(1, 1)

    p(a==c)
    p(a.toString())
    var d = Coordinate(5)
    p(d)
    //copy的坑 不走次构造函数
    p(d.copy(5).toString())
}

/**让类支持解构语法**/
fun testJiegou() {
    var (a, b) = JieGouYuFa("zb", 29)
    //a就是name b就是age
    p(a)
    p(b)
}

/**让类支持运算符重载**/
fun testYunSuanFuChongZai() {
    var a = Coordinate(1,2,3)
    var b = Coordinate(3,2,1)
    var c = a + b
    p(c)
}

fun testMeiju() {
    Meiju.EAST is Meiju  //true  枚举类中的枚举就是枚举类的对象

    var controller = BoatController(Meiju.WEST)
    p(controller.getCurrentDirection())
}