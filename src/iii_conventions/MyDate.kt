package iii_conventions

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return firstNonZero(
                year.compareTo(other.year),
                month.compareTo(other.month),
                dayOfMonth.compareTo(other.dayOfMonth)
        )
    }

    private fun firstNonZero(vararg els: Int) : Int {
        for (el in els) {
            if (el != 0) return el
        }
        return els.last()
    }

    operator fun inc() : MyDate {
        val tomorrow = LocalDate.of(year, month + 1, dayOfMonth).plusDays(1)
        return MyDate(tomorrow.year, tomorrow.month.value - 1, tomorrow.dayOfMonth)
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    operator fun contains(date: MyDate) : Boolean {
        return start <= date && date <= endInclusive
    }

    override fun iterator(): Iterator<MyDate> = DateRangeIterator(start, endInclusive)
}

class DateRangeIterator(val start: MyDate, val endInclusive: MyDate) : Iterator<MyDate> {

    private var current = start;

    override fun hasNext(): Boolean = current <= endInclusive

    override fun next(): MyDate {
        return current++
    }
}
