package com.example.dice

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.buttonRoll)
        rollButton.setOnClickListener { roll() }

        roll()

    }

    private fun roll() {
        val dice = Dice(6)
        val diceImage:ImageView = findViewById(R.id.diceImage)
        val drawableResurse = when (dice.rollDice()){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> {R.drawable.dice_6}
        }
        diceImage.setImageResource(drawableResurse)
    }

    class Dice(val sides: Int) {
        fun rollDice(): Int {
            return (1..sides).random()
        }
    }
}