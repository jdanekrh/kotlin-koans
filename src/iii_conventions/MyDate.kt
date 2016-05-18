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

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate) {
    operator fun contains(d: MyDate): Boolean = d >= start && d <= endInclusive
    operator fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {
            var current: MyDate = start
            override fun hasNext(): Boolean = current <= endInclusive
            override fun next(): MyDate {
                val c = current
                current = current.nextDay()
                return c
            }
        }
    }
}
