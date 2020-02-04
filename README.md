# file-reader
This is design of a generic File based reader which supports reading data and handles encoding, decryption & decompression based on user configuration.

This project is written in core java without using any frameworks.

## Taking file properties as input
### CharSet :

Charset with which the file data (binary) is encoded with. For example ISO-8859-1 is used to convert ISO Latin Alphabet string to binary for writing into file.

In case if this property is not specified then the bytes (Update: File is encoded with UTF-8) are encoded with UTF-8 is considered.

### Encryption :

AES Encryption algorithm is used for encrypting the data before writing to file. If not specified, then there is no encryption and the data will be persisted as plain text.

### Compression:

Compression logic is used to compress the data before writing to file. Possible values are gzip, (yet to implement bzip, bz etc). In case is user has not specified, then the data is uncompressed.

Classes are designed with respective methods to read files and perform the operation specified and return the plain text to the caller.

Also unit tests are covered as well.


## Architecturing the file reader

The project directory:
![](docs/images/Overall.png)

