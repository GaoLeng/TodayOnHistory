package top.gaoleng.todayonhistory.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_today.*
import top.gaoleng.todayonhistory.utils.Const
import top.gaoleng.todayonhistory.R
import top.gaoleng.todayonhistory.beans.TodayBean
import top.gaoleng.todayonhistory.utils.Utils

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
        tv_date.text = Utils.formatDate(data.year, data.month, data.day)
        tv_content.text = convertContent(data.content)
        Glide.with(this).load(data.pic).into(im_img)
        im_img.setOnClickListener {
            val intent = Intent(this, DragImgActivity::class.java)
            val location = IntArray(2)
            im_img.getLocationOnScreen(location)
            intent.putExtra("left", location[0])
            intent.putExtra("top", location[1])
            intent.putExtra("height", im_img.height)
            intent.putExtra("width", im_img.width)
            intent.putExtra(Const.EXTRA_IMG, data.pic)

            startActivity(intent)
            overridePendingTransition(0,0)
        }
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