package com.example.flightapi.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private TestRepos testRepos;

    public TestEntity createTestEntity(String name) {
        TestEntity entity = new TestEntity();
        entity.setName(name);
        System.out.println("Example Utility is running...\n\n\n\n\n");
        return testRepos.save(entity);
    }
}
