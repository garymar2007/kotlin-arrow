package org.gary

import arrow.core.Either
import arrow.core.left
import arrow.core.raise.*
import io.kotest.assertions.fail
import io.kotest.matchers.shouldBe

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {


}

data class UserNotFound(val message: String)
object UserAlreadyExists

data class User(val id: Long)

//extension function
fun User.isValid() : Either<UserNotFound, Unit> = either {
    ensure(id > 0) { UserNotFound("User not found with id: ${id}") }
}

//context parameter
//context(Raise<UserNotFound>)
//fun User.isValid() : Unit =
//    ensure(id > 0) { UserNotFound("User not found with id: ${id}") }


fun Raise<UserNotFound>.isValid(user: User): User {
    ensure(user.id > 0) { UserNotFound("User not found with id: ${user.id}") }
    return user
}

fun example() {
    User(-1).isValid() shouldBe UserNotFound("User not found with id: -1").left()

    fold (
        { isValid(User(1)) },
        { _: UserNotFound -> fail("No logical failure occurred!") },
        { user: User -> user.id shouldBe 1 }
    )
}

fun process(user: User?): Either<UserNotFound, Long> = either {
    ensureNotNull(user) { UserNotFound("Cannot process null user") }
    user.id
}

fun Raise<UserNotFound>.process(user: User?): Long {
    ensureNotNull(user) { UserNotFound("Cannot process null user") }
    return user.id
}

fun example1() {
    process(null) shouldBe UserNotFound("Cannot process null user").left()

    fold(
        { process(User(1)) },
        { _: UserNotFound -> fail("No logical failure occurred!") },
        { i: Long -> i shouldBe 1L }
    )
}

