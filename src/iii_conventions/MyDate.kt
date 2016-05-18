package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)

operator fun MyDate.compareTo(other: MyDate): Int {
    if (year < other.year) {
        return -1
    } else if (year > other.year) {
        return 1
    }

    if (month < other.month) {
        return -1
    } else if (month > other.month) {
        return 1
    }

    if (dayOfMonth < other.dayOfMonth) {
        return -1
    } else if (dayOfMonth > other.dayOfMonth) {
        return 1
    }

    return 0
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = todoTask27()

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate)
