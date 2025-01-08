package com.kjw.ecommerce.jpa.repository.user;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.kjw.ecommerce.jpa.entity.user.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("[단위] - UserRepository 테스트")
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	@DisplayName("[단위] - UserRepository.findById(String id) 성공")
	void testFindByIdSuccess() {

		// given
		String userId = "wjddn312";

		// when
		Optional<User> user = userRepository.findById(userId);

		// then
		assertThat(user).isPresent();

	}

	@Test
	@DisplayName("[단위] - UserRepository.findById(String id) 실패")
	void testFindByIdFail() {

		// given
		String userId = "test01";

		//when
		Optional<User> user = userRepository.findById(userId);

		// then
		assertThat(user).isEmpty();

	}


}
