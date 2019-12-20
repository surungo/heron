/*
 * Copyright (c) 1995 - 2008 Sun Microsystems, Inc.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Sun Microsystems nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;

/**
 * Demonstrates how to rename an object.
 *
 * usage: java Rename
 */

class RenameAD {

	private final static String MASTER_USER = "portoalegre\\svc_criausuario";
	private final static String MASTER_PASS = "@trSenha365";

	public static void main(String[] args) {


//		portoalegre.pucrsnet.br/Acad/Alunos Emails/Rafaela Viecelli

//		portoalegre.pucrsnet.br/Acad/Escola EN/ACE/Alunos/RAFAELA VIECELLI74


		//String ou = "OU=GERAL,OU=Visitantes,DC=portoalegre,DC=pucrsnet,DC=br";

		// Set up the environment for creating the initial context
		Hashtable<String, Object> env = new Hashtable<String, Object>(11);
		//"LDAPS://poa01dcp03.portoalegre.pucrsnet.br:636";
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		//env.put(Context.PROVIDER_URL, "ldap://localhost:389/ou=People,o=JNDITutorial");
		//env.put(Context.PROVIDER_URL, "LDAPS://poa01dcp03.portoalegre.pucrsnet.br:636/"+ou);
		env.put(Context.PROVIDER_URL, "LDAPS://poa01dcp03.portoalegre.pucrsnet.br:636");
		//CN=teste123,OU=GERAL,OU=Visitantes,DC=portoalegre,DC=pucrsnet,DC=br


		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, MASTER_USER);
		env.put(Context.SECURITY_CREDENTIALS, MASTER_PASS);


		try {

			//String d = "CN=RAFAELA VIECELLI74,OU=Alunos,OU=ACE,OU=Escola EN,OU=Acad,DC=portoalegre,DC=pucrsnet,DC=br";
			String d = "CN=RAFAELA.VIECELLI74,OU=Alunos Emails,OU=Acad,DC=portoalegre,DC=pucrsnet,DC=br";
			String p = "CN=RAFAELA VIECELLI74,OU=Alunos Emails,OU=Acad,DC=portoalegre,DC=pucrsnet,DC=br";


			/*
			String d = "teste123";
			String p = "teste321";
			if(2==1) {
				p = "teste123";
				d = "teste321";
			}
			d += ","+ou;
			p += ","+ou;
			 */

			Properties properties;
			DirContext dirContext;

			dirContext = null;

			properties = new Properties();

			properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			//properties.put(Context.PROVIDER_URL, "LDAPS://portoalegre.pucrsnet.br:636");
			properties.put(Context.PROVIDER_URL, "LDAPS://poa01dcp03.portoalegre.pucrsnet.br:636");
			properties.put(Context.SECURITY_PROTOCOL, "ssl");
			properties.put(Context.SECURITY_AUTHENTICATION, "simple");
			properties.put(Context.SECURITY_PRINCIPAL, MASTER_USER);
			properties.put(Context.SECURITY_CREDENTIALS, MASTER_PASS);
/*
			try {
				dirContext = new InitialDirContext(properties);
			} catch (NamingException e) {
			} catch (Exception e) {
			}


			ModificationItem modifyUserMail[];
			modifyUserMail = new ModificationItem[2];
			modifyUserMail[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("cn", p));
			modifyUserMail[1] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("sAMAccountName", p));

			dirContext.modifyAttributes(p, modifyUserMail);
*/


			// Create the initial context
//			Context ctx = new InitialContext(env);
			Context ctx = new InitialContext(properties);



			Object obj = ctx.lookup(d);

			System.out.println(obj.toString());

			// Rename to Scott S
			ctx.rename(d, p);

			// Check that it is there using new name
			obj = ctx.lookup(p);
			System.out.println(obj);


/*
			// Rename back to Scott Seligman
			ctx.rename("CN=teste321", "CN=teste123");

			// Check that it is there with original name
			obj = ctx.lookup("CN=teste123");
			System.out.println(obj);
*/
			// Close the context when we're done




			ctx.close();




		} catch (NamingException e) {
			System.out.println("Rename failed: " + e);
		}
	}


}