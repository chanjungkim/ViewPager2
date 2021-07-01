package io.github.chanjungkim

data class MyItem(
    val color: Int,
    var isFocused: Boolean
){
    override fun toString(): String {
        return "MyItem(color=$color, isFocused=$isFocused)"
    }
}
