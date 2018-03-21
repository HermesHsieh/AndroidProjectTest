package tw.android.test.activity.anko

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.setContentView
import tw.android.test.base.BaseSimpleActivity

class AnkoActivity : BaseSimpleActivity() {

    companion object {
        fun launch(activity: Activity) {
            var intent = Intent(activity, AnkoActivity.javaClass)
            activity.startActivity(intent)
        }
    }

    override fun setContentView() {
        AnkoActivityUi().setContentView(this)
    }

    override fun initView() {

    }

    override fun initData() {
    }

    fun performLogin(ui: AnkoContext<AnkoActivity>, name: CharSequence?, password: CharSequence?) {
        Toast.makeText(this, "User: $name, Password: $password", Toast.LENGTH_SHORT).show()
    }

}
