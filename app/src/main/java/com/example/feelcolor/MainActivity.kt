package com.example.feelcolor

import android.content.DialogInterface
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Window
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContentView(R.layout.activity_main)
        var image = findViewById<Button>(R.id.image)
        var button = findViewById<Button>(R.id.button)
        var sbred = findViewById<SeekBar>(R.id.red_seekBar2)
        var sbgreen = findViewById<SeekBar>(R.id.green_seekBar2)
        var sbblue = findViewById<SeekBar>(R.id.blue_seekBar2)
        var tr = findViewById<TextView>(R.id.tr)
        var tg = findViewById<TextView>(R.id.tg)
        var tb = findViewById<TextView>(R.id.tb)
        var argb = findViewById<TextView>(R.id.argb)
        var album_view = findViewById<Button>(R.id.button4)

        fun rnd():Int{
            val s = (0..255).shuffled().first()
            return s
        }

        var album: List<List<Int>>
        album = listOf()

        var r1 = rnd()
        var g1 = rnd()
        var b1 = rnd()

        var color1 = Color.rgb(r1, g1, b1)
        var r=0; var g=0; var b=0;




        fun setButtonColor(r:Int, g:Int, b:Int) {
            tr.setText(r.toString())
            tg.setText(g.toString())
            tb.setText(b.toString())
            val color = Color.rgb(r, g, b)
            argb.setText("#"+java.lang.Integer.toHexString(color))
            var color2 = Color.rgb(r, g, b)
            button.setBackgroundColor(color2)
        }
        fun hint(r:Int, g:Int, b:Int){

            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialog_layout = inflater.inflate(R.layout.hint, null)

            var trh = dialog_layout.findViewById<TextView>(R.id.trh)
            var tgh = dialog_layout.findViewById<TextView>(R.id.tgh)
            var tbh = dialog_layout.findViewById<TextView>(R.id.tbh)
            var colorh = dialog_layout.findViewById<TextView>(R.id.colorh)


            trh.setText("Red: "+r.toString())
            tgh.setText("Green: "+g.toString())
            tbh.setText("Blue: "+b.toString())

            var color_h = Color.rgb(r, g, b)
            colorh.setText("ARGB: #"+java.lang.Integer.toHexString(color_h))

            builder.setView(dialog_layout)
            builder.show()

        }
        fun update(){
            album.plus(listOf(color1, r1, g1, b1, r, g, b))
            r1 = rnd()
            g1 = rnd()
            b1 = rnd()
            color1 = Color.rgb(r1, g1, b1)
            image.setBackgroundColor(color1)
        }

        val positive = {dialog: DialogInterface, which: Int -> update() }
        fun next(color1:Int,  color:Int){

            val builder = AlertDialog.Builder(this)
            builder.setPositiveButton("Next", DialogInterface.OnClickListener(function = positive))
            val inflater = layoutInflater
            val dialog_layout = inflater.inflate(R.layout.next, null)

            var color1_v = dialog_layout.findViewById<TextView>(R.id.textView1)
            var color_v = dialog_layout.findViewById<TextView>(R.id.textView)
            var button2 = dialog_layout.findViewById<Button>(R.id.button2)
            var button3 = dialog_layout.findViewById<Button>(R.id.button3)
            var text_percent = dialog_layout.findViewById<TextView>(R.id.textView_percent)
            var percent = (100-(sqrt (((r1-r)*(r1-r)+(g1-g)*(g1-g)+(b1-b)*(b1-b)).toDouble()) / (sqrt(2.0)*255))*100).toInt()


            color1_v.setText("#"+java.lang.Integer.toHexString(color1))
            color_v.setText("#"+java.lang.Integer.toHexString(color))
            button2.setBackgroundColor(color1)
            button3.setBackgroundColor(color)
            text_percent.setText(percent.toString()+"%")

            builder.setView(dialog_layout)
            builder.show()

        }



        image.setBackgroundColor(color1)
        setButtonColor(r,g,b)

        sbred?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                r = progress
                setButtonColor(r,g,b)
                // написать собственный код для прогресса

            }
            override fun onStartTrackingTouch(seek: SeekBar) {}


            override fun onStopTrackingTouch(seek: SeekBar) {}

        })
        sbgreen?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                g = progress
                setButtonColor(r,g,b)
                // написать собственный код для прогресса

            }
            override fun onStartTrackingTouch(seek: SeekBar) {}


            override fun onStopTrackingTouch(seek: SeekBar) {}

        })
        sbblue?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                b = progress
                setButtonColor(r,g,b)
                // написать собственный код для прогресса

            }
            override fun onStartTrackingTouch(seek: SeekBar) {}


            override fun onStopTrackingTouch(seek: SeekBar) {}

        })

        image.setOnClickListener{
            hint(r1, g1, b1)
        }
        button.setOnClickListener{
            next(color1, Color.rgb(r,g,b))

        }
        album_view.setOnClickListener{

        }

    }

}
/*int R = (color >> 16) & 0xff;
 int G = (color >>  8) & 0xff;
 int B = (color      ) & 0xff;
*/