package com.mingyuwu.lrucache_pro

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.LruCache
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mingyuwu.lrucache_pro.databinding.ItemImgBinding
import com.mingyuwu.lrucache_pro.util.memoryCache


class BitmapAdapter() :
    ListAdapter<Int, BitmapAdapter.ImageViewHolder>(DiffCallback) {

    class ImageViewHolder(private var binding: ItemImgBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(img: Int) {
            val key = img.toString()
            val bm = memoryCache.get(key)
            binding.position = key

            if (bm == null) {
                util.decodeSampledBitmapFromResource(
                    binding.root.resources, img, 800, 200
                )?.also { bitmap ->
                    memoryCache.put(key, bitmap)
                    binding.img.setImageBitmap(bitmap)
                }
            } else {
                binding.img.setImageBitmap(bm)
            }

//             without using cache
//            util.decodeSampledBitmapFromResource(
//                binding.root.resources, img, 800, 200
//            )?.also { bitmap ->
//                memoryCache.put(key, bitmap)
//                binding.img.setImageBitmap(bitmap)
//            }
        }
    }

    // Allows the RecyclerView to determine which items have changed when the [List] of [Bitmap] has been updated.
    companion object DiffCallback : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return false
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return false
        }
    }

    // Create new [RecyclerView] item views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ImageViewHolder {

        val holder = ImageViewHolder(
            ItemImgBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        return holder
    }

    // Replaces the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val img = getItem(position)
        holder.bind(img)
    }
}

object util {

    private val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()

    val cacheSize =
        maxMemory / 3 // Use 1/3th (kBytes) of the available memory for this memory cache.

    val memoryCache = object : LruCache<String, Bitmap>(cacheSize) {

        override fun sizeOf(key: String, bitmap: Bitmap): Int {

            return bitmap.byteCount / 1024

        }
    }

    fun decodeSampledBitmapFromResource(
        res: Resources?, resId: Int,
        reqWidth: Int, reqHeight: Int,
    ): Bitmap? {

        // First decode with inJustDecodeBounds=true to check dimensions
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(res, resId, options)

        // Calculate inSampleSize
//        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeResource(res, resId, options)
    }

    private fun calculateInSampleSize(
        options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int,
    ): Int {
        // Raw height and width of image
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1
        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            val heightRatio = Math.round(height.toFloat() / reqHeight.toFloat())
            val widthRatio = Math.round(width.toFloat() / reqWidth.toFloat())

            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
        }
        return inSampleSize
    }
}