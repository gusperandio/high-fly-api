package br.pucpr.HighFlyAPI.users

import br.pucpr.HighFlyAPI.role.Role
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "users")
class User(
    @Id @GeneratedValue
    var id: Long? = null,

    @Column(unique = true, nullable = false)
    var email: String = "",

    @NotNull
    var password: String = "",

    @NotNull
    var name: String = "",

    @ManyToMany
    @JoinTable(
        name = "UserRole",
        joinColumns = [JoinColumn(name = "idUser", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "idRole", referencedColumnName = "id")]
    )
    val roles: MutableSet<Role> = mutableSetOf()
)