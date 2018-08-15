package ii_collections

fun example4() {
    val max = listOf(1, 42, 4).max()
    val longestString = listOf("a", "b").maxBy { it.length }
}

fun Shop.getCustomerWithMaximumNumberOfOrders(): Customer? {
    // Return a customer whose order count is the highest among all customers
    return this.customers.sortedByDescending { customer -> customer.orders.count() }.firstOrNull()
}

fun Customer.getMostExpensiveOrderedProduct(): Product? {
    // Return the most expensive product which has been ordered
    return this.orders.flatMap { order -> order.products }.sortedByDescending { product -> product.price }.firstOrNull()
}
