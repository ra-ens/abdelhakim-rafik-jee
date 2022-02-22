package serializer;

import java.io.IOException;
import java.io.InputStream;

/**
 * An interface for converting from data in an InputStream to an Object.
 * @param <T>
 */
public interface IDeserializer<T> {

    /**
     * Read an object of type T from the given InputStream.
     * @param inputStream the input stream
     * @return the deserialized object
     * @throws IOException in case of errors reading from the stream
     */
    T deserialize(InputStream inputStream) throws IOException;
}
