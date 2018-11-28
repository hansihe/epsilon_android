package com.hansihe.epsilon_sim

object EpsilonEvents {

    const val Left: Int = 0;
    const val Up: Int = 1;
    const val Down: Int = 2;
    const val Right: Int = 3;
    const val OK: Int = 4;
    const val Back: Int = 5;
    const val Home: Int = 6;
    const val OnOff: Int = 7;
    const val Shift: Int = 12;
    const val Alpha: Int = 13;
    const val XNT: Int = 14;
    const val Var: Int = 15;
    const val Toolbox: Int = 16;
    const val Backspace: Int = 17;
    const val Exp: Int = 18;
    const val Ln: Int = 19;
    const val Log: Int = 20;
    const val Imaginary: Int = 21;
    const val Comma: Int = 22;
    const val Power: Int = 23;
    const val Sine: Int = 24;
    const val Cosine: Int = 25;
    const val Tangent: Int = 26;
    const val Pi: Int = 27;
    const val Sqrt: Int = 28;
    const val Square: Int = 29;
    const val Seven: Int = 30;
    const val Eight: Int = 31;
    const val Nine: Int = 32;
    const val LeftParenthesis: Int = 33;
    const val RightParenthesis: Int = 34;
    const val Four: Int = 36;
    const val Five: Int = 37;
    const val Six: Int = 38;
    const val Multiplication: Int = 39;
    const val Division: Int = 40;
    const val One: Int = 42;
    const val Two: Int = 43;
    const val Three: Int = 44;
    const val Plus: Int = 45;
    const val Minus: Int = 46;
    const val Zero: Int = 48;
    const val Dot: Int = 49;
    const val EE: Int = 50;
    const val Ans: Int = 51;
    const val EXE: Int = 52;
    const val ShiftLeft: Int = 54;
    const val ShiftUp: Int = 55;
    const val ShiftDown: Int = 56;
    const val ShiftRight: Int = 57;
    const val AlphaLock: Int = 67;
    const val Cut: Int = 68;
    const val Copy: Int = 69;
    const val Paste: Int = 70;
    const val Clear: Int = 71;
    const val LeftBracket: Int = 72;
    const val RightBracket: Int = 73;
    const val LeftBrace: Int = 74;
    const val RightBrace: Int = 75;
    const val Underscore: Int = 76;
    const val Sto: Int = 77;
    const val Arcsine: Int = 78;
    const val Arccosine: Int = 79;
    const val Arctangent: Int = 80;
    const val Equal: Int = 81;
    const val Lower: Int = 82;
    const val Greater: Int = 83;
    const val Colon: Int = 122;
    const val SemiColon: Int = 123;
    const val DoubleQuotes: Int = 124;
    const val LowerA: Int = 126;
    const val LowerB: Int = 127;
    const val LowerC: Int = 128;
    const val LowerD: Int = 129;
    const val LowerE: Int = 130;
    const val LowerF: Int = 131;
    const val LowerG: Int = 132;
    const val LowerH: Int = 133;
    const val LowerI: Int = 134;
    const val LowerJ: Int = 135;
    const val LowerK: Int = 136;
    const val LowerL: Int = 137;
    const val LowerM: Int = 138;
    const val LowerN: Int = 139;
    const val LowerO: Int = 140;
    const val LowerP: Int = 141;
    const val LowerQ: Int = 142;
    const val LowerR: Int = 144;
    const val LowerS: Int = 145;
    const val LowerT: Int = 146;
    const val LowerU: Int = 147;
    const val LowerV: Int = 148;
    const val LowerW: Int = 150;
    const val LowerX: Int = 151;
    const val LowerY: Int = 152;
    const val LowerZ: Int = 153;
    const val Space: Int = 154;
    const val Question: Int = 156;
    const val Exclamation: Int = 157;
    const val UpperA: Int = 180;
    const val UpperB: Int = 181;
    const val UpperC: Int = 182;
    const val UpperD: Int = 183;
    const val UpperE: Int = 184;
    const val UpperF: Int = 185;
    const val UpperG: Int = 186;
    const val UpperH: Int = 187;
    const val UpperI: Int = 188;
    const val UpperJ: Int = 189;
    const val UpperK: Int = 190;
    const val UpperL: Int = 191;
    const val UpperM: Int = 192;
    const val UpperN: Int = 193;
    const val UpperO: Int = 194;
    const val UpperP: Int = 195;
    const val UpperQ: Int = 196;
    const val UpperR: Int = 198;
    const val UpperS: Int = 199;
    const val UpperT: Int = 200;
    const val UpperU: Int = 201;
    const val UpperV: Int = 202;
    const val UpperW: Int = 204;
    const val UpperX: Int = 205;
    const val UpperY: Int = 206;
    const val UpperZ: Int = 207;
    const val None: Int = 210;
    const val Termination: Int = 211;

    val lowerCaseCharacters = arrayOf(
        126, // LowerA
        127, // LowerB
        128, // LowerC
        129, // LowerD
        130, // LowerE
        131, // LowerF
        132, // LowerG
        133, // LowerH
        134, // LowerI
        135, // LowerJ
        136, // LowerK
        137, // LowerL
        138, // LowerM
        139, // LowerN
        140, // LowerO
        141, // LowerP
        142, // LowerQ
        144, // LowerR
        145, // LowerS
        146, // LowerT
        147, // LowerU
        148, // LowerV
        150, // LowerW
        151, // LowerX
        152, // LowerY
        153 // LowerZ
    )
    val upperCaseCharacters = arrayOf(
        180, // UpperA
        181, // UpperB
        182, // UpperC
        183, // UpperD
        184, // UpperE
        185, // UpperF
        186, // UpperG
        187, // UpperH
        188, // UpperI
        189, // UpperJ
        190, // UpperK
        191, // UpperL
        192, // UpperM
        193, // UpperN
        194, // UpperO
        195, // UpperP
        196, // UpperQ
        198, // UpperR
        199, // UpperS
        200, // UpperT
        201, // UpperU
        202, // UpperV
        204, // UpperW
        205, // UpperX
        206, // UpperY
        207 // UpperZ
    )

}
