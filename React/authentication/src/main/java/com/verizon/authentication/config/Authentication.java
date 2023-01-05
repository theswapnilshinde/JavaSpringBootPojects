package com.verizon.authentication.config;

import com.verizon.authentication.model.UserData;
import org.springframework.context.annotation.Configuration;

import javax.naming.*;
import javax.naming.directory.*;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

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
        env.put(Context.PROVIDER_URL, "ldap://localhost:2389/dc=springframework,dc=org");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "uid=bob,ou=people,dc=springframework,dc=org");
        env.put(Context.SECURITY_CREDENTIALS, "bobspassword");

        try {
            DirContext ctx = new InitialDirContext(env);
         // System.out.println("connected");
            System.out.println(ctx.getEnvironment());
            userVerify = false;
            ctx.close();

        } catch (AuthenticationNotSupportedException ex) {
            System.out.println("The authentication is not supported by the server");
        } catch (AuthenticationException ex) {
            System.out.println("incorrect password or username");
            System.out.println(ex.getLocalizedMessage());
            throw new AuthenticationException();
        } catch (NamingException ex) {
            System.out.println("error when trying to create the context");
        }
        return userVerify;
    }

    public UserData getUser(String uname) throws Exception {
        /*
         * System.out.println(uname); System.out.println(passwd);
         */
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://localhost:2389/dc=springframework,dc=org");

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
                UserData userData = new UserData();
                userData.setUserName(uname);
                UserData.roleName = list.get(1);
                userData.setPassword(list.get(0));
                return userData;
            }
        } catch (AuthenticationNotSupportedException ex) {
            System.out.println("The authentication is not supported by the server");
        } catch (AuthenticationException ex) {
            System.out.println("incorrect password or username");
            System.out.println(ex.getLocalizedMessage());
            throw new AuthenticationException();
        } catch (NamingException ex) {
            System.out.println("error when trying to create the context");
        }
        return null;
    }
}