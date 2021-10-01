package com.zb.review.data.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;


/**
 schema ="myschema",  //如果该实体属于多个表单，可以使用该参数;
          active =true,  // 该实体属于激活状态，激活状态的实体有更新，删除，刷新方法;
          nameInDb ="name", // 给这个表指定一个名字，默认情况下是名字是类名
           indexes = {@Index(value ="name DESC", unique =true) }, // 可以给多个属性定义索引和其他属性.
         createInDb =false, //是否使用GreenDao创建该表.
          generateConstructors =true,  // 是否所有的属性构造器都应该被生成，无参构造器总是被要求
          generateGettersSetters =true,  // 如果该类中没有set get方法是否自动生成

        -->实体属性的注解及参数
@Id  //一般会选择long/Long属性作为Entity ID（即数据库中的主键）autoincrement=true表示主键会自增如果false就会使用旧值
@Property  //可以自定义一个该属性在数据库中的名称，默认情况下数据库中该属性名称是Bean对象中的 属性名但是不是以驼峰式而是以大写与下划线组合形式来命名的比如：customName将命名为 CUSTOM_NAME;注意：外键不能使用该属性；
@NotNull  //确保属性值不会为null值;
@Transient  //使用该注释的属性不会被存入数据库中;
@Unique   //唯一约束
@ToMany   //一对多
@OrderBy   //排序
@ToOne   一对一
@generated  //由greendao产生的构造函数或方法
 */


@Entity(nameInDb = "table_person")
public class Person {

    @Id(autoincrement = true)
    private Long id;

    private String name;

    private String gender;

    private Integer age;

    private Float height;

    private Float weight;

    @Generated(hash = 942471396)
    public Person(Long id, String name, String gender, Integer age, Float height,
            Float weight) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    @Generated(hash = 1024547259)
    public Person() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getHeight() {
        return this.height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWeight() {
        return this.weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}
