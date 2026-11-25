package io.github.chrislo27.rhre3.sfxdb

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonValue

enum class CuePickerBehavior(@get:JsonValue val jsonName: String, @JsonIgnore val properName: String) {

    DEFAULT("default", "<automatic>"),
    HIDE("hide", "Hide from the cue picker"),
    ABOVE("above", "Sort above others"),
    BELOW("below", "Sort below others");

    companion object {
        val VALUES = values().toList()

        @JvmStatic
        @JsonCreator
        fun getEnumFromJsonName(n: String): CuePickerBehavior = VALUES.first { n.equals(it.jsonName, ignoreCase = true) }
    }
}
