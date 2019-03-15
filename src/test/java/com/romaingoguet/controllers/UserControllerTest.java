package com.romaingoguet.controllers;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@Transactional
@Rollback
@ActiveProfiles("test")
public class UserControllerTest {

}
