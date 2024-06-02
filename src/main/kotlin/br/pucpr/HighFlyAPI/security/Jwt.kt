package br.pucpr.HighFlyAPI.security

import br.pucpr.HighFlyAPI.users.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.jackson.io.JacksonSerializer
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.Date

@Component
class Jwt {
    fun createToken(user: User): String =
        UserToken(user).let {
            Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(SECRET.toByteArray())) //CREATE TOKEN
                .json(JacksonSerializer()) //SERIALIZER
                .issuedAt(utcNow().toDate()) //TOKEN WITH TIME NOW
                .expiration(
                    utcNow().plusHours(if (it.isAdmin) ADMIN_EXPIRE_HOURS else EXPIRE_HOURS).toDate()
                ) //Expire Date TOKEN
                .issuer(ISSUER) //! Server name
                .subject(user.id.toString()) //The object how we appoint to discovery who is this guy
                .claim(USER_FIELD, it) //Bonus Information
                .compact() //GENERATE THE TEXT
        }

    companion object {
        const val SECRET = "35981FB176D6262784986ED376677"
        const val EXPIRE_HOURS = 1L;
        const val ADMIN_EXPIRE_HOURS = 12L;
        const val ISSUER = "High-Fly"
        const val USER_FIELD = "user"

        private fun utcNow() = ZonedDateTime.now(ZoneOffset.UTC)
        private fun ZonedDateTime.toDate(): Date = Date.from(this.toInstant())
    }
}
