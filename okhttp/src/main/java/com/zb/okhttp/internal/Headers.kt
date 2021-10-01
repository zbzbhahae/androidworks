package com.zb.okhttp.internal

/**
 * 单个 HTTP 消息的头字段。值是未解释的
 * 字符串；将 `Request` 和 `Response` 用于
 * 解释的标头。此类维护 HTTP 消息中标头字段
 * 的顺序。此类逐行跟踪标题值。在同一行上具有
 * 多个逗号分隔值的字段将被此类视为具有单个值
 * 的字段。如果他们的字段允许多个值，则调用者
 * 有责任检测和拆分逗号。这简化了单值字段的使用，
 * 这些字段的值通常包含逗号，例如 cookie 或日期。
 * 此类从值中修剪空白。它从不返回带有前导或尾随
 * 空格的值。此类的实例是不可变的。使用 [Builder]
 * 创建实例。
 */

@Suppress("NAME_SHADOWING")
class Headers private constructor(
    private val namesAndValues: Array<String>
) : Iterable<Pair<String, String>> {

    @get:JvmName("size") val size: Int
        get() = namesAndValues.size / 2

    fun name(index: Int): String = namesAndValues[index * 2]
    fun value(index: Int): String = namesAndValues[index * 2  + 1]

    /** 返回指定字段对应的最后一个值，或为空**/
    operator fun get(name: String): String? = get(namesAndValues, name)



    override fun iterator(): Iterator<Pair<String, String>> {
        return Array(size) {
            name(it) to value(it)
        }.iterator()
    }


    companion object {
        private fun get(namesAndValues: Array<String>, name: String) : String? {
            for( i in namesAndValues.size - 2 downTo 0 step 2) {
                if(name.equals(namesAndValues[i], ignoreCase = true)) {
                    return namesAndValues[i + 1]
                }
            }
            return null
        }


        /**返回交替标头名称和值的标头。必须有偶数个参数，并且它们必须在标头名称和值之间交替。**/
        @JvmStatic
        @JvmName("of")
        fun headersOf(vararg namesAndValues: String) : Headers {
            require(namesAndValues.size % 2 == 0) {"Expected alternating header names and values"}

            //Make a defensive copy and clean it up
            val namesAndValues: Array<String> = namesAndValues.clone() as Array <String>
            for(i in namesAndValues.indices) {
                require(namesAndValues[i] != null) {"Headers cannot be null"}
                namesAndValues[i] = namesAndValues[i].trim()
            }

            //Check for malformed headers
            for(i in namesAndValues.indices step 2) {
                val name = namesAndValues[i]
                val value = namesAndValues[i + 1]
                checkName(name)
                checkValue(value, name)
            }

            return Headers(namesAndValues)
        }

        private fun checkName(name : String) {
            require(name.isNotEmpty()) {"name is empty"}
            for(i in name.indices) {
                val c = name[i]
                require(c in '\u0021'..'\u007e') {
                    format("Unexpected char %#04x and %d in header name : %s", c.code, i, name)
                }
            }
        }

        private fun checkValue(value : String, name : String) {
            for (i in value.indices) {
                val c = value[i]
                require(c == '\t' || c in '\u0020'..'\u007e') {
                    format("Unexpected char %#04x and %d in %s value", c.code, i ,name) +
                            (if (isSensitiveHeader(name)) "" else ":$value")
                }

            }
        }

    }

}