package com.example.dash.repository;

import com.example.dash.model.Admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {

    Admin findByUsername(String username);
}
