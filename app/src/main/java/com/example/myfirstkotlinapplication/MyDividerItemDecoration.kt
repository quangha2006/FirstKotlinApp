package com.example.myfirstkotlinapplication
//
//import android.content.Context
//import android.content.res.TypedArray
//import android.graphics.Canvas
//import android.graphics.drawable.Drawable
//import android.support.v7.widget.LinearLayoutManager
//import android.support.v7.widget.RecyclerView
//import android.util.TypedValue
//import android.view.View
//
//
//class MyDividerItemDecoration : RecyclerView.ItemDecoration() {
//    private val ATTRS = intArrayOf(
//        R.attr.listDivider
//    )
//
//    val HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL
//
//    val VERTICAL_LIST = LinearLayoutManager.VERTICAL
//
//    private var mDivider: Drawable? = null
//    private var mOrientation = 0
//    private var context: Context? = null
//    private var margin = 0
//
//    fun MyDividerItemDecoration(context: Context, orientation: Int, margin: Int) {
//        this.context = context
//        this.margin = margin
//        val a: TypedArray = context.obtainStyledAttributes(ATTRS)
//        mDivider = a.getDrawable(0)
//        a.recycle()
//        setOrientation(orientation)
//    }
//
//    fun setOrientation(orientation: Int) {
//        require(!(orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST)) { "invalid orientation" }
//        mOrientation = orientation
//    }
//
//    fun onDrawOver(c: Canvas?, parent: RecyclerView, state: RecyclerView.State?) {
//        if (mOrientation == VERTICAL_LIST) {
//            drawVertical(c, parent)
//        } else {
//            drawHorizontal(c, parent)
//        }
//    }
//
//    fun drawVertical(c: Canvas?, parent: RecyclerView) {
//        val left = parent.paddingLeft
//        val right = parent.width - parent.paddingRight
//        val childCount = parent.childCount
//        for (i in 0 until childCount) {
//            val child: View = parent.getChildAt(i)
//            val params = child
//                .getLayoutParams() as RecyclerView.LayoutParams
//            val top: Int = child.getBottom() + params.bottomMargin
//            val bottom = top + mDivider!!.intrinsicHeight
//            mDivider!!.setBounds(left + dpToPx(margin), top, right - dpToPx(margin), bottom)
//            mDivider!!.draw(c)
//        }
//    }
//
//    fun drawHorizontal(c: Canvas?, parent: RecyclerView) {
//        val top = parent.paddingTop
//        val bottom = parent.height - parent.paddingBottom
//        val childCount = parent.childCount
//        for (i in 0 until childCount) {
//            val child: View = parent.getChildAt(i)
//            val params = child
//                .getLayoutParams() as RecyclerView.LayoutParams
//            val left: Int = child.getRight() + params.rightMargin
//            val right = left + mDivider!!.intrinsicHeight
//            mDivider!!.setBounds(left, top + dpToPx(margin), right, bottom - dpToPx(margin))
//            mDivider!!.draw(c)
//        }
//    }
//
//    fun getItemOffsets(
//        outRect: Rect,
//        view: View?,
//        parent: RecyclerView?,
//        state: RecyclerView.State?
//    ) {
//        if (mOrientation == VERTICAL_LIST) {
//            outRect.set(0, 0, 0, mDivider!!.intrinsicHeight)
//        } else {
//            outRect.set(0, 0, mDivider!!.intrinsicWidth, 0)
//        }
//    }
//
//    private fun dpToPx(dp: Int): Int {
//        val r: Resources = context.getResources()
//        return Math.round(
//            TypedValue.applyDimension(
//                TypedValue.COMPLEX_UNIT_DIP,
//                dp.toFloat(),
//                r.getDisplayMetrics()
//            )
//        )
//    }
//}