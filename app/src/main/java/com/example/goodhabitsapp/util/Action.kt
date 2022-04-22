package com.example.goodhabitsapp.util

enum class Action {
    ADD,
    UPDATE,
    DELETE,
    DELETE_ALL,
    UNDO,
    NO_ACTION,
    COMPLETED,
    NOT_COMPLETED
}

fun String?.toAction(): Action {
    return when {
        this == "ADD" -> {
            Action.ADD
        }
        this == "UPDATE" -> {
            Action.UPDATE
        }
        this == "DELETE" -> {
            Action.DELETE
        }
        this == "DELETE_ALL" -> {
            Action.DELETE_ALL
        }
        this == "UNDO" -> {
            Action.UNDO
        }
        this == "COMPLETED" -> {
            Action.COMPLETED
        }
        this == "NOT_COMPLETED" -> {
            Action.NOT_COMPLETED
        }
        else -> {
            Action.NO_ACTION
        }
    }
}