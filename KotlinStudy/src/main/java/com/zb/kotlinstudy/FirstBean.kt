package com.zb.kotlinstudy


/**
 * 初始化顺序
 * 主构造函数里声明的属性
 * 类级别的属性赋值
 * init初始化
 * 次构造函数里的属性赋值和函数调用
 */
open class FirstBean (
    //主构造函数
    name:String,
    age : Int = 18,//可以设置默认值
    var city : String

) {

    init {
        //输出话代码块  在构造函数执行之前执行 不是类加载时
        //每次构造时都会调用
        //执行顺序为init书写顺序
        p("我初始化了 在块1")
        //条件语句 不满足会抛出异常
        //条件参数需要满足 否则异常
//        require(name.isNotBlank()) { "must have name"}
    }
    init {
        p("我初始化了 在块2")
    }



    constructor(_name : String) : this(_name, 29, "西安") {
        p("次构造函数")
        name = "$_name name次构造函数"
    }

    constructor(_age : Int) : this("", _age, "") {
        p("次构造函数")
        name = "$name   次构造函数"
        age = _age + 1
        if(city.isNullOrBlank()) {
            city = "西安"
        }
    }

    constructor() : this("", 0, "")

    /** kotlin自动生成set get方法 想要覆盖 如下写 其中field指该字段
     * field不是必须的 在方法中*/
    var name : String? = name
//        get() = "name is $field"
//        set(value) {
//            field = "中文:$value"
//        }

    var age : Int? = age
//        private set(value) {
//            //隐藏set方法
//        }

    override fun toString(): String {
        return "FirstBean(city='$city', name=$name, age=$age)"
    }

    lateinit var info:String;

    fun showInfo() {
        if(::info.isInitialized)
            p("info is $info")
    }


    //lazy初始化的属性没有set方法 所以不能用var开头
    val infoWithLazy by lazy {
        p("进入lazy方法")
        "你今年会找到好工作，走向人生巅峰，家人也会平平安安。"
    }



    fun showLazyInfo() {
        p(infoWithLazy)
    }

}