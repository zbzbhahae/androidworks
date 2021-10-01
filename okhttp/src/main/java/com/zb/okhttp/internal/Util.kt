package com.zb.okhttp.internal

import java.util.*

/** Returns true if we should void putting this this header in an exception or toString() */
fun isSensitiveHeader(name: String) : Boolean {
    return name.equals("Authorization", ignoreCase = true) ||
            name.equals("Cookie", ignoreCase = true) ||
            name.equals("Proxy-Authorization", ignoreCase = true) ||
            name.equals("Set-Cookie", ignoreCase = true)
}


/** Returns a [Locale.US] formatted [String].*/
fun format(format : String, vararg args:Any) : String {
    return String.format(Locale.US, format, *args)
}

class Util {


}