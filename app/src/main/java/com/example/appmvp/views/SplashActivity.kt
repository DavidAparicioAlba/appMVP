package com.example.appmvp.views

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FloatPropertyCompat
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.example.appmvp.R
import kotlinx.android.synthetic.main.activity_splash.*
import kotlin.LazyThreadSafetyMode.NONE


class SplashActivity : AppCompatActivity() {

    var circ1:ImageView?=null
    var circ0:ImageView?=null
    var circ2:ImageView?=null

    private val firstXAnim by lazy(NONE) { createSpringAnimation(circ1, DynamicAnimation.X) }
    private val firstYAnim by lazy(NONE) { createSpringAnimation(circ1, DynamicAnimation.Y) }
    private val secondXAnim by lazy(NONE) { createSpringAnimation(circ2, DynamicAnimation.X) }
    private val secondYAnim by lazy(NONE) { createSpringAnimation(circ2, DynamicAnimation.Y) }

    private var dX = 0f
    private var dY = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        //circ1=circle1
        //circ1?.animate()?.translationX(300.toFloat())?.setDuration(2000)?.start()
        //circ1?.animate()?.setStartDelay(2000)?.translationX(-600.toFloat())?.setDuration(2000)?.alpha(0.toFloat())
        circ0=circle0
        circ1=circle1
        circ2=circle2
        circ0?.alpha=0.9f
        circ1?.alpha=0.6f
        circ2?.alpha=0.3f
        setupAnimation()

        Handler().postDelayed({
            startActivity(Intent(this, InitActivity::class.java))
        }, 10000)

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupAnimation() {
        val firstLayoutParams = circ1?.layoutParams as ViewGroup.MarginLayoutParams
        val secondLayoutParams = circ2?.layoutParams as ViewGroup.MarginLayoutParams

        firstXAnim.onUpdate { value ->

                secondXAnim.animateToFinalPosition(value + ((circ2?.width?.toFloat()?.let {
                    (circ1?.width?.toFloat())?.minus(
                        it
                    )
                })?.div(
                    1
                ) ?: 0f))
        }
        firstYAnim.onUpdate { value ->
            secondYAnim.animateToFinalPosition(value + (circ1?.height?.toFloat() ?: 0f) +
                    secondLayoutParams.topMargin)
        }

        circ0?.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    dX = view.x - event.rawX
                    dY = view.y - event.rawY
                }

                MotionEvent.ACTION_MOVE -> {
                    val newX = event.rawX + dX
                    val newY = event.rawY + dY

                    view.animate().x(newX).y(newY).setDuration(0).start()
                    firstXAnim.animateToFinalPosition(newX + ((circ0?.width?.toFloat() ?: 0f -
                    (circ1?.width?.toFloat() ?: 0f)) / 100))
                    firstYAnim.animateToFinalPosition(newY + (circ0?.height?.toFloat() ?: 0f) +
                            firstLayoutParams.topMargin)
                }
            }

            return@setOnTouchListener true
        }
    }

    private fun <K> createSpringAnimation(obj: K, property: FloatPropertyCompat<K>): SpringAnimation {
        return SpringAnimation(obj, property).setSpring(SpringForce())
    }

    private inline fun SpringAnimation.onUpdate(crossinline onUpdate: (value: Float) -> Unit): SpringAnimation {
        return this.addUpdateListener { _, value, _ ->
            onUpdate(value)
        }
    }
}
