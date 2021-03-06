package com.example.runningapp.db

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream


class Converters {
    @TypeConverter
    fun fromBitmap(bmp: Bitmap): ByteArray {
        val outPutStream = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.PNG,100,outPutStream)
        return outPutStream.toByteArray()
    }
    @TypeConverter
    fun toBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
    }
}