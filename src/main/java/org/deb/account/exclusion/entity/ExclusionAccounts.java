package org.deb.account.exclusion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
public class ExclusionAccounts {
    @Id
    private String accountNumber;

    public ExclusionAccounts(){

    }
}
