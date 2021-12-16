package com.mingyuwu.lrucache_pro

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mingyuwu.lrucache_pro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val adapter = BitmapAdapter()
        binding.objectList.adapter = adapter
        adapter.submitList(listOf(
            R.drawable.img1,R.drawable.img2,R.drawable.img_1,R.drawable.img_1,
//            R.drawable.img_1,R.drawable.img_1,R.drawable.img_1,R.drawable.img_1,
//            R.drawable.img_1,R.drawable.img_1,R.drawable.img_1,R.drawable.img_1,
//            R.drawable.img_1,R.drawable.img_1,R.drawable.img_1,R.drawable.img_1,
//            R.drawable.img_1,R.drawable.img_1,R.drawable.img_1,R.drawable.img_1,
//            R.drawable.img_1,R.drawable.img_1,R.drawable.img_1,R.drawable.img_1,
//            R.drawable.img_1,R.drawable.img_1,R.drawable.img_1,R.drawable.img_1,
//            R.drawable.img_1,R.drawable.img_1,R.drawable.img_1,R.drawable.img_1,
//            R.drawable.img_1,R.drawable.img_1,R.drawable.img_1,R.drawable.img_1,
//            R.drawable.img_1, R.drawable.img_2, R.drawable.img_3,
//            R.drawable.img_4, R.drawable.img_5,
//            R.drawable.img_6,
//            R.drawable.img_7, R.drawable.img_8, R.drawable.img_9, R.drawable.img_10,R.drawable.img_7,R.drawable.img_7,R.drawable.img_7,R.drawable.img_7,R.drawable.img_7,
//            R.drawable.img_7,R.drawable.img_7,R.drawable.img_7,R.drawable.img_7,R.drawable.img_7
        ))

//        val cityPoints: LinkedHashMap<String, String> = LinkedHashMap()
//        cityPoints["Pune"] = "Ganesh Temple"
//        cityPoints["Mumbai"] = "Gateway Of India"
//        cityPoints["Delhi"] = "Red fort"
//        val cities: Iterator<String> = cityPoints.keys.iterator()
//        while (cities.hasNext()) {
//            val city = cities.next()
//            Log.d("LinkedHashMap","City name : $city")
//        }

        setContentView(binding.root)

    }
}

