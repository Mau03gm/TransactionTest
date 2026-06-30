import CryptoJS from 'crypto-js'

export function encryptAes256Ecb(plaintext: string): string {
  const secretKey = import.meta.env.VITE_AES_SECRET_KEY

  if (!secretKey || secretKey.length !== 32) {
    throw new Error('VITE_AES_SECRET_KEY debe tener exactamente 32 caracteres')
  }

  const key = CryptoJS.enc.Utf8.parse(secretKey)
  const encrypted = CryptoJS.AES.encrypt(plaintext, key, {
    mode: CryptoJS.mode.ECB,
    padding: CryptoJS.pad.Pkcs7,
  })

  return encrypted.ciphertext.toString(CryptoJS.enc.Base64)
}
