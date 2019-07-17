package top.gaoleng.todayonhistory.beans

import java.io.Serializable

data class TodayBean(
//"_id":"12230714",
//"title":"法国国王腓力二世逝世",
//"pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201108/6/08492011422141.jpg",
//"year":1223,
//"month":7,
//"day":14,
//"des":"在796年前的今天，1223年7月14日 (农历六月十五)，法国国王腓力二世逝世。",
//"lunar":"癸未年六月十五"
        val _id: String,
        val title: String,
        val pic: String,
        val year: Int,
        val month: Int,
        val day: Int,
        val des: String,
        val content: String,
        val lunar: String
) : Serializable