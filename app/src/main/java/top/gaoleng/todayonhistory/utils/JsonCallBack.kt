package top.gaoleng.todayonhistory.utils

import android.app.ProgressDialog
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lzy.okgo.callback.AbsCallback
import com.lzy.okgo.model.Response
import com.lzy.okgo.request.base.Request
import top.gaoleng.todayonhistory.beans.ResponseBean
import top.gaoleng.todayonhistory.beans.TodayBean

abstract class JsonCallBack<T>(val context: Context) : AbsCallback<ResponseBean<T>>() {
    var dialog: ProgressDialog? = null

    @Throws(Throwable::class)
    override fun convertResponse(response: okhttp3.Response): ResponseBean<T>? {
        val res = response.body()!!.string()
        Log.e("TAG", "result: $res")
        return Gson().fromJson<ResponseBean<T>>(res, object : TypeToken<ResponseBean<List<TodayBean>>>() {}.type)
    }

    override fun onStart(request: Request<ResponseBean<T>, out Request<Any, Request<*, *>>>?) {
        super.onStart(request)
        showLoading()
    }

    override fun onFinish() {
        super.onFinish()
        dismiss()
    }

    override fun onSuccess(response: Response<ResponseBean<T>>?) {
        if (response?.body() == null) {
            onError("服务器返回为空")
            return
        }
        if (response.body().error_code != 0) {
            onError(response.body().reason)
            return
        }
        onSuccess(response.body().result)
    }

    abstract fun onSuccess(data: T)

    abstract fun onError(msg: String)

    fun showLoading() {
        if (dialog == null){
            dialog = ProgressDialog(context)
            dialog!!.setMessage("加载中...")
        }
        dialog!!.show()
    }

    fun dismiss() {
        if (dialog == null || dialog!!.isShowing)
            dialog?.dismiss()
    }
}
