package io.github.chrislo27.rhre3.sfxdb

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonValue
import io.github.chrislo27.rhre3.sfxdb.Language.Companion.VALID_VALUES

enum class StretchBehavior(@get:JsonValue val jsonName: String, @JsonIgnore val properName: String) {

    STAY("stay", "Entity stays in place"),
    EXTEND("extend", "Entity extends with end"),
    MOVE("move", "Entity moves with end");

    companion object {
        val VALUES = values().toList()

        @JvmStatic
        @JsonCreator
        fun getEnumFromJsonName(n: String): StretchBehavior = VALUES.first { n.toLowerCase() == it.jsonName.toLowerCase() }
        val CODE_MAP: Map<String, Language> = VALID_VALUES.associateBy { it.code }
    }
}
