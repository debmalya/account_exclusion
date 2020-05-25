package org.deb.account.exclusion.enums;

public enum ActionOnAccount {

  ADD("add"),
  REMOVE("remove");

  private String action;
  ActionOnAccount(String action){
    this.action = action;
  }
}
