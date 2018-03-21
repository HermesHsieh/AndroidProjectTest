package tw.android.test.activity.anko

import android.text.InputType
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import com.example.hermes.test.R
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class AnkoActivityUi : AnkoComponent<AnkoActivity> {

    private val customStyle = { v: Any ->
        {
            when (v) {
                is Button -> v.textSize = 26f
                is EditText -> v.textSize = 20f
            }
        }
    }

    override fun createView(ui: AnkoContext<AnkoActivity>) = with(ui) {
        verticalLayout {
            scrollView {
                verticalLayout {
                    padding = dip(32)

                    imageView(R.mipmap.android_oreo).lparams {
                        margin = dip(16)
                        gravity = Gravity.CENTER
                        height = dip(360)
                    }

                    val name = editText {
                        hintResource = R.string.app_name
                    }.lparams(width = matchParent, height = wrapContent)

                    val password = editText {
                        hintResource = R.string.input_hint
                        inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    }.lparams(width = matchParent, height = wrapContent)

                    button("Login") {
                        onClick {
                            ui.owner.performLogin(ui, name.text, password.text)
                        }
                    }.lparams(width = matchParent, height = wrapContent)
                }.applyRecursively { customStyle }
            }.lparams(width = matchParent, height = matchParent)
        }
    }
}
