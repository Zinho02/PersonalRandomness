"""
A simple shift-cipher implementation for educative purposes.

Author: Alek Frohlich, Date: 15/07/2019.
"""

from os import urandom


def generateKey(symbol_size=1):
    """
    Generates a symmetric key using the secure os.urandom() as it's generator.
    
    Params:
        symbol_size: The size of a symbol in bytes.

    Returns:
        The Newly generated key.
    """

    return int.from_bytes(urandom(symbol_size), byteorder='big')


class ShiftChiper:
    
    def __init__(self, key=generateKey(), num_symbols=256):
        """
        ShiftChiper's Constructor.

        Params:
            key: The symmetric key to be used for encryption/decryption. 
            num_symbols: The number of symbols supported by the encoding format. 
        """

        self.key = key 
        self.NUM_SYMBOLS = num_symbols


    def encrypt(self, data):
        """
        Encrypts the 'data' using the key provided as parameter to the constructor.

        Params:
            data: The data to be encrypted.

        Returns:
            The encrypted data.
        """

        encrypted = list(data)
        for i in range(len(encrypted)):
            encrypted[i] = chr((ord(data[i]) + self.key) % self.NUM_SYMBOLS)
        return str(''.join(encrypted))


    def decrypt(self, data):
        """
        Decrypts the 'data' using the key reverse-map of the key provided as parameter
        to the constructor.

        Params:
            data: The data to be decrypted.

        Returns:
            The decrypted data.
        """

        decrypted = ''
        for i in range(len(data)):
            decrypted += chr((ord(data[i]) - self.key) % self.NUM_SYMBOLS)
        return decrypted


if __name__ == '__main__':
    message = input()
    scipher = ShiftChiper()
    encrypted_message = scipher.encrypt(message)
    print(encrypted_message)
    print(scipher.decrypt(encrypted_message))
