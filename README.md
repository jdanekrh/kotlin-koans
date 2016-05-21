My solutions to Kotlin Koans
============================

Kotlin Koans are a series of exercises to get you familiar with the Kotlin Syntax. 
Each exercise is created as a failing unit test and your job is to make it pass.

(Read more in the upstream repo `README.md`)

## Interesting differences

to the `resulutions` branch in the upstream repo. My branch is called `solutions`. I suggest you try to solve the tasks yourself, before you look at any solutions.

### getTotalOrderPrice

#### resolution

    fun Customer.getTotalOrderPrice(): Double {
        // Return the sum of prices of all products that a customer has ordered.
        // Note: a customer may order the same product for several times.
        return orders.sumByDouble { it.products.sumByDouble { it.price } }
    }

#### solution

    fun Customer.getTotalOrderPrice(): Double {
        // Return the sum of prices of all products that a customer has ordered.
        // Note: a customer may order the same product for several times.
        return orders.flatMap { it.products }.map { it.price }.sum()
    }

Resolution is much more efficient, I guess, because it is reducing the result (summing up) as soon as possible.

### getCustomersWithMoreUndeliveredOrdersThanDelivered

#### Resolution

    fun Shop.getCustomersWithMoreUndeliveredOrdersThanDelivered(): Set<Customer> {
        // Return customers who have more undelivered orders than delivered
        return customers.filter {
            val (delivered, undelivered) = it.orders.partition { it.isDelivered }
            undelivered.size > delivered.size
        }.toSet()
    }

#### Solution a)

    fun Shop.getCustomersWithMoreUndeliveredOrdersThanDelivered(): Set<Customer> {
        // Return customers who have more undelivered orders than delivered
        // FIXME: ugly
        val (moreUndelivered, moreDelivered) = customers.partition {
            val (delivered, undelivered) = it.orders.partition { it.isDelivered }
            undelivered.size > delivered.size
        }
        return moreUndelivered.toSet()

#### Solution b)

        return customers.filter { it.orders.count { !it.isDelivered } > (it.orders.size / 2) }.toSet()
    }


I prefer the terseness Solution b), although Resolution is self-documenting and Solution b) is not.

### getCustomersWhoOrderedProduct

#### Resolution

    fun Shop.getCustomersWhoOrderedProduct(product: Product): Set<Customer> {
        // Return the set of customers who ordered the specified product
        return customers.filter { it.orderedProducts.contains(product) }.toSet()
    }

#### Solution

    fun Shop.getCustomersWhoOrderedProduct(product: Product): Set<Customer> {
        // Return the set of customers who ordered the specified product
        return customers.fold(emptySet<Customer>(), {
            result, customer ->
            if (customer.orderedProducts.contains(product)) result.plus(customer) else result
        })
    }

I believed that the task is intended to practise `fold`, so I used `fold`, even though it is not appropriate.

### Invokable

#### resolution

    class Invokable(private var invocations: Int = 0) {
        operator fun invoke(): Invokable {
            invocations++
            return this
        }

        fun getNumberOfInvocations() = invocations
    }


#### Solution

    class Invokable {
        var _numberOfInvocations: Int = 0
        operator fun invoke(): Invokable {
            _numberOfInvocations++
            return this
        }

        fun getNumberOfInvocations(): Int {
            return _numberOfInvocations
        }
    }

Reminder that there is the field declaration syntactic shortcut. I would not use it here, though, even if it occurred to me I could.

### compareTo

#### Resolution

    data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
        override fun compareTo(other: MyDate) = when {
            year != other.year -> year - other.year
            month != other.month -> month - other.month
            else -> dayOfMonth - other.dayOfMonth
        }
    }

#### Solution

    // too long to put here

Use when for writing compareTo. I took close to 20 lines to do the same thing. Also, fine to return anything negative, no point limiting myself to 1 or -1

### LazyPropertyUsingDelegates

#### solution

    class LazyPropertyUsingDelegates(val initializer: () -> Int) {
        val lazyValue: Int by lazy { initializer() }
    }

#### resolution

    class LazyPropertyUsingDelegates(val initializer: () -> Int) {
        val lazyValue: Int by lazy(initializer)
    }

Do not wrap a lone function call in a lambda if the call and lambda have same arguments. No arguments in this case.

