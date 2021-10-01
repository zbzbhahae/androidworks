package com.zb.okhttp.internal.http

object HttpMethod {

    fun invalidatesCache(method: String) : Boolean = (method == "POST" ||
            method == "PATCH" ||
            method == "PUT" ||
            method == "DELETE" ||
            method == "MOVE")//webDAV

    @JvmStatic
    fun requiresRequestBody(method: String) : Boolean = (method == "POST" ||
            method == "PATCH" ||
            method == "PUT" ||
            method == "PROPPATCH" ||//webDAV
            method == "REPORT")//CalDAV/CardDAV

    @JvmStatic
    fun permitsRequestBody(method: String) : Boolean = !(method == "GET" || method == "HEAD")

    fun redirectsWithBody(method: String) : Boolean =
        method == "PROPFIND"

    fun redirectsToGet(method: String) : Boolean =
        method != "PROPFIND"

}