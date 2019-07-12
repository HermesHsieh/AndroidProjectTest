package tw.android.test.activity.anko

import android.content.Context
import android.graphics.Color
import android.support.annotation.DimenRes
import android.support.v4.content.ContextCompat
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.hermes.test.R
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class AnkoActivityUi : AnkoComponent<AnkoActivity> {

    private val customStyle = { v: Any ->
        {
            when (v) {
                is Button -> {
                    v.textSize = 20f
                    v.backgroundColor = R.color.red_700_trans
                }
                is EditText -> {
                    v.textSize = 20f
                    v.hintTextColor = R.color.red_500
                }
            }
        }
    }

    fun applyTemplateViewStyles(v: View) {
        when (v) {
            is Button -> {
                v.textSize = v.context.getDimension(R.dimen.big_text)
//                v.backgroundColor = R.color.deep_blue_500
//                v.backgroundColor = ContextCompat.getColor(v.context, R.color.red_700_trans)
            }
            is EditText -> {
                v.textSize = 20f
                v.hintTextColor = R.color.red_700_trans
//                v.hintTextColor = ContextCompat.getColor(v.context, R.color.deep_blue_500)
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
                        backgroundColor = ContextCompat.getColor(context, R.color.deep_blue_500)
                        textColor = Color.WHITE
                    }.lparams(width = matchParent, height = wrapContent)
                }.applyRecursively { v -> applyTemplateViewStyles(v) }
            }.lparams(width = matchParent, height = matchParent)
        }
    }
}

fun Context.getDimension(@DimenRes res: Int): Float {
    return this.resources.getDimension(res)
}
