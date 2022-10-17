package com.kkulpa.moviesservice.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.kkulpa.moviesservice.security.ApplicationUserPermission.LOGIN_DETAILS_WRITE;
import static com.kkulpa.moviesservice.security.ApplicationUserPermission.USER_DETAILS_WRITE;

public enum ApplicationUserRole {
    USER(Sets.newHashSet(   LOGIN_DETAILS_WRITE,
                            USER_DETAILS_WRITE
                        ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
