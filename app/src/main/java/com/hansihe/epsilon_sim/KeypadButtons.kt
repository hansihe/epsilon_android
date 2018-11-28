package com.hansihe.epsilon_sim

object KeypadButtons {

    // Button codes in
    // ion/include/ion/events.h
    // ion/include/ion/keyboard.h

    data class BtnDef(
            val keyNum: Int,
            val main: String,
            val alpha: String? = null,
            val shift: String? = null
    )

    val buttons = arrayOf(
            arrayOf(
                    BtnDef(12, "shift"),
                    BtnDef(13, "alpha", null, "ALPHA"),
                    BtnDef(14, "x,n,t", ":", "cut"),
                    BtnDef(15, "var", ";", "copy"),
                    BtnDef(16, "tool", "\"", "paste"),
                    BtnDef(17, "del", null, "clear")
            ),
            arrayOf(
                    BtnDef(18, "e^x", "A", "["),
                    BtnDef(19, "ln", "B", "]"),
                    BtnDef(20, "log", "C", "{"),
                    BtnDef(21, "i", "D", "}"),
                    BtnDef(22, ",", "E", "_"),
                    BtnDef(23, "x^y", "F", "sto")
            ),
            arrayOf(
                    BtnDef(24, "sin", "G", "asin"),
                    BtnDef(25, "cos", "H", "acos"),
                    BtnDef(26, "tan", "I", "atan"),
                    BtnDef(27, "π", "J", "="),
                    BtnDef(28, "√", "K", "<"),
                    BtnDef(29, "x^2", "L", ">")
            ),
            arrayOf(
                    BtnDef(30, "7", "M"),
                    BtnDef(31, "8", "N"),
                    BtnDef(32, "9", "O"),
                    BtnDef(33, "(", "P"),
                    BtnDef(34, ")", "Q")
            ),
            arrayOf(
                    BtnDef(36, "4", "R"),
                    BtnDef(37, "5", "S"),
                    BtnDef(38, "6", "T"),
                    BtnDef(39, "×", "U"),
                    BtnDef(40, "÷", "V")
            ),
            arrayOf(
                    BtnDef(42, "1", "W"),
                    BtnDef(43, "2", "X"),
                    BtnDef(44, "3", "Y"),
                    BtnDef(45, "+", "Z"),
                    BtnDef(46, "-", "space")
            ),
            arrayOf(
                    BtnDef(48, "0", "?"),
                    BtnDef(49, ".", "!"),
                    BtnDef(50, "x10"),
                    BtnDef(51, "Ans"),
                    BtnDef(52, "EXE")
            )
    )

}