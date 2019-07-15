package me.gaoleng.todayonhistory

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_today.*

class TodayActivity : AppCompatActivity() {

    private val DOUBLE_SPACE: String = "\u3000\u3000"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today)

        init()
    }

    private fun init() {
        val data = intent.getSerializableExtra("data") as TodayBean

        setTitle(data.lunar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        tv_title.text = data.title
        tv_date.text = """${data.year}-${data.month}-${data.day}"""
        tv_content.text = convertContent(data.content)
        Glide.with(this).load(data.pic).into(im_img)
    }

    private fun convertContent(content: String): String {
        return (DOUBLE_SPACE + content).replace("\n", "\n\n$DOUBLE_SPACE")
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item)
    }
}