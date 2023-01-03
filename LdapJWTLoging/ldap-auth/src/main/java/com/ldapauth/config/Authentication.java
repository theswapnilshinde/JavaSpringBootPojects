package com.ldapauth.config;

import org.springframework.context.annotation.Configuration;

import com.ldapauth.common.dto.UserResData;

import lombok.extern.slf4j.Slf4j;

import javax.naming.*;
import javax.naming.directory.*;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Configuration
/*Check The User is valid or not*/
public class Authentication {

    // boolean function to test user and password
    public boolean userVerify(String uname, String passwd) throws Exception {
        /*
         * System.out.println(uname); System.out.println(passwd);
         */
        boolean userVerify = true;
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:11389/dc=springframework,dc=org");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "uid=bob,ou=people,dc=springframework,dc=org");
        env.put(Context.SECURITY_CREDENTIALS, passwd);

        try {
            DirContext ctx = new InitialDirContext(env);
         // System.out.println("connected");
            log.info("Environment {} ",ctx.getEnvironment());
            userVerify = false;
            ctx.close();

        } catch (AuthenticationNotSupportedException ex) {
        	log.info("The authentication is not supported by the server");
        } catch (AuthenticationException ex) {
        	log.info("incorrect password or username");
        	log.info(ex.getLocalizedMessage());
            throw new AuthenticationException();
        } catch (NamingException ex) {
        	log.info("error when trying to create the context");
        }
        return userVerify;
    }

    public UserResData getUser(String uname) throws Exception {
        /*
         * System.out.println(uname); System.out.println(passwd);
         */
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:11389/dc=springframework,dc=org");

        try {
            DirContext ctx = new InitialDirContext(env);
      //**      System.out.println("connected");

            List<String> list = new LinkedList<String>();
            NamingEnumeration results = null;

            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            results = ctx.search("", "(uid=" + uname+")", controls);

            while (results.hasMore()) {
                SearchResult searchResult = (SearchResult) results.next();
                Attributes attributes = searchResult.getAttributes();
                String roleName = searchResult.getName();
                String ldapPassword = new String((byte[]) attributes.get("userPassword").get());

         //**       System.out.println(ldapPassword);
                list.add(ldapPassword);
                list.add(roleName);
            }
           // System.out.println(list); // commented by **
            ctx.close();
            if (list.size() ==2) {
                // more than one is error case
            	UserResData userData = new UserResData();
                userData.setUserName(uname);
                userData.setRoleName(list.get(1));
                userData.setPassword(list.get(0));
                return userData;
            }
        } catch (AuthenticationNotSupportedException ex) {
        	log.info("The authentication is not supported by the server");
        } catch (AuthenticationException ex) {
        	log.info("incorrect password or username");
        	log.info(ex.getLocalizedMessage());
            throw new AuthenticationException();
        } catch (NamingException ex) {
        	log.info("error when trying to create the context");
        }
        return null;
    }
}
