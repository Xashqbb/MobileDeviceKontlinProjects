class Currency(val code: String, val amount: Double)
    class CurrencyConverter {
    fun convert(inputCurrency: Currency, conversionRate: Double, outputCurrencyCode: String): Currency {
        val outputAmount = inputCurrency.amount * conversionRate
        return Currency(outputCurrencyCode, outputAmount)
    }
    fun getUserInput(): Triple<Currency, Double, String> {
        println("Input code of currency (example, USD, EUR, UAH):")
        val inputCurrencyCode = readLine() ?: ""

        println("Input amount of input currency :")
        val inputAmount = readLine()?.toDoubleOrNull() ?: 0.0

        println("Enter the input currency rate")
        val conversionRate = readLine()?.toDoubleOrNull() ?: 1.0

        println("Enter code of output currency (example, USD, EUR, UAH):")
        val outputCurrencyCode = readLine() ?: ""

        val inputCurrency = Currency(inputCurrencyCode, inputAmount)
        return Triple(inputCurrency, conversionRate, outputCurrencyCode)
    }
}
fun main() {
    val converter = CurrencyConverter()
    val (inputCurrency, conversionRate, outputCurrencyCode) = converter.getUserInput()
    val outputCurrency = converter.convert(inputCurrency, conversionRate, outputCurrencyCode)
    println("${inputCurrency.amount} ${inputCurrency.code} equal ${outputCurrency.amount} ${outputCurrency.code} according to the exchange rate $conversionRate.")
}
