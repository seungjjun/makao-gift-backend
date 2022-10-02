package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.models.User;

public class UserService {
  public User detail(String id) {
    return new User(id, "", 0L);
  }
}
