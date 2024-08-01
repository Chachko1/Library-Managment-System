package org.librarymanagementsystem.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.librarymanagementsystem.Enums.RoleEnum;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member rolesAddedBy;

    public Role() {}

    public Member getRolesAddedBy() {
        return rolesAddedBy;
    }

    public void setRolesAddedBy(Member rolesAddedBy) {
        this.rolesAddedBy = rolesAddedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }
}