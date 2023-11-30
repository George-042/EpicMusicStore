package by.georgprog.epicmusicstore.utils;

import lombok.experimental.UtilityClass;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@UtilityClass
public class AudioFileUtils {

    public AudioFile getAudioFile(MultipartFile file) throws IOException, CannotReadException, TagException,
            InvalidAudioFrameException, ReadOnlyFileException {
        File aFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (
                InputStream inputStream = file.getInputStream();
                FileOutputStream outputStream = new FileOutputStream(aFile)) {
            IOUtils.copy(inputStream, outputStream);
        }
        AudioFile audioFile = new AudioFileIO().readFile(aFile);
        aFile.delete();
        return audioFile;
    }

    public AudioHeader getAudioHeader(AudioFile audioFile) {
        return audioFile.getAudioHeader();
    }
}
