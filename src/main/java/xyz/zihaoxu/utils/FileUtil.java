package xyz.zihaoxu.utils;

import org.apache.commons.io.IOUtils;
import xyz.zihaoxu.Main;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class FileUtil {
    public static void saveResource(File target, String path, boolean overwrite) throws IOException {
        if (target.exists() && !overwrite) return;
        try (InputStream stream = Main.class.getClassLoader().getResourceAsStream(path)) {
            BufferedOutputStream bos = new BufferedOutputStream(Files.newOutputStream(target.toPath()));
            bos.write(IOUtils.toByteArray(stream));
            bos.close();
        }
    }
}
