
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.raghu.entity.User;
import com.app.raghu.repository.UserRepository;
import com.app.raghu.service.IUserService;


// public enum ERole {
// 	ROLE_USER, ROLE_MODERATOR, ROLE_ADMIN
// }

// @Entity
// @Table(name = "roles")
// @Data
// public class Role {
// 	@Id
// 	@GeneratedValue(strategy = GenerationType.IDENTITY)
// 	private Integer id;
// 	@Enumerated(EnumType.STRING)
// 	@Column(length = 20)
// 	private ERole name;
// }		

// @Entity
// @Table(name="app_user_tab")
// @Data
// public class User {
// 	@Id
// 	@GeneratedValue
// 	private Integer id;
// 	private String name;
// 	private String username;
// 	private String password;
// 	@ElementCollection(fetch = FetchType.EAGER)
// @CollectionTable(
//     name="rolestab",
//     joinColumns = @JoinColumn(name="id")
//     )
// 	private List<String> roles;
// }


@Service
public class UserServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;

	public Integer saveUser(User user) {
		user.setPassword(pwdEncoder.encode(user.getPassword()));
		return repository.save(user).getId();
	}

	public User findByUsername(String username) {
		Optional<User> user = repository.findByUsername(username);
		if (user.isPresent())
			return user.get();
		return null;
	}

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException(
					new StringBuffer().append("User name ").append(username).append(" not found!").toString());

		List<GrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
	}

}