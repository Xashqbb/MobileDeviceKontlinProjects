import java.util.Scanner

// Базовий клас для всіх математичних операцій
abstract class Operation(val a: Double, val b: Double) {
    abstract fun calculate(): Double
}

// Клас для додавання
class Addition(a: Double, b: Double) : Operation(a, b) {
    override fun calculate(): Double {
        return a + b
    }
}

// Клас для віднімання
class Subtraction(a: Double, b: Double) : Operation(a, b) {
    override fun calculate(): Double {
        return a - b
    }
}

// Клас для множення
class Multiplication(a: Double, b: Double) : Operation(a, b) {
    override fun calculate(): Double {
        return a * b
    }
}

// Клас для ділення
class Division(a: Double, b: Double) : Operation(a, b) {
    override fun calculate(): Double {
        if (b == 0.0) {
            throw ArithmeticException("Ділення на нуль неможливе!")
        }
        return a / b
    }
}

// Функція для перевірки введення числа
fun getValidNumber(scanner: Scanner, prompt: String): Double {
    var number: Double? = null
    while (number == null) {
        print(prompt)
        val input = scanner.nextLine()
        try {
            number = input.toDouble()
        } catch (e: NumberFormatException) {
            println("Некоректне значення! Будь ласка, введіть число.")
        }
    }
    return number
}

fun main() {
    val scanner = Scanner(System.`in`)

    val num1 = getValidNumber(scanner, "Введіть перше число: ")

    println("Введіть операцію (+, -, *, /):")
    val operation = scanner.nextLine()

    val num2 = getValidNumber(scanner, "Введіть друге число: ")

    val result: Double

    try {
        result = when (operation) {
            "+" -> Addition(num1, num2).calculate()
            "-" -> Subtraction(num1, num2).calculate()
            "*" -> Multiplication(num1, num2).calculate()
            "/" -> Division(num1, num2).calculate()
            else -> throw IllegalArgumentException("Невідома операція!")
        }
        println("Результат: $result")
    } catch (e: Exception) {
        println("Помилка: ${e.message}")
    }
}
