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
            solidColor.setOnSeekBarChangeListener(onChangeListener)
            strokeColor.setOnSeekBarChangeListener(onChangeListener)
            strokeWidth.setOnSeekBarChangeListener(onChangeListener)
            cornersRadius.setOnSeekBarChangeListener(onChangeListener)
        }

        return mBinding.root
    }

    private val onChangeListener: SeekBar.OnSeekBarChangeListener =
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

            private fun getColor(h: Int) =
                Color.HSVToColor(arrayOf(h.toFloat(), 1f, 1f).toFloatArray())

            override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit
            override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit
        }

    companion object {
        fun create(): ShapeUtilsFragment {
            return ShapeUtilsFragment()
        }
    }
}
