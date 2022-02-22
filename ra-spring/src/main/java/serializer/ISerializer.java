package serializer;

import java.io.IOException;
import java.io.OutputStream;

/**
 * An interface for streaming an object to an OutputStream.
 * @param <T>
 */
public interface ISerializer<T> {

    /**
     * Write an object of type T to the given OutputStream.
     * @param object the object to serialize
     * @param outputStream the output stream
     * @throws IOException in case of errors writing to the stream
     */
    void serialize(T object, OutputStream outputStream) throws IOException;
}
