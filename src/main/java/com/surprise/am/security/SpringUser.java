package com.surprise.am.security;


import com.surprise.am.model.User;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * Created by stella on 3/18/19.
 */
public class SpringUser extends org.springframework.security.core.userdetails.User {

  private User user;

  public SpringUser(User user) {
    super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getGender().name()));
    this.user = user;
  }

  public User getUser() {
    return user;
  }

}
//