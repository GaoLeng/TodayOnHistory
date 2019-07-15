package me.gaoleng.todayonhistory.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.lzy.okgo.OkGo
import com.lzy.okgo.cache.CacheMode
import com.lzy.okgo.callback.AbsCallback
import kotlinx.android.synthetic.main.activity_main.*
import me.gaoleng.todayonhistory.*
import me.gaoleng.todayonhistory.adapters.TodayAdapter
import me.gaoleng.todayonhistory.beans.ResponseBean
import me.gaoleng.todayonhistory.beans.TodayBean
import me.gaoleng.todayonhistory.utils.Const
import me.gaoleng.todayonhistory.utils.JsonCallBack
import me.gaoleng.todayonhistory.utils.Utils

class MainActivity : AppCompatActivity() {

    var adapter: TodayAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        adapter = TodayAdapter(this, null)
        rv_history.adapter = adapter
        rv_history.layoutManager = LinearLayoutManager(this)
        adapter!!.callBack = object : TodayAdapter.Callback {
            override fun onItemClick(data: TodayBean, position: Int) {
                getTodayDetails(data._id)
            }
        }

        val today = Utils.getTodayForDisplay(this);
        tv_today.setText("""历史上${today}都发生了什么""")

        getTodayHistory()
    }

    private fun getTodayHistory() {
        val dayMonth = Utils.getTodayForQuery(this).split("/")
        get("http://api.juheapi.com/japi/toh",
                mapOf("month" to dayMonth[0], "day" to dayMonth[1]),
                object : JsonCallBack<List<TodayBean>>(this) {
                    override fun onSuccess(data: List<TodayBean>) {
                        adapter?.refresh(data)
                    }

                    override fun onError(msg: String) {
                        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
                    }
                })
    }

    private fun getTodayDetails(id: String) {
        Log.e("TAG", "id: $id")
        get("http://api.juheapi.com/japi/tohdet",
                mapOf("id" to id),
                object : JsonCallBack<List<TodayBean>>(this) {
                    override fun onSuccess(data: List<TodayBean>) {
                        val intent = Intent(this@MainActivity, TodayActivity::class.java)
                        intent.putExtra("data", data[0])
                        startActivity(intent)
                    }

                    override fun onError(msg: String) {
                        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
                    }
                })
    }

    inline fun <reified T> get(url: String, params: Map<String, String>, callBack: AbsCallback<ResponseBean<T>>) {
        OkGo.get<ResponseBean<T>>(url)
                .cacheKey(url)
                .cacheMode(CacheMode.IF_NONE_CACHE_REQUEST)
                .params("key", Const.getKey())
                .params(params)
                .execute(callBack)
    }
}