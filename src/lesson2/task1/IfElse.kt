@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String =
    if (age in 10..19 || age == 111) {
        "$age лет"
    } else when (age % 10) {
        in 2..4 -> "$age года"
        1 -> "$age год"
        else -> "$age лет"
    }


/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double = TODO()

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
//    0 - нет угрозы.
//    1 - угроза от первой. Когда К стоит на одной вертикали с Л1 И НЕ на одной горизонтали с Л2 ИЛИ когда стоит на одной горизонтали Л1 и не на одной вертикали с Л2.
//    2 - угроза от второй. Когда К стоит на одной вертикали с Л2 И НЕ на одной горизонтали с Л1 ИЛИ когда стоит на одной горизонтали с Л2 и не на одной вертикали с Л1.
//    3 - угроза от обеих. Когда К стоит на одной вертикали с Л1 И одной горизонтали с Л2 ИЛИ на одной вертикали с Л2 И одной горизонтали с Л1.
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    if (kingX == rookX1 && kingY != rookY2 || kingY == rookY1 && kingX != rookX2) {
        return 1
    } else if (kingX == rookX2 && kingY != rookY1 || kingY == rookY2 && kingX != rookX1) {
        return 2
    } else if (kingX == rookX1 && kingY == rookY2 || kingX == rookX2 && kingY == rookY1) {
        return 3
    } else {
        return 0
    }
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    if (!(kingX + kingY == bishopX + bishopY || kingX - kingY == bishopX - bishopY) && !(kingX == rookX || kingY == rookY)) { //НЕ бьется слоном И НЕ бьется ладьей
        return 0
    } else if (kingX == rookX || kingY == rookY && !(kingX + kingY == bishopX + bishopY || kingX - kingY == bishopX - bishopY)) { //Бьется ладьей И НЕ бьется слоном
        return 1
    } else if ((kingX + kingY == bishopX + bishopY || kingX - kingY == bishopX - bishopY) && !(kingX == rookX || kingY == rookY)) { //Бьется слоном И НЕ бьется ладьей
        return 2
    } else {
        return 3
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    if (a + c < b) {
        return -1
    } else if (sqr(a) == sqr(b) + sqr(c)) {
        return 1
    } else if (sqr(a) + sqr(b) > sqr(c)) {
        return 0
    } else if (sqr(a) + sqr(b) < sqr(c)) {
        return 2
    }
    return 100
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
//Отрезки не пересекаются, если с > b ИЛИ d < a
//Отрезки не пересекаются, но длина пересечения 0, если отрезки не пересекаются, но соприкасаются скрайними точками, т.е. если c == b или d == a
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    if (c > b || d < a) { //отрезки не пересекаются
        return -1
    } else if (c == b || d == a) { //отрезки соприкасаются
        return 0
    } else if (a >= c && b <= d) { //отрезок 1 лежит на отрезке 2 и наоборот
        return b - a
    } else if (a < c && b > d) {
        return d - c
    } else if (a > c && b > d) { //отрезок 1 пересекается слева
        return d - a
    } else if (a < c && b > c) {
        return b - c
    }
    return 100
}
