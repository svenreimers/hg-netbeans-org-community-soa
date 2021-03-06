/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.netbeans.org/cddl.html
 * or http://www.netbeans.org/cddl.txt.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://www.netbeans.org/cddl.txt.
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.netbeans.modules.soa.jca.base;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.InputStream;

/**
 * Copied Base64 utility class from Hawaii eways common codebase
 *
 * @author echou
 */
public class Base64 {
    /** Symbol that represents the end of an input stream */
    private static final int END_OF_INPUT = -1;
    /** A character that is not a valid base 64 character. */
    private static final int NON_BASE_64 = -1;
    /** A character that is not a valid base 64 character. */
    private static final int NON_BASE_64_WHITESPACE = -2;
    /** A character that is not a valid base 64 character. */
    private static final int NON_BASE_64_PADDING = -3;
    /**
     * Table of the sixty-four characters that are used as the Base64 alphabet:
     * [A-Za-z0-9+/]
     */
    protected static final byte[] BASE64CARS = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
        'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b',
        'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
        'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
        '4', '5', '6', '7', '8', '9', '+', '/',
    };
    /**
     * Reverse lookup table for the Base64 alphabet. reversebase64Chars[byte]
     * gives n for the nth Base64 character or negative if a character is not
     * a Base64 character.
     */
    protected static final byte[] REVERSEBASE64CHARS = new byte[0x100];

    static {
        // Fill in NON_BASE_64 for all characters to start with
        for (int i = 0; i < REVERSEBASE64CHARS.length; i++) {
            REVERSEBASE64CHARS[i] = NON_BASE_64;
        }

        // For characters that are BASE64CARS, adjust
        // the reverse lookup table.
        for (byte i = 0; i < BASE64CARS.length; i++) {
            REVERSEBASE64CHARS[BASE64CARS[i]] = i;
        }

        REVERSEBASE64CHARS[' '] = NON_BASE_64_WHITESPACE;
        REVERSEBASE64CHARS['\n'] = NON_BASE_64_WHITESPACE;
        REVERSEBASE64CHARS['\r'] = NON_BASE_64_WHITESPACE;
        REVERSEBASE64CHARS['\t'] = NON_BASE_64_WHITESPACE;
        REVERSEBASE64CHARS['\f'] = NON_BASE_64_WHITESPACE;
        REVERSEBASE64CHARS['='] = NON_BASE_64_PADDING;
    }

    /**
     * Encode a String in Base64. The String is converted to and from bytes
     * according to the platform's default character encoding. No line breaks
     * or other white space are inserted into the encoded data.
     *
     * @param string The data to encode.
     *
     * @return An encoded String.
     */
   public static String encode(String string) throws UnsupportedEncodingException {
        //return new String(encode(string.getBytes("ISO-8859-1")));
       return new String(encode(string.getBytes("UTF-8")));
    }

    /**
     * Encode a String in Base64. No line breaks or other white space are
     * inserted into the encoded data.
     *
     * @param string The data to encode.
     * @param enc Character encoding to use when converting to and from bytes.
     *
     * @return An encoded String.
     *
     * @throws UnsupportedEncodingException if the character encoding specified
     *         is not supported.
     */
    public static String encode(String string, String enc)
        throws UnsupportedEncodingException {
        return new String(
            encode(string.getBytes(enc)),
            enc);
    }

    /**
     * Encode bytes in Base64. No line breaks or other white space are inserted
     * into the encoded data.
     *
     * @param bytes The data to encode.
     *
     * @return Encoded bytes.
     */
    public static byte[] encode(byte[] bytes) {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);

        // calculate the length of the resulting output.
        // in general it will be 4/3 the size of the input
        // but the input length must be divisible by three.
        // If it isn't the next largest size that is divisible
        // by three is used.
        int mod;
        int length = bytes.length;

        if ((mod = length % 3) != 0) {
            length += (3 - mod);
        }

        length = (length * 4) / 3;

        ByteArrayOutputStream out = new ByteArrayOutputStream(length);

        try {
            encode(
                in,
                out,
                false);
        } catch (IOException x) {
            // This can't happen.
            // The input and output streams were constructed
            // on memory structures that don't actually use IO.
        }

        return out.toByteArray();
    }

    /**
     * Encode data from the InputStream to the OutputStream in Base64.
     *
     * @param in Stream from which to read data that needs to be encoded.
     * @param out Stream to which to write encoded data.
     * @param lineBreaks Whether to insert line breaks every 76 characters in
     *        the output.
     *
     * @throws IOException if there is a problem reading or writing.
     */
    public static void encode(InputStream in, OutputStream out,
        boolean lineBreaks)
        throws IOException {
        // Base64 encoding converts three bytes of input to
        // four bytes of output
        int[] inBuffer = new int[3];
        int lineCount = 0;

        boolean done = false;

        while (!done && ((inBuffer[0] = in.read()) != END_OF_INPUT)) {
            // Fill the buffer
            inBuffer[1] = in.read();
            inBuffer[2] = in.read();

            // Calculate the out Buffer
            // The first byte of our in buffer will always be valid
            // but we must check to make sure the other two bytes
            // are not END_OF_INPUT before using them.
            // The basic idea is that the three bytes get split into
            // four bytes along these lines:
            //      [AAAAAABB] [BBBBCCCC] [CCDDDDDD]
            // [xxAAAAAA] [xxBBBBBB] [xxCCCCCC] [xxDDDDDD]
            // bytes are considered to be zero when absent.
            // the four bytes are then mapped to common ASCII symbols
            // A's: first six bits of first byte
            out.write(BASE64CARS[inBuffer[0] >> 2]);

            if (inBuffer[1] != END_OF_INPUT) {
                // B's: last two bits of first byte, first four bits of second byte
                out.write(
                    BASE64CARS[((inBuffer[0] << 4) & 0x30)
                    | (inBuffer[1] >> 4)]);

                if (inBuffer[2] != END_OF_INPUT) {
                    // C's: last four bits of second byte, first two bits of third byte
                    out.write(
                        BASE64CARS[((inBuffer[1] << 2) & 0x3c)
                        | (inBuffer[2] >> 6)]);
                    // D's: last six bits of third byte
                    out.write(BASE64CARS[inBuffer[2] & 0x3F]);
                } else {
                    // C's: last four bits of second byte
                    out.write(BASE64CARS[((inBuffer[1] << 2) & 0x3c)]);
                    // an equals sign for a character that is not a Base64 character
                    out.write('=');
                    done = true;
                }
            } else {
                // B's: last two bits of first byte
                out.write(BASE64CARS[((inBuffer[0] << 4) & 0x30)]);
                // an equal signs for characters that is not a Base64 characters
                out.write('=');
                out.write('=');
                done = true;
            }

            lineCount += 4;

            if (lineBreaks && (lineCount >= 76)) {
                out.write('\n');
                lineCount = 0;
            }
        }

        if (lineBreaks && (lineCount >= 1)) {
            out.write('\n');
            lineCount = 0;
        }

        out.flush();
    }

    /**
     * Decode a Base64 encoded String. Characters that are not part of the
     * Base64 alphabet are ignored in the input. The String is converted to
     * and from bytes according to the platform's default character encoding.
     *
     * @param string The data to decode.
     *
     * @return A decoded String.
     */
    public static String decode(String string) throws UnsupportedEncodingException {
        //return new String(decode(string.getBytes("ISO-8859-1")));
        return new String(decode(string.getBytes("UTF-8")));
    }

    /**
     * Decode a Base64 encoded String. Characters that are not part of the
     * Base64 alphabet are ignored in the input.
     *
     * @param string The data to decode.
     * @param enc Character encoding to use when converting to and from bytes.
     *
     * @return A decoded String.
     *
     * @throws UnsupportedEncodingException if the character encoding specified
     *         is not supported.
     */
    public static String decode(String string, String enc)
        throws UnsupportedEncodingException {
        return new String(
            decode(string.getBytes(enc)),
            enc);
    }

    /**
     * Decode Base64 encoded bytes. Characters that are not part of the Base64
     * alphabet are ignored in the input.
     *
     * @param bytes The data to decode.
     *
     * @return Decoded bytes.
     */
    public static byte[] decode(byte[] bytes) {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);

        // calculate the length of the resulting output.
        // in general it will be at most 3/4 the size of the input
        // but the input length must be divisible by four.
        // If it isn't the next largest size that is divisible
        // by four is used.
        int mod;
        int length = bytes.length;

        if ((mod = length % 4) != 0) {
            length += (4 - mod);
        }

        length = (length * 3) / 4;

        ByteArrayOutputStream out = new ByteArrayOutputStream(length);

        try {
            decode(
                in,
                out,
                false);
        } catch (IOException x) {
            // This can't happen.
            // The input and output streams were constructed
            // on memory structures that don't actually use IO.
            ;
        }

        return out.toByteArray();
    }

    /**
     * Reads the next (decoded) Base64 character from the input stream. Non
     * Base64 characters are skipped.
     *
     * @param in Stream from which bytes are read.
     * @param throwExceptions Throw an exception if an unexpected character is
     *        encountered.
     *
     * @return the next Base64 character from the stream or -1 if there are no
     *         more Base64 characters on the stream.
     *
     * @throws IOException if an IO Error occurs.
     * @throws Base64DecodingException if unexpected data is encountered when
     *         throwExceptions is specified.
     */
    private static final int readBase64(InputStream in, boolean throwExceptions)
        throws IOException {
        int read;
        int numPadding = 0;

        do {
            read = in.read();

            if (read == END_OF_INPUT) {
                return END_OF_INPUT;
            }

            read = REVERSEBASE64CHARS[(byte) read];

            if (throwExceptions
                    && (
                        (read == NON_BASE_64)
                        || ((numPadding > 0) && (read > NON_BASE_64))
                    )) {
                throw new IOException("ioexception");
            }

            if (read == NON_BASE_64_PADDING) {
                numPadding++;
            }
        } while (read <= NON_BASE_64);

        return read;
    }

    /**
     * Decode Base64 encoded data from the InputStream to the OutputStream.
     * Characters in the Base64 alphabet, white space and equals sign are
     * expected to be in urlencoded data.  The presence of other characters
     * could be a sign that the data is corrupted.
     *
     * @param in Stream from which to read data that needs to be decoded.
     * @param out Stream to which to write decoded data.
     *
     * @throws IOException if an IO error occurs.
     */
    public static void decode(InputStream in, OutputStream out)
        throws IOException {
        decode(
            in,
            out,
            true);
    }

    /**
     * Decode Base64 encoded data from the InputStream to the OutputStream.
     * Characters in the Base64 alphabet, white space and equals sign are
     * expected to be in urlencoded data.  The presence of other characters
     * could be a sign that the data is corrupted.
     *
     * @param in Stream from which to read data that needs to be decoded.
     * @param out Stream to which to write decoded data.
     * @param throwExceptions Whether to throw exceptions when unexpected data
     *        is encountered.
     *
     * @throws IOException if an IO error occurs.
     */
    public static void decode(InputStream in, OutputStream out,
        boolean throwExceptions)
        throws IOException {
        // Base64 decoding converts four bytes of input to three bytes of output
        int[] inBuffer = new int[4];

        // read bytes unmapping them from their ASCII encoding in the process
        // we must read at least two bytes to be able to output anything
        boolean done = false;

        while (!done
                && (
                    (inBuffer[0] = readBase64(
                            in,
                            throwExceptions)) != END_OF_INPUT
                )
                && (
                    (inBuffer[1] = readBase64(
                            in,
                            throwExceptions)) != END_OF_INPUT
                )) {
            // Fill the buffer
            inBuffer[2] = readBase64(
                    in,
                    throwExceptions);
            inBuffer[3] = readBase64(
                    in,
                    throwExceptions);

            // Calculate the output
            // The first two bytes of our in buffer will always be valid
            // but we must check to make sure the other two bytes
            // are not END_OF_INPUT before using them.
            // The basic idea is that the four bytes will get reconstituted
            // into three bytes along these lines:
            // [xxAAAAAA] [xxBBBBBB] [xxCCCCCC] [xxDDDDDD]
            //      [AAAAAABB] [BBBBCCCC] [CCDDDDDD]
            // bytes are considered to be zero when absent.
            // six A and two B
            out.write((inBuffer[0] << 2) | (inBuffer[1] >> 4));

            if (inBuffer[2] != END_OF_INPUT) {
                // four B and four C
                out.write((inBuffer[1] << 4) | (inBuffer[2] >> 2));

                if (inBuffer[3] != END_OF_INPUT) {
                    // two C and six D
                    out.write((inBuffer[2] << 6) | inBuffer[3]);
                } else {
                    done = true;
                }
            } else {
                done = true;
            }
        }

        out.flush();
    }

    /**
     * Determines if the byte array is in base64 format.
     *
     * <p>
     * Data will be considered to be in base64 format if it contains only
     * base64 characters and whitespace with equals sign padding on the end so
     * that the number of base64 characters is divisible by four.
     * </p>
     *
     * <p>
     * It is possible for data to be in base64 format but for it to not meet
     * these stringent requirements.  It is also possible for data to meet
     * these requirements even though decoding it would not make any sense.
     * This method should be used as a guide but it is not authoritative
     * because of the possibility of these false positives and false
     * negatives.
     * </p>
     *
     * <p>
     * Additionally, extra data such as headers or footers may throw this
     * method off the scent and cause it to return false.
     * </p>
     *
     * @param bytes data that could be in base64 format.
     *
     * @return DOCUMENT ME!
     */
    public static boolean isBase64(byte[] bytes) {
        try {
            return isBase64(new ByteArrayInputStream(bytes));
        } catch (IOException x) {
            // This can't happen.
            // The input and output streams were constructed
            // on memory structures that don't actually use IO.
            return false;
        }
    }

    /**
     * Determines if the String is in base64 format. The String is converted to
     * and from bytes according to the platform's default character encoding.
     *
     * <p>
     * Data will be considered to be in base64 format if it contains only
     * base64 characters and whitespace with equals sign padding on the end so
     * that the number of base64 characters is divisible by four.
     * </p>
     *
     * <p>
     * It is possible for data to be in base64 format but for it to not meet
     * these stringent requirements.  It is also possible for data to meet
     * these requirements even though decoding it would not make any sense.
     * This method should be used as a guide but it is not authoritative
     * because of the possibility of these false positives and false
     * negatives.
     * </p>
     *
     * <p>
     * Additionally, extra data such as headers or footers may throw this
     * method off the scent and cause it to return false.
     * </p>
     *
     * @param string String that may be in base64 format.
     *
     * @return Best guess as to whether the data is in base64 format.
     */
    public static boolean isBase64(String string) throws UnsupportedEncodingException {
        //return isBase64(string.getBytes("ISO-8859-1"));
        return isBase64(string.getBytes("UTF-8"));
    }

    /**
     * Determines if the String is in base64 format.
     *
     * <p>
     * Data will be considered to be in base64 format if it contains only
     * base64 characters and whitespace with equals sign padding on the end so
     * that the number of base64 characters is divisible by four.
     * </p>
     *
     * <p>
     * It is possible for data to be in base64 format but for it to not meet
     * these stringent requirements.  It is also possible for data to meet
     * these requirements even though decoding it would not make any sense.
     * This method should be used as a guide but it is not authoritative
     * because of the possibility of these false positives and false
     * negatives.
     * </p>
     *
     * <p>
     * Additionally, extra data such as headers or footers may throw this
     * method off the scent and cause it to return false.
     * </p>
     *
     * @param string String that may be in base64 format.
     * @param enc Character encoding to use when converting to bytes.
     *
     * @return Best guess as to whether the data is in base64 format.
     *
     * @throws UnsupportedEncodingException if the character encoding specified
     *         is not supported.
     */
    public static boolean isBase64(String string, String enc)
        throws UnsupportedEncodingException {
        return isBase64(string.getBytes(enc));
    }


    /**
     * Reads data from the stream and determines if it is in base64 format.
     *
     * <p>
     * Data will be considered to be in base64 format if it contains only
     * base64 characters and whitespace with equals sign padding on the end so
     * that the number of base64 characters is divisible by four.
     * </p>
     *
     * <p>
     * It is possible for data to be in base64 format but for it to not meet
     * these stringent requirements.  It is also possible for data to meet
     * these requirements even though decoding it would not make any sense.
     * This method should be used as a guide but it is not authoritative
     * because of the possibility of these false positives and false
     * negatives.
     * </p>
     *
     * <p>
     * Additionally, extra data such as headers or footers may throw this
     * method off the scent and cause it to return false.
     * </p>
     *
     * @param in Stream from which to read data to be tested.
     *
     * @return Best guess as to whether the data is in base64 format.
     *
     * @throws IOException if an IO error occurs.
     */
    public static boolean isBase64(InputStream in)
        throws IOException {
        long numBase64Chars = 0;
        int numPadding = 0;
        int read;

        while ((read = in.read()) != -1) {
            read = REVERSEBASE64CHARS[read];

            if (read == NON_BASE_64) {
                return false;
            } else if (read == NON_BASE_64_WHITESPACE) {
            } else if (read == NON_BASE_64_PADDING) {
                numPadding++;
                numBase64Chars++;
            } else if (numPadding > 0) {
                return false;
            } else {
                numBase64Chars++;
            }
        }

        if (numBase64Chars == 0) {
            return false;
        }

        if ((numBase64Chars % 4) != 0) {
            return false;
        }

        return true;
    }
}

