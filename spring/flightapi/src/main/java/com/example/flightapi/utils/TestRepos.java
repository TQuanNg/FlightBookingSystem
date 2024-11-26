package com.example.flightapi.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepos extends JpaRepository<TestEntity, Long> {
}
