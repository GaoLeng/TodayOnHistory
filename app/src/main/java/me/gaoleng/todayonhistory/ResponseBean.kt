package me.gaoleng.todayonhistory

data class ResponseBean<T>(
        //  "result":
        //	"reason":"请求成功！",
        //	"error_code":0
        val result: T,
        val reason: String,
        val error_code: Int
)