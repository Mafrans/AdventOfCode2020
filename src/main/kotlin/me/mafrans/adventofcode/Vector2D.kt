package me.mafrans.adventofcode

class Vector2D<out TX : Number, out TY: Number>(val x: TX, val y: TY) : Cloneable {

    operator fun plus(other: Vector2D<Number, Number>) = Vector2D((x.toDouble() + other.x.toDouble()) as TX, (y.toDouble() + other.y.toDouble()) as TY)
    operator fun minus(other: Vector2D<Number, Number>) = Vector2D((x.toDouble() - other.x.toDouble()) as TX, (y.toDouble() - other.y.toDouble()) as TY)

    operator fun times(other: Vector2D<Number, Number>) = Vector2D((x.toDouble() * other.x.toDouble()) as TX, (y.toDouble() * other.y.toDouble()) as TY)
    operator fun times(other: Number) = Vector2D((x.toDouble() * other.toDouble()) as TX, (y.toDouble() * other.toDouble()) as TY)

    operator fun div(other: Vector2D<Number, Number>) = Vector2D((x.toDouble() / other.x.toDouble()) as TX, (y.toDouble() / other.y.toDouble()) as TY)
    operator fun div(other: Number) = Vector2D((x.toDouble() / other.toDouble()) as TX, (y.toDouble() / other.toDouble()) as TY)

    operator fun unaryMinus() = Vector2D(-x.toDouble() as TX, -y.toDouble() as TY);

    operator fun inc() = Vector2D((x.toDouble() + 1) as TX, (y.toDouble() + 1) as TY)
    operator fun dec() = Vector2D((x.toDouble() - 1) as TX, (y.toDouble() - 1) as TY)

    operator fun set(x: Number, y: Number, other: Vector2D<Number, Number>) = Vector2D(x, y);

    fun swap() = Vector2D(y, x);

    override fun equals(other: Any?): Boolean = other is Vector2D<Number, Number> && other.x == x && other.y == y
    override fun toString(): String = "($x, $y)"
}