package com.iyxan23.netpen.helpers

import android.util.Base64
import java.math.BigInteger
import java.nio.charset.StandardCharsets
import java.security.*
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.RSAPublicKeySpec
import javax.crypto.Cipher

object RSAHelper {
    // https://gist.github.com/nielsutrecht/855f3bef0cf559d8d23e94e2aecd4ede
    fun generateKeyPair(keySize: Int = 2048): KeyPair {
        val generator: KeyPairGenerator = KeyPairGenerator.getInstance("RSA")
        generator.initialize(keySize, SecureRandom())
        return generator.generateKeyPair()
    }

    // https://stackoverflow.com/a/21606909/9613353
    fun serializePublicKey(publicKey: RSAPublicKey): String = "${publicKey.modulus}|${publicKey.publicExponent}"

    fun deserializePublicKey(publicKey: String): RSAPublicKey? {
        val parts = publicKey.split("|")
        val spec = RSAPublicKeySpec(
            BigInteger(parts[0]),
            BigInteger(parts[1])
        )

        return KeyFactory.getInstance("RSA").generatePublic(spec) as RSAPublicKey?
    }

    fun encrypt(plainText: String, publicKey: RSAPublicKey?): String {
        val encryptCipher: Cipher = Cipher.getInstance("RSA")
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey)

        val cipherText: ByteArray = encryptCipher.doFinal(plainText.toByteArray(StandardCharsets.UTF_8))
        return Base64.encode(cipherText, Base64.DEFAULT).toString()
    }

    fun decrypt(cipherText: String?, privateKey: RSAPrivateKey?): String {
        val bytes: ByteArray = Base64.decode(cipherText, Base64.DEFAULT)
        val decryptCipher = Cipher.getInstance("RSA")
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey)

        return String(decryptCipher.doFinal(bytes), StandardCharsets.UTF_8)
    }
}