package com.yjy.sample

import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.yjy.resourceutils.ShapeDrawableUtils
import com.yjy.sample.databinding.FragmentUtilsShapeBinding

class ShapeUtilsFragment : Fragment() {

    private lateinit var mBinding: FragmentUtilsShapeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_utils_shape,
            container,
            false
        )

        mBinding.apply {
            solidColor.setOnSeekBarChangeListener(onShapeConfigChangeListener)
            strokeColor.setOnSeekBarChangeListener(onShapeConfigChangeListener)
            strokeWidth.setOnSeekBarChangeListener(onShapeConfigChangeListener)
            cornersRadius.setOnSeekBarChangeListener(onShapeConfigChangeListener)

            startColor.setOnSeekBarChangeListener(onGradientShapeConfigChangeListener)
            endColor.setOnSeekBarChangeListener(onGradientShapeConfigChangeListener)
            angle.setOnSeekBarChangeListener(onGradientShapeConfigChangeListener)
        }

        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    private fun initView() {
        onShapeConfigChangeListener.onProgressChanged(null, 0, false)
        onGradientShapeConfigChangeListener.onProgressChanged(null, 0, false)
    }

    private val onShapeConfigChangeListener =
        object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean
            ) {
                mBinding.apply {
                    shape.setImageDrawable(
                        ShapeDrawableUtils.Builder(context)
                            .solid(getColor(solidColor.progress))
                            .stroke(strokeWidth.progress, getColor(strokeColor.progress))
                            .corner(cornersRadius.progress)
                            .build()
                    )
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit
            override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit
        }

    private val onGradientShapeConfigChangeListener =
        object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mBinding.apply {
                    gradientShape.setImageDrawable(
                        ShapeDrawableUtils.Builder(context)
                            .startColor(getColor(startColor.progress))
                            .endColor(getColor(endColor.progress))
                            .angle(getAngle())
                            .corner(cornersRadius.progress)
                            .build()
                    )
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit
            override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit
        }

    private fun getColor(h: Int) =
        Color.HSVToColor(arrayOf(h.toFloat(), 1f, 1f).toFloatArray())

    private fun getAngle(): Int = mBinding.angle.progress * 45

    companion object {
        fun create(): ShapeUtilsFragment {
            return ShapeUtilsFragment()
        }
    }
}
