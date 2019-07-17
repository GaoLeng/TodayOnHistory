package top.gaoleng.todayonhistory.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_today.view.*
import top.gaoleng.todayonhistory.R
import top.gaoleng.todayonhistory.beans.TodayBean
import top.gaoleng.todayonhistory.utils.Utils

class TodayAdapter(var context: Context, var datas: List<TodayBean>?) : RecyclerView.Adapter<TodayAdapter.Holder>() {

    var callBack: Callback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(context).inflate(R.layout.item_today, parent, false))
    }

    override fun getItemCount(): Int {
        return if (datas == null) 0 else datas!!.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val todayBean = datas?.get(position)

        var des = todayBean?.title
        if (position == itemCount - 1) {
            holder.itemView.ll_line.visibility = View.GONE
        } else {
            holder.itemView.ll_line.visibility = View.VISIBLE

        }

        holder.itemView.tv_year.setText(todayBean?.year.toString())
        holder.itemView.tv_desc.setText(des)
        val options: RequestOptions = RequestOptions().override(Utils.dp2px(context, 100), Utils.dp2px(context, 150))
        Glide.with(context).load(todayBean?.pic).apply(options).into(holder.itemView.im_cover)

        holder.itemView.setOnClickListener {
            callBack?.onItemClick(todayBean!!, position)
        }
    }

    fun refresh(datas: List<TodayBean>?) {
        this.datas = datas?.sortedWith(Comparator { o1, o2 ->
            o2.year - o1.year
        })
        notifyDataSetChanged()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface Callback {
        fun onItemClick(data: TodayBean, position: Int)
    }
}