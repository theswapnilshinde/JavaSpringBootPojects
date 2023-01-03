package com.example.LoginLdapserver.repo;

import org.springframework.data.ldap.repository.LdapRepository;

import org.springframework.stereotype.Repository;

import com.example.LoginLdapserver.Person;

@Repository
public interface Repo extends LdapRepository<Person>,RepoAdditional{

}
