package br.com.fiap.fintech.util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Classe utilitária para realizar operações de criptografia utilizando o algoritmo MD5.
 */

public class CriptografiaUtils {

	public static String criptografar(String senha) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(senha.getBytes("ISO-8859-1"));
		BigInteger hash = new BigInteger(1, md.digest());
		return hash.toString(16);
	}
}
