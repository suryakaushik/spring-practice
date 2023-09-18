package io.javabrains.springsecurityjpa;

import io.javabrains.springsecurityjpa.models.MyUserDetails;

import io.javabrains.springsecurityjpa.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

        return user.map(MyUserDetails::new).get();
    }
}


// public class User {
// 	@Id
// 	private Integer usrId;
// 	@Column(name="uname")
// 	private String usrName;
// 	@Column(name="uemail")
// 	private String usrMail;
// 	@Column(name="upwd")
// 	private String usrPwd;

// 	@ElementCollection(fetch = FetchType.EAGER)
// 	@CollectionTable(name="rolestab",
// 	joinColumns = @JoinColumn(name="uid"))
// 	@Column(name="urole")
// 	private Set<String> usrRoles;
// }


// Below is the custom implementation of service for above user
// @Service
// public class UserServiceImpl implements IUserService, UserDetailsService
// {
// 	@Autowired
// 	private BCryptPasswordEncoder encoder;
	
// 	@Autowired
// 	private UserRepository repo; //HAS-A
	
// 	public Integer saveUser(User user) {
// 		//read Register Form Password  and encode
// 		String encPwd = encoder.encode(user.getUsrPwd());
// 		//set it back to object
// 		user.setUsrPwd(encPwd);
// 		user = repo.save(user);
// 		return user.getUsrId();
// 	}
	
// 	@Override
// 	public Optional<User> findByEmai(String email) {
// 		return repo.findByUsrMail(email);
// 	}
    
// 	public UserDetails loadUserByUsername(String username)
// 			throws UsernameNotFoundException 
// 	{
// 		//fetch obj by email
// 		Optional<User> opt =  repo.findByUsrMail(username);
// 		if(opt.isEmpty()) {
// 			throw new UsernameNotFoundException("Not exist");
// 		} else {
// 			User user = opt.get();
// 			return new org.springframework.security.core.userdetails
// 					.User(
// 							username, 
// 							user.getUsrPwd(),
// 							user.getUsrRoles()
// 							.stream()
// 							.map(role->new SimpleGrantedAuthority(role))
// 							.collect(Collectors.toSet())
// 							);
// 		}
// 	}
// }