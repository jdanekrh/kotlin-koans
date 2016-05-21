package iii_conventions

fun todoTask29_2(): Nothing = TODO(
    """
        Task29.2.
        Support adding several time intervals to a date.
        Add an extra class for storing the time interval and the number of intervals,
        e.g. 'class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)'.
        Add an extension function 'times' to TimeInterval, constructing the value of this class.
        Add an extension function 'plus' to MyDate, taking a RepeatedTimeInterval as an argument.
    """
)

class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)

operator fun TimeInterval.times(n: Int): RepeatedTimeInterval {
    return RepeatedTimeInterval(this, n)
}

operator fun MyDate.plus(ti: TimeInterval): MyDate {
    return addTimeIntervals(ti, 1)
}

operator fun MyDate.plus(rti: RepeatedTimeInterval): MyDate {
    return addTimeIntervals(rti.ti, rti.n)
}