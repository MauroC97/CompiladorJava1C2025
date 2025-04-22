package lyc.compiler;

import java_cup.runtime.Symbol;
import lyc.compiler.factories.ParserFactory;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static com.google.common.truth.Truth.assertThat;
import static lyc.compiler.Constants.EXAMPLES_ROOT_DIRECTORY;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void assignmentWithExpression() throws Exception {
        compilationSuccessful("c:=d*(e-21)/4");
    }

    @Disabled
    @Test
    public void syntaxError() {
        compilationError("1234");
    }

    @Test
    void assignments() throws Exception {
        System.out.println("### Assignments test ###");
        compilationSuccessful(readFromResources("assignments.txt"));
    }

    @Disabled
    @Test
    void write() throws Exception {
        compilationSuccessful(readFromResources("write.txt"));
    }

    @Disabled
    @Test
    void read() throws Exception {
        compilationSuccessful(readFromResources("read.txt"));
    }

    @Test
    void comment() throws Exception {
        System.out.println("### Comment test ###");
        compilationSuccessful(readFromResources("comment.txt"));
    }

    @Test
    void init() throws Exception {
        System.out.println("### Init test ###");
        compilationSuccessful(readFromResources("init.txt"));
    }

    @Disabled
    @Test
    void and() throws Exception {
        compilationSuccessful(readFromResources("and.txt"));
    }

    @Disabled
    @Test
    void or() throws Exception {
        compilationSuccessful(readFromResources("or.txt"));
    }

    @Disabled
    @Test
    void not() throws Exception {
        compilationSuccessful(readFromResources("not.txt"));
    }

    @Disabled
    @Test
    void ifStatement() throws Exception {
        compilationSuccessful(readFromResources("if.txt"));
    }

    @Disabled
    @Test
    void whileStatement() throws Exception {
        compilationSuccessful(readFromResources("while.txt"));
    }

    private void compilationSuccessful(String input) throws Exception {
        assertThat(scan(input).sym).isEqualTo(ParserSym.EOF);
    }

    private void compilationError(String input) {
        assertThrows(Exception.class, () -> scan(input));
    }

    private Symbol scan(String input) throws Exception {
        System.out.println(input);
        return ParserFactory.create(input).parse();
    }

    private String readFromFile(String fileName) throws IOException {
        URL url = new URL(EXAMPLES_ROOT_DIRECTORY + "/%s".formatted(fileName));
        System.out.println("Reading from file: " + url);
        assertThat(url).isNotNull();
        return IOUtils.toString(url.openStream(), StandardCharsets.UTF_8);
    }

    private String readFromResources(String filename) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/%s".formatted(filename));
        assertThat(inputStream).isNotNull();
        return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
    }
}
