package com.svl.servicebase.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public enum Role {
    User, Investor, Admin, Senior, Boss
}
